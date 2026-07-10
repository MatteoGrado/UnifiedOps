package de.grado.accountingservice.model;

import de.grado.accountingservice.dto.Status;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "invoice")
@Getter
@Setter
public class Invoice
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String sellerId;

    private String invoiceNumber;

    private BigDecimal netAmount;
    private BigDecimal taxAmount;
    private BigDecimal brutAmount;

    private String paymentConditions;

    @OneToMany(mappedBy = "invoice", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<InvoicePosition> positions = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    private Status status;
}
