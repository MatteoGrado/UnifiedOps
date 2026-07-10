package de.grado.accountingservice.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "customer_snapshot")
@Getter
@Setter
public class CustomerSnapshot
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
}
