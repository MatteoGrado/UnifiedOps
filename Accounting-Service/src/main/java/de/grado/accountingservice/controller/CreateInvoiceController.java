package de.grado.accountingservice.controller;

import de.grado.accountingservice.dto.CreateInvoiceRequest;
import de.grado.accountingservice.dto.CustomerQueue;
import de.grado.accountingservice.service.CreateOutboundInvoiceService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<byte[]> createInvoice(@RequestBody CreateInvoiceRequest request, @RequestBody CustomerQueue queue)
    {
        byte[] pdf = createOutboundInvoiceService.createInvoice(request, queue);

        return ResponseEntity.ok()
                .header(
                        HttpHeaders.CONTENT_DISPOSITION,
                        "inline; filename=\"invoice-" +
                                request.getInvoiceNumber() +
                                ".pdf\""
                )
                .contentType(MediaType.APPLICATION_PDF)
                .body(pdf);
    }
}
