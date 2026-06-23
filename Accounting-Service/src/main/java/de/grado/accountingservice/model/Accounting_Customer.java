package de.grado.accountingservice.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "accounting_customer")
@Getter
@Setter
public class Accounting_Customer
{
    @Id
    private Long customerId;

    private String companyName;

    private String contactPerson;

    private String street;

    private String postalCode;

    private String city;

    private String country;

    private String email;

    private String vatId; // USt-IdNr.

    private LocalDateTime updatedAt;
}
