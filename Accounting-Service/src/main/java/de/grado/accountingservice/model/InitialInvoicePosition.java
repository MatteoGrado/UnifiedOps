package de.grado.accountingservice.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name = "initial_invoice_position")
@Getter
@Setter
public class InitialInvoicePosition {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "invoice_id", nullable = false)
    private InitialInvoice invoice;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "article_id", nullable = false)
    private Article article;

    @Column
    private Integer accountNumber;

    @Column
    private String accountName;

    @Column(nullable = false)
    private Integer quantity;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal unitPrice;

    @Column(precision = 5, scale = 2)
    private BigDecimal discount;

    @Column(nullable = false, precision = 5, scale = 2)
    private BigDecimal vatRate;

    @Column(precision = 10, scale = 2)
    private BigDecimal debit;

    @Column(precision = 10, scale = 2)
    private BigDecimal credit;
}
