package de.grado.rechnungswesenservice.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Getter
@Setter
public class Account
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, precision = 19, scale = 4)
    private BigDecimal balance;

    @Column(nullable = false, precision = 19, scale = 4)
    private BigDecimal sollAccount;

    @Column(nullable = false, precision = 19, scale = 4)
    private BigDecimal haveAccount;

    @Column(nullable = false, precision = 19, scale = 4)
    private BigDecimal amount;
}
