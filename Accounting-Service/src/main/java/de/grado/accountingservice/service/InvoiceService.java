package de.grado.accountingservice.service;

import de.grado.accountingservice.repository.InvoiceRepository;
import de.grado.accountingservice.dto.CreateInitialInvoiceRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Objects;

@Service
@RequiredArgsConstructor
@Slf4j
public class InvoiceService
{
    private final InvoiceRepository invoiceRepository;

    public Map<String, Objects> createInitialInvoice(CreateInitialInvoiceRequest request)
    {
        return Map.of();
    }
}