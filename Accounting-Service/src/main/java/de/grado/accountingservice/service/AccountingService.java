package de.grado.accountingservice.service;

import de.grado.accountingservice.dto.InvoiceStatus;
import de.grado.accountingservice.model.Invoice;
import de.grado.accountingservice.repository.AccountRepository;
import de.grado.accountingservice.repository.InvoiceRepository;
import de.grado.accountingservice.repository.JournalEntryLineRepository;
import de.grado.accountingservice.repository.JournalEntryRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class AccountingService
{
    private final AccountRepository accountRepository;
    private final InvoiceRepository invoiceRepository;
    private final JournalEntryRepository journalEntryRepository;
    private final JournalEntryLineRepository journalEntryLineRepository;

    public void createJournalEntry(Invoice invoice)
    {
        /*
        * TODO:
        *  Get Data from frontend "EntryRequest"
        *  Create a new Journal
        *     - Create a new if period over save the old two
        *  Check which period is active
        *  Create JournalEntryLine
        *     - Put data in
        *  Save all
        */
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
