package de.grado.accountingservice.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "journal_entry")
@Getter
@Setter
public class JournalEntry
{
    @Id
    private Long id;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "journal_entry_id")
    private List<JournalEntryLine> journalEntryLines = new ArrayList<>();
}
