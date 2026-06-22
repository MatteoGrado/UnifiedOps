package de.grado.accountingservice.repository;

import de.grado.accountingservice.model.Period;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PeriodRepository extends JpaRepository<Period, Long>
{
    Optional<Period> findByYearAndMonth(int year, int month);
}
