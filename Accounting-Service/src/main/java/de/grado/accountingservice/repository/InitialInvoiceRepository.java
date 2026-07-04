package de.grado.accountingservice.repository;

import de.grado.accountingservice.dto.Status;
import de.grado.accountingservice.model.InitialInvoice;
import de.grado.accountingservice.model.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.List;

@Repository
public interface InitialInvoiceRepository extends JpaRepository<InitialInvoice, BigInteger>
{
    InitialInvoice findByInvoiceNumber(String invoiceNumber);
    List<InitialInvoice> findByStatus(List<Status> status);
}
