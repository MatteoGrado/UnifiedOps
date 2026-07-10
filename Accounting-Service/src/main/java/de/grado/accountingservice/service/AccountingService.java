package de.grado.accountingservice.service;

import de.grado.accountingservice.event.PaymentCheckEvent;
import de.grado.accountingservice.dto.Status;
import de.grado.accountingservice.model.InitialInvoice;
import de.grado.accountingservice.model.Invoice;
import de.grado.accountingservice.model.JournalEntry;
import de.grado.accountingservice.model.JournalEntryLine;
import de.grado.accountingservice.model.InitialInvoicePosition;
import de.grado.accountingservice.model.InvoicePosition;
import de.grado.accountingservice.repository.JournalEntryLineRepository;
import de.grado.accountingservice.repository.JournalEntryRepository;
import de.grado.accountingservice.repository.InitialInvoiceRepository;
import de.grado.accountingservice.repository.InvoiceRepository;
import jakarta.transaction.Transactional;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class AccountingService
{
    private final JournalEntryRepository journalEntryRepository;
    private final JournalEntryLineRepository journalEntryLineRepository;
    private final PeriodService periodService;
    private final InitialInvoiceRepository initialInvoiceRepository;
    private final InvoiceRepository invoiceRepository;
    private final RabbitTemplate rabbitTemplate;

    public List<JournalEntry> getJournal()
    {
        return journalEntryRepository.findAll();
    }

    public JournalEntry createJournalEntry(JournalEntry journalEntry)
    {
        return journalEntryRepository.save(journalEntry);
    }

    public Map<Integer, java.math.BigDecimal> getBalanceSheet()
    {
        return buildBalanceSheet();
    }

    public Map<Integer, java.math.BigDecimal> createBalanceSheet()
    {
        return buildBalanceSheet();
    }

    public Map<Integer, java.math.BigDecimal> updateBalanceSheet()
    {
        return buildBalanceSheet();
    }

    public void bookPaidInitialInvoice(InitialInvoice initialInvoice)
    {
        bookJournalEntry(initialInvoice.getPositions());
        initialInvoice.setStatus(Status.PAID);
        initialInvoiceRepository.save(initialInvoice);
    }

    @Transactional
    public void bookDraftedInitialInvoice(InitialInvoice initialInvoice)
    {
        if ("End of Month".equals(periodService.checkPeriod())) {
            log.info("Periode geschlossen. Rechnung {} wird nicht gebucht.",
                    initialInvoice.getInvoiceNumber());
            return;
        }

        bookJournalEntry(initialInvoice.getPositions());

        PaymentCheckEvent paymentCheckEvent = getPaymentCheckEvent(initialInvoice);
        rabbitTemplate.convertAndSend(
                "accounting.exchange",
                "accounting.routing.key",
                paymentCheckEvent
        );

        initialInvoice.setStatus(Status.OPEN);
        initialInvoiceRepository.save(initialInvoice);
    }

    private static @NonNull PaymentCheckEvent getPaymentCheckEvent(InitialInvoice initialInvoice)
    {
        PaymentCheckEvent paymentCheckEvent = new PaymentCheckEvent();

        paymentCheckEvent.setCustomerId(initialInvoice.getCustomerId());
        paymentCheckEvent.setInvoiceNumber(initialInvoice.getInvoiceNumber());
        paymentCheckEvent.setInvoiceDate(initialInvoice.getInvoiceDate());
        paymentCheckEvent.setDueDate(initialInvoice.getDueDate());
        paymentCheckEvent.setPaymentConditions(initialInvoice.getPaymentConditions());
        paymentCheckEvent.setPositions(initialInvoice.getPositions());
        return paymentCheckEvent;
    }

    public void bookPaidInvoice(Invoice invoice)
    {
        bookJournalEntry(invoice.getPositions());
        invoice.setStatus(Status.PAID);
        invoiceRepository.save(invoice);
    }

    public void bookDraftedInvoice(Invoice invoice)
    {
        bookJournalEntry(invoice.getPositions());

        PaymentCheckEvent paymentCheckEvent = new PaymentCheckEvent();
        paymentCheckEvent.setInvoiceNumber(invoice.getInvoiceNumber());
        paymentCheckEvent.setPaymentConditions(invoice.getPaymentConditions());
        paymentCheckEvent.setPositions(toInitialInvoicePositions(invoice.getPositions()));
        paymentCheckEvent.setStatus(Status.OPEN);

        rabbitTemplate.convertAndSend(
                "accounting.exchange",
                "accounting.routing.key",
                paymentCheckEvent
        );

        invoice.setStatus(Status.OPEN);
        invoiceRepository.save(invoice);
    }

    private void bookJournalEntry(Iterable<?> positions)
    {
        JournalEntry journalEntry = new JournalEntry();

        for (Object position : positions) {
            JournalEntryLine line = new JournalEntryLine();
            populateJournalEntryLine(position, line);
            journalEntry.getJournalEntryLines().add(line);
        }

        journalEntryRepository.save(journalEntry);
    }

    private static void populateJournalEntryLine(Object position, JournalEntryLine line)
    {
        if (position instanceof InitialInvoicePosition initialInvoicePosition) {
            line.setAccountNumber(initialInvoicePosition.getAccountNumber());
            line.setAccountName(initialInvoicePosition.getAccountName());
            line.setDebit(initialInvoicePosition.getDebit());
            line.setCredit(initialInvoicePosition.getCredit());
            return;
        }

        if (position instanceof InvoicePosition invoicePosition) {
            line.setAccountNumber(invoicePosition.getAccountNumber());
            line.setAccountName(invoicePosition.getAccountName());
            line.setDebit(invoicePosition.getDebit());
            line.setCredit(invoicePosition.getCredit());
        }
    }

    private static List<InitialInvoicePosition> toInitialInvoicePositions(Iterable<InvoicePosition> positions)
    {
        List<InitialInvoicePosition> paymentCheckPositions = new ArrayList<>();

        for (InvoicePosition position : positions) {
            InitialInvoicePosition paymentCheckPosition = new InitialInvoicePosition();
            paymentCheckPosition.setAccountNumber(position.getAccountNumber());
            paymentCheckPosition.setAccountName(position.getAccountName());
            paymentCheckPosition.setArticle(position.getArticle());
            paymentCheckPosition.setQuantity(position.getQuantity());
            paymentCheckPosition.setUnitPrice(position.getUnitPrice());
            paymentCheckPosition.setDiscount(position.getDiscount());
            paymentCheckPosition.setVatRate(position.getVatRate());
            paymentCheckPosition.setDebit(position.getDebit());
            paymentCheckPosition.setCredit(position.getCredit());
            paymentCheckPositions.add(paymentCheckPosition);
        }

        return paymentCheckPositions;
    }

    private Map<Integer, java.math.BigDecimal> buildBalanceSheet()
    {
        Map<Integer, java.math.BigDecimal> balanceSheet = new LinkedHashMap<>();

        for (JournalEntry journalEntry : journalEntryRepository.findAll()) {
            for (JournalEntryLine line : journalEntry.getJournalEntryLines()) {
                if (line.getAccountNumber() == null) {
                    continue;
                }

                java.math.BigDecimal debit = line.getDebit() != null ? line.getDebit() : java.math.BigDecimal.ZERO;
                java.math.BigDecimal credit = line.getCredit() != null ? line.getCredit() : java.math.BigDecimal.ZERO;
                java.math.BigDecimal netAmount = debit.subtract(credit);

                balanceSheet.merge(line.getAccountNumber(), netAmount, java.math.BigDecimal::add);
            }
        }

        return balanceSheet;
    }
}
