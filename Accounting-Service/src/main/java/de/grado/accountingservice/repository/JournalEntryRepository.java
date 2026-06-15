package de.grado.accountingservice.repository;

import de.grado.accountingservice.dto.JournalEntryStatus;
import de.grado.accountingservice.model.JournalEntry;
import org.springframework.data.jpa.repository.JpaRepository;import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Repository
public interface JournalEntryRepository extends JpaRepository<JournalEntry, UUID>
{
    List<JournalEntry> findByStatus(
            JournalEntryStatus status
    );

    List<JournalEntry> findByBookingDateBetween(
            LocalDate from,
            LocalDate to
    );

    List<JournalEntry> findByReference(
            String reference
    );
}
