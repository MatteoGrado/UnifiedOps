package de.grado.accountingservice.model;

import de.grado.accountingservice.dto.Status;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "initial_invoice")
@Getter
@Setter
public class InitialInvoice
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String customerId;
    private String companyName;
    private String contactPerson;
    private String address;
    private String houseNumber;
    private String zipcode;
    private String city;
    private String country;
    private String state;
    private String paymentConditions;

    private String invoiceNumber;
    private LocalDate invoiceDate;
    private LocalDate dueDate;

    @OneToMany(
            mappedBy = "invoice",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<InitialInvoicePosition> positions = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    private Status status;
}
