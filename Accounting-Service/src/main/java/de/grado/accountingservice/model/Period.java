package de.grado.accountingservice.model;

import de.grado.accountingservice.dto.PeriodStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "period")
@Getter
@Setter
public class Period
{
    @Id
    @GeneratedValue
    private Long id;

    private int year;
    private int month;

    @Enumerated(EnumType.STRING)
    private PeriodStatus status;
}
