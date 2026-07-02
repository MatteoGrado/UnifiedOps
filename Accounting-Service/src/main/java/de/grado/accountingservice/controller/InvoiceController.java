package de.grado.accountingservice.controller;


import de.grado.accountingservice.event.OrderEvent;
import de.grado.accountingservice.model.InitialInvoice;
import de.grado.accountingservice.service.InvoiceService;
import de.grado.accountingservice.dto.CreateInitialInvoiceRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/accounting")
@RequiredArgsConstructor
@Slf4j
public class InvoiceController
{
    private final InvoiceService invoiceServervice;

    @GetMapping("/getInitialInvoice/{invoiceNumber}")
    public InitialInvoice getInvoice(@PathVariable String invoiceNumber)
    {
        return invoiceServervice.getInitialInvoice(invoiceNumber);
    }

    @GetMapping("/getInitialInvoices")
    public List<InitialInvoice> getInvoices()
    {
        return invoiceServervice.getInvoices();
    }

    @PostMapping("/readInvoice")
    public void readInvoice()
    {
        //TODO: Only use when we receive a new product
    }

    @PostMapping("/createInitialInvoice")
    public Map<String, Object> createInitialInvoice(@RequestBody CreateInitialInvoiceRequest request, OrderEvent event)
    {
        return invoiceServervice.createInitialInvoice(request, event);
    }
}
