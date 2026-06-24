package de.grado.accountingservice.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Setter
@Getter
@Entity
@Table(name = "outbound_invoice")
public class OutboundInvoice
{
    @Id
    private Long id;

    private String invoiceNumber;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Accounting_Customer customer;

    @OneToMany(mappedBy = "invoice",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private List<Articles> articles;
}