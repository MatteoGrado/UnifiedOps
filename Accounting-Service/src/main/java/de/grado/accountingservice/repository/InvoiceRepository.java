package de.grado.accountingservice.repository;

import de.grado.accountingservice.dto.Status;
import de.grado.accountingservice.model.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

@Repository
public interface InvoiceRepository extends JpaRepository<Invoice, BigInteger>
{
    Invoice findByInvoiceNumber(String invoiceNumber);
    List<Invoice> findByStatus(List<Status> status);
}
