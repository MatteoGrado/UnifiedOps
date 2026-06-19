package de.grado.accountingservice.service;

import de.grado.accountingservice.dto.InvoiceStatus;
import de.grado.accountingservice.dto.JournalEntryLineRequest;
import de.grado.accountingservice.dto.JournalEntryRequest;
import de.grado.accountingservice.model.Invoice;
import de.grado.accountingservice.model.JournalEntry;
import de.grado.accountingservice.model.JournalEntryLine;
import de.grado.accountingservice.model.Period;
import de.grado.accountingservice.repository.*;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class AccountingService
{
    private final PeriodService periodService;
    private final AccountRepository accountRepository;
    private final InvoiceRepository invoiceRepository;
    private final JournalEntryRepository journalEntryRepository;
    private final JournalEntryLineRepository journalEntryLineRepository;

    public JournalEntry createJournalEntry(Invoice invoice, JournalEntryRequest request)
    {
        Period period = periodService.getPostingPeriod(request.getBookingDate());

        JournalEntry journalEntry = new JournalEntry();
        journalEntry.setPeriod(period);
        journalEntry.setBookingDate(request.getBookingDate());
        journalEntry.setStatus(request.getJournalEntryStatus());

        for (JournalEntryLineRequest dto : request.getLines()) {

            JournalEntryLine line = new JournalEntryLine();
            line.setAccountName(dto.getAccountName());
            line.setAccountType(dto.getAccountType());
            line.setDebit(dto.getDebit());
            line.setCredit(dto.getCredit());

            line.setJournalEntry(journalEntry);
            journalEntry.getLines().add(line);
        }

        return journalEntryRepository.save(journalEntry);
    }

    private void validateStatus(String invoiceNumber)
    {
        Invoice invoice = invoiceRepository.findByInvoiceNumber(invoiceNumber)
                .orElseThrow(() ->
                        new EntityNotFoundException(
                                "Invoice with number " + invoiceNumber + " not found"
                        ));

        if (invoice.getStatus() != InvoiceStatus.APPROVED) {
            throw new IllegalStateException(
                    "Invoice with number " + invoiceNumber + " isn't approved");
        }
    }
}
