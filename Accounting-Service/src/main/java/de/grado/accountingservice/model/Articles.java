package de.grado.accountingservice.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name = "accounting_articles")
@Getter
@Setter
public class Articles
{
    @Id
    private Long id;

    private String productNumber;
    private String productName;
    private int quantity;
    private BigDecimal singlePrice;

    @ManyToOne
    @JoinColumn(name = "invoice_id")
    private OutboundInvoice invoice;
}
