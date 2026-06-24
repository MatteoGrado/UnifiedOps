package de.grado.accountingservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OutboundInvoice extends JpaRepository<OutboundInvoice, Long>
{
    OutboundInvoice findByInvoiceNumber(String invoiceNumber);
}
