package de.grado.accountingservice.model;

import de.grado.accountingservice.dto.JournalEntryStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "journal_entries")
@Getter
@Setter
public class JournalEntry
{
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String reference;

    private LocalDate bookingDate;

    private String description;

    @Enumerated(EnumType.STRING)
    private JournalEntryStatus status;

    @OneToMany(
            mappedBy = "journalEntry",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )

    private List<JournalEntryLine> lines = new ArrayList<>();
}