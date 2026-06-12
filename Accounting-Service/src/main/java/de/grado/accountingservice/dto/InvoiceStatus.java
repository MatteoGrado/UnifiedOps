package de.grado.accountingservice.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
public enum InvoiceStatus
{
    DRAFT,
    PENDING_APPROVAL,
    APPROVED,
    DUE,
    PARTIALLY_PAID,
    PAID,
    CANCELLED
}
