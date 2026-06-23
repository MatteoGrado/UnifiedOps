package de.grado.accountingservice.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class Articles
{
    private String productNumber;
    private String productName;
    private int quantity;
    private BigDecimal singlePrice;
}
