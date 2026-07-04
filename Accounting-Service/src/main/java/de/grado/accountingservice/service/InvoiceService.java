package de.grado.accountingservice.service;

import de.grado.accountingservice.dto.InvoiceRequest;
import de.grado.accountingservice.model.Article;
import de.grado.accountingservice.model.Invoice;
import de.grado.accountingservice.model.InvoicePosition;
import de.grado.accountingservice.repository.ArticleRepository;
import de.grado.accountingservice.repository.InvoiceRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class InvoiceService
{
    private final ArticleRepository articleRepository;
    private final InvoiceRepository invoiceRepository;

    public Invoice readInvoice(InvoiceRequest invoiceRequest)
    {
        Invoice invoice = new Invoice();

        invoice.setInvoiceNumber(invoiceRequest.getInvoiceNumber());
        invoice.setSellerId(invoiceRequest.getSellerId());
        invoice.setPaymentConditions(invoiceRequest.getPaymentConditions());

        List<InvoicePosition> positions = new ArrayList<>();
        for (InvoiceRequest.InvoicePositionRequest positionRequest : invoiceRequest.getPositions()) {
            Article article = articleRepository.findByArticleNumber(positionRequest.getArticleNumber())
                    .orElseThrow(() -> new EntityNotFoundException("Article not found"));

            InvoicePosition position = new InvoicePosition();
            position.setInvoice(invoice);
            position.setArticle(article);
            position.setQuantity(positionRequest.getQuantity());
            position.setUnitPrice(positionRequest.getUnitPrice());
            position.setDiscount(positionRequest.getDiscount());
            position.setVatRate(positionRequest.getVatRate());
            positions.add(position);
        }

        invoice.setPositions(positions);

        return invoiceRepository.save(invoice);
    }

    public List<Invoice> getInvoices()
    {
        return invoiceRepository.findAll();
    }

    public Invoice getInvoice(String invoiceNumber)
    {
        return invoiceRepository.findByInvoiceNumber(invoiceNumber);
    }
}
