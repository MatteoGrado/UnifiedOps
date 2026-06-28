package de.grado.accountingservice.repository;

import de.grado.accountingservice.model.JournalEntryLine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;

@Repository
public interface JournalEntryLineRepository extends JpaRepository<JournalEntryLine, BigInteger>
{
}
