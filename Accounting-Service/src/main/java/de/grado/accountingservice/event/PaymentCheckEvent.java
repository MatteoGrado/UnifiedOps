package de.grado.accountingservice.event;

import de.grado.accountingservice.dto.Status;
import de.grado.accountingservice.model.InitialInvoicePosition;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class PaymentCheckEvent
{
    private String customerId;
    private String paymentConditions;
    private String invoiceNumber;
    private LocalDate invoiceDate;
    private LocalDate dueDate;

    private List<InitialInvoicePosition> positions = new ArrayList<>();

    private Status status;
}
