package de.grado.accountingservice.repository;

import de.grado.accountingservice.model.JournalEntry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

@Repository
public interface JournalEntryRepository extends JpaRepository<JournalEntry, BigDecimal>
{
}
