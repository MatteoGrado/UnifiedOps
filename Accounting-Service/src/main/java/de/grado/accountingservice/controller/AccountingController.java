package de.grado.accountingservice.controller;

import de.grado.accountingservice.model.JournalEntry;
import de.grado.accountingservice.service.AccountingService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/accounting")
@RequiredArgsConstructor
@Slf4j
public class AccountingController
{
    private final AccountingService accountingService;

    @GetMapping("/journal")
    public List<JournalEntry> getJournal()
    {
        return accountingService.getJournal();
    }

    @PostMapping("/journal")
    public JournalEntry createJournalEntry(@RequestBody JournalEntry journalEntry)
    {
        return accountingService.createJournalEntry(journalEntry);
    }

    @PostMapping("/balance-sheet")
    public Map<Integer, BigDecimal> createBalanceSheet()
    {
        return accountingService.createBalanceSheet();
    }

    @GetMapping("/balance-sheet")
    public Map<Integer, BigDecimal> getBalanceSheet()
    {
        return accountingService.getBalanceSheet();
    }

    @PutMapping("/balance-sheet")
    public Map<Integer, BigDecimal> updateBalanceSheet()
    {
        return accountingService.updateBalanceSheet();
    }
}
