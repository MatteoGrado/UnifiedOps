package de.grado.accountingservice.sto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class InvoiceItem
{
    private String productNumber;
    private BigDecimal itemPrice;
}
