package de.grado.accountingservice.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name = "article")
@Getter
@Setter
public class Article
{
    @Id
    private Long id;

    private String articleNumber;
    private String articleName;
    private String title;
    private String description;

    //Counts for one item!
    private BigDecimal netPrice;
    private BigDecimal tax;
    private BigDecimal brutPrice;
}
