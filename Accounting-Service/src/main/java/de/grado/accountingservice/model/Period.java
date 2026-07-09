package de.grado.accountingservice.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.time.Month;
import java.time.Year;

@Entity
@Table(name = "accounting_period")
@Getter
@Setter
public class Period
{
    @Id
    private Long id;

    private Year year;
    private Month month;
}
