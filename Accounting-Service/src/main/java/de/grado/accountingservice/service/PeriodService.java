package de.grado.accountingservice.service;

import de.grado.accountingservice.repository.PeriodRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.YearMonth;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class PeriodService
{
    private final PeriodRepository periodRepository;

    public String checkPeriod()
    {
        LocalDate currentDay = LocalDate.now();
        LocalDate endOfMonth = YearMonth.now().atEndOfMonth();

        if (!currentDay.isBefore(endOfMonth)) {
            return "End of Month";
        } else {
            return "Period Not Over!";
        }
    }
}
