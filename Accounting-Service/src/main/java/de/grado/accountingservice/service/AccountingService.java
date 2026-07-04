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

    @RabbitListener(queues = "accounting.queue")
    public void createJournalEntry()
    {
    }
}
