package de.grado.accountingservice.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.math.BigInteger;

@Entity
@Table(name = "invoice")
@Getter
@Setter
public class Invoice
{
    @Id
    private Long id;

    private BigInteger sellerId;

    private String invoiceNumber;

    @ManyToOne
    @JoinColumn(name = "article_number")
    private Article article;

    private BigDecimal netAmount;
    private BigDecimal taxAmount;
    private BigDecimal brutAmount;

    private String paymentConditions;
}
