package de.grado.accountingservice.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class CreateInvoiceRequest
{
    private String invoiceNumber;
    private LocalDate invoiceDate;
    private BigDecimal grossAmount;
    private BigDecimal netAmount;
    private BigDecimal taxAmount;
    List<Articles> articles;
}
