package de.grado.accountingservice.service;

import de.grado.accountingservice.dto.CreateInvoiceRequest;
import de.grado.accountingservice.repository.InvoiceRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.s3.S3Client;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class CreateOutboundInvoice
{
    private final S3Client s3Client;
    private final InvoiceRepository invoiceRepository;

    public void createInvoice(CreateInvoiceRequest createRequest)
    {
    }

    private void uploadInvoice()
    {
    }
}
