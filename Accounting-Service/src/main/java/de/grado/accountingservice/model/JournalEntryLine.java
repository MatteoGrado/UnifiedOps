package de.grado.accountingservice.model;

import de.grado.accountingservice.dto.AccountType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "journal_entry_lines")
@Getter
@Setter
public class JournalEntryLine
{
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "journal_entry_id")
    private JournalEntry journalEntry;

    private String accountNumber;

    private String accountName;

    private AccountType accountType;

    private BigDecimal debit;

    private BigDecimal credit;
}
