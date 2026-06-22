package de.grado.accountingservice.dto;

import de.grado.accountingservice.model.JournalEntryLine;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Getter
public class JournalEntryRequest
{
    private LocalDate bookingDate;
    private JournalEntryStatus journalEntryStatus;
    private List<JournalEntryLineRequest> lines;
}
