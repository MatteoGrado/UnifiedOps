package de.grado.rechnungswesenservice.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class MoneyMovementDto
{
    private BigDecimal amount;
}
