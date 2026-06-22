package de.grado.accountingservice.model;

import de.grado.accountingservice.dto.AccountType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "accounts")
@Getter
@Setter
public class Account
{
    @Id
    private String accountNumber;

    private String name;

    @Enumerated(EnumType.STRING)
    private AccountType type;
}
