package de.grado.accountingservice.controller;

import de.grado.accountingservice.dto.CreateInvoiceRequest;
import de.grado.accountingservice.service.CreateOutboundInvoiceService;
import de.grado.accountingservice.service.InvoiceService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/accounting/")
@RequiredArgsConstructor
@Slf4j
public class CreateInvoiceController
{
    private final CreateOutboundInvoiceService createOutboundInvoiceService;

    @PostMapping("/create/invoice")
    public void createInvoice(@RequestBody CreateInvoiceRequest createInvoiceRequest)
    {
    }
}
