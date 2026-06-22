package de.grado.accountingservice.service;

import de.grado.accountingservice.dto.InvoiceRequest;
import de.grado.accountingservice.dto.InvoiceStatus;
import de.grado.accountingservice.model.Invoice;
import de.grado.accountingservice.repository.InvoiceRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
@Transactional
@Slf4j
public class InvoiceService
{
    private final InvoiceRepository invoiceRepository;

    public void createInvoice(InvoiceRequest invoiceRequest)
    {
        Invoice invoice = new Invoice();

        invoice.setInvoiceNumber(invoiceRequest.getInvoiceNumber());
        invoice.setInvoiceDate(invoiceRequest.getInvoiceDate());
        invoice.setDueDate(invoiceRequest.getDueDate());
        invoice.setSupplierId(invoiceRequest.getSupplierId());
        invoice.setNetAmount(invoiceRequest.getNetAmount());
        invoice.setTaxAmount(invoiceRequest.getTaxAmount());
        invoice.setGrossAmount(invoiceRequest.getGrossAmount());
        invoice.setStatus(InvoiceStatus.DRAFT);

        invoiceRepository.save(invoice);
    }
}
