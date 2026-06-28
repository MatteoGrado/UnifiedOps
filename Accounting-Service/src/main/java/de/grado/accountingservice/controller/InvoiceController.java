package de.grado.accountingservice.controller;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/accounting")
@RequiredArgsConstructor
@Slf4j
public class InvoiceController
{
    @GetMapping("/getInvoice/{invoiceNumber}")
    public void getInvoice(@PathVariable String invoiceNumber)
    {
    }

    @GetMapping("/getInvoices")
    public void getInvoices()
    {
    }

    @PostMapping("/readInvoice")
    public void readInvoice()
    {
        //TODO: Only use when we receive a new product
    }

    @PostMapping("/createInitialInvoice")
    public void createInitialInvoice()
    {
    }
}
