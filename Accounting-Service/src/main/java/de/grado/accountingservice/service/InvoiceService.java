package de.grado.accountingservice.service;

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
}
