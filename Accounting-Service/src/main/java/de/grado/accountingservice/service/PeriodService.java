package de.grado.accountingservice.service;

import de.grado.accountingservice.dto.PeriodStatus;
import de.grado.accountingservice.model.Period;
import de.grado.accountingservice.repository.PeriodRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@Transactional
@AllArgsConstructor
public class PeriodService
{
    private final PeriodRepository periodRepository;

    public Period getPostingPeriod(LocalDate bookingDate)
    {

        int year = bookingDate.getYear();
        int month = bookingDate.getMonthValue();

        Period period = periodRepository.findByYearAndMonth(year, month)
                .orElseGet(() -> createPeriod(year, month));

        if (period.getStatus() == PeriodStatus.CLOSED) {
            throw new IllegalStateException(
                    "Die Periode " + month + "/" + year + " ist bereits abgeschlossen.");
        }

        return period;
    }

    private Period createPeriod(int year, int month)
    {

        Period period = new Period();
        period.setYear(year);
        period.setMonth(month);
        period.setStatus(PeriodStatus.OPEN);

        return periodRepository.save(period);
    }
}
