package de.grado.accountingservice.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name = "journal_entry_line")
@Getter
@Setter
public class JournalEntryLine
{
    @Id
    private Long id;

    private int accountNumber;
    private String accountName;
    private BigDecimal credit;
    private BigDecimal debit;
}
