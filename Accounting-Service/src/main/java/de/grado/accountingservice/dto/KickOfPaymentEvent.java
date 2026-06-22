package de.grado.accountingservice.dto;

import de.grado.accountingservice.model.JournalEntry;
import de.grado.accountingservice.model.JournalEntryLine;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@RequiredArgsConstructor
public class KickOfPaymentEvent
{
    private UUID journalEntryId;
    private LocalDate dueDate;
    List<JournalEntryLineRequest> journalEntries;

    public KickOfPaymentEvent(LocalDate dueDate, List<JournalEntryLineRequest> journalEntries) {
        this.dueDate = dueDate;
        this.journalEntries = journalEntries;
    }
}
