package de.grado.accountingservice.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "journal_entry")
@Getter
@Setter
public class JournalEntry
{
    @Id
    private Long id;

    @ManyToOne
    private JournalEntryLine journalEntryLine;
}
