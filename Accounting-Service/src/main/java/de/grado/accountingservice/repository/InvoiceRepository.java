package de.grado.accountingservice.repository;

import de.grado.accountingservice.dto.Status;
import de.grado.accountingservice.model.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InvoiceRepository extends JpaRepository<Invoice, Long>
{
    Invoice findByInvoiceNumber(String invoiceNumber);
    List<Invoice> findByStatusIn(List<Status> status);
}
