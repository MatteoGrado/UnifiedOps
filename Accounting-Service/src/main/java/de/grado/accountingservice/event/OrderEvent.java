package de.grado.accountingservice.event;

import lombok.Getter;

@Getter
public class OrderEvent
{
    private final String prefix = "DE";
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
