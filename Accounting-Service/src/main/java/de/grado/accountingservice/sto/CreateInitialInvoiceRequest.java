package de.grado.accountingservice.sto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDate;

@Getter
@Setter
public class CreateInitialInvoiceRequest
{
    private String invoiceNumber;
    private String productNumber;
    private LocalDate invoiceDate;
    private LocalDate dueDate;

    private BigInteger customerId;

    private InvoiceItem invoiceItem;

    private int quantity;
    private BigDecimal netAmount;
    private BigDecimal taxAmount;
    private BigDecimal brutAmount;
}
