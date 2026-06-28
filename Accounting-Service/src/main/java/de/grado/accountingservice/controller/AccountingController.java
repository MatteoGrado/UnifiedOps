package de.grado.accountingservice.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/accounting")
@RequiredArgsConstructor
@Slf4j
public class AccountingController
{
    @GetMapping("/getJournal")
    public void getJournal()
    {
    }

    @PostMapping("/create/journalEntry")
    public void createJournalEntry()
    {
    }

    @PostMapping("/create/balanceSheet")
    public void createBalanceSheet()
    {
    }

    @GetMapping("/getBalanceSheet")
    public void getBalanceSheet()
    {
    }

    @PutMapping("/updateBalanceSheet")
    public void updateBalanceSheet()
    {
    }
}
