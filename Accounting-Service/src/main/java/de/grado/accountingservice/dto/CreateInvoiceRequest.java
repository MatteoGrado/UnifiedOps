package de.grado.accountingservice.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class CreateInvoiceRequest
{
    private String invoiceNumber;
    private LocalDate invoiceDate;
}
