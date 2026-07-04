package de.grado.accountingservice.service;

import de.grado.accountingservice.repository.JournalEntryLineRepository;
import de.grado.accountingservice.repository.JournalEntryRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class AccountingService
{
    private final JournalEntryRepository journalEntryRepository;
    private final JournalEntryLineRepository journalEntryLineRepository;

    public void bookPaidInitialInvoice()
    {
        /*
        * TODO:
        *  Book in initialInvoice and set Status to Done.
        */
    }

    public void bookDraftedInitialInvoice()
    {
        /*
        * TODO:
        *  Book in initialInvoice
        *   Trigger Event for Payment check
        *    Book again if paid
        */
    }

    public void bookPaidInvoice()
    {
        /*
         * TODO:
         *  Book in invoice and set Status to Done.
         */
    }

    public void bookDraftedInvoice()
    {
        /*
         * TODO:
         *  Book in invoice
         *   Trigger Event for Payment check
         *    Book again if paid
         */
    }
}
