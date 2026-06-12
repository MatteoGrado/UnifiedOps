package de.grado.accountingservice.dto;

import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Getter
public class InvoiceRequest
{
    private String invoiceNumber;
    private UUID supplierId;
    private LocalDate invoiceDate;
    private LocalDate dueDate;
    private BigDecimal netAmount;
    private BigDecimal taxAmount;
    private BigDecimal grossAmount;
}
