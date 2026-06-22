package de.grado.accountingservice.repository;

import de.grado.accountingservice.model.JournalEntryLine;
import org.springframework.data.jpa.repository.JpaRepository;import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface JournalEntryLineRepository extends JpaRepository<JournalEntryLine, UUID>
{
    List<JournalEntryLine> findByJournalEntryId(
            UUID journalEntryId
    );

    List<JournalEntryLine> findByAccountNumber(
            String accountNumber
    );
}
