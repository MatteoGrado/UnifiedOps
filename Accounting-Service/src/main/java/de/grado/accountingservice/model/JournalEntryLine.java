package de.grado.accountingservice.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.GenerationType;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer accountNumber;
    private String accountName;

    @jakarta.persistence.Column(precision = 10, scale = 2)
    private BigDecimal credit;

    @jakarta.persistence.Column(precision = 10, scale = 2)
    private BigDecimal debit;
}
