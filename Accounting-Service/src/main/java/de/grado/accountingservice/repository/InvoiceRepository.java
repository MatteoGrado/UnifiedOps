package de.grado.accountingservice.repository;

import de.grado.accountingservice.model.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;

@Repository
public interface InvoiceRepository extends JpaRepository<Invoice, BigInteger>
{
}
