package de.grado.accountingservice.model;

import de.grado.accountingservice.dto.InvoiceStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "invoices")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Invoice
{
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String invoiceNumber;

    private UUID supplierId;

    private LocalDate invoiceDate;

    private LocalDate dueDate;

    private BigDecimal netAmount;

    private BigDecimal taxAmount;

    private BigDecimal grossAmount;

    @Enumerated(EnumType.STRING)
    private InvoiceStatus status;
}
