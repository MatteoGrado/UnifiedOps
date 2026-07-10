package de.grado.accountingservice.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class CreateInitialInvoiceRequest
{
    private String invoiceNumber;
    private LocalDate invoiceDate;
    private LocalDate dueDate;
    private String paymentConditions;

    private List<InvoicePositionRequest> positions = new ArrayList<>();

    @Getter
    @Setter
    public static class InvoicePositionRequest
    {
        private String articleNumber;
        private Integer accountNumber;
        private String accountName;
        private Integer quantity;
        private BigDecimal unitPrice;
        private BigDecimal discount;
        private BigDecimal vatRate;
        private BigDecimal debit;
        private BigDecimal credit;
    }
}
