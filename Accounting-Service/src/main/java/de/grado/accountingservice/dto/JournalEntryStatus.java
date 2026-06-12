package de.grado.accountingservice.dto;

import lombok.Getter;

@Getter
public enum JournalEntryStatus
{
    DRAFT,
    PENDING_APPROVAL,
    POSTED,
    REVERSED,
    CLOSED
}
