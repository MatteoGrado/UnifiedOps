package de.grado.accountingservice.dto;

import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class JournalEntryLineRequest
{
    private String accountName;
    private AccountType accountType;

    private BigDecimal debit;
    private BigDecimal credit;
}
