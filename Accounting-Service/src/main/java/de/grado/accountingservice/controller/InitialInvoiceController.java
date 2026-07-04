package de.grado.accountingservice.controller;


import de.grado.accountingservice.event.OrderEvent;
import de.grado.accountingservice.model.InitialInvoice;
import de.grado.accountingservice.service.InitialInvoiceService;
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
public class InitialInvoiceController
{
    private final InitialInvoiceService initialInvoiceService;

    @GetMapping("/getInitialInvoice/{invoiceNumber}")
    public InitialInvoice getInvoice(@PathVariable String invoiceNumber)
    {
        return initialInvoiceService.getInitialInvoice(invoiceNumber);
    }

    @GetMapping("/getInitialInvoices")
    public List<InitialInvoice> getInvoices()
    {
        return initialInvoiceService.getInitialInvoices();
    }

    @PostMapping("/createInitialInvoice")
    public Map<String, Object> createInitialInvoice(@RequestBody CreateInitialInvoiceRequest request, OrderEvent event)
    {
        return initialInvoiceService.createInitialInvoice(request, event);
    }
}
