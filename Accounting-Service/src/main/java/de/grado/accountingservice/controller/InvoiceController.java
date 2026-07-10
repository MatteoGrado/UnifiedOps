package de.grado.accountingservice.controller;

import de.grado.accountingservice.dto.InvoiceRequest;
import de.grado.accountingservice.model.Invoice;
import de.grado.accountingservice.service.InvoiceService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/accounting/invoices")
@RequiredArgsConstructor
@Slf4j
public class InvoiceController
{
    private final InvoiceService invoiceService;

    @GetMapping
    public List<Invoice> getInvoices()
    {
        return invoiceService.getInvoices();
    }

    @GetMapping("/{invoiceNumber}")
    public Invoice getInvoice(@PathVariable String invoiceNumber)
    {
        return invoiceService.getInvoice(invoiceNumber);
    }

    @PostMapping
    public Invoice readInvoice(@RequestBody InvoiceRequest invoiceRequest)
    {
        return invoiceService.readInvoice(invoiceRequest);
    }
}
