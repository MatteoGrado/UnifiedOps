package de.grado.accountingservice.service;

import de.grado.accountingservice.dto.CreateInitialInvoiceRequest;
import de.grado.accountingservice.dto.Status;
import de.grado.accountingservice.event.OrderEvent;
import de.grado.accountingservice.model.Article;
import de.grado.accountingservice.model.CustomerSnapshot;
import de.grado.accountingservice.model.InitialInvoice;
import de.grado.accountingservice.model.InitialInvoicePosition;
import de.grado.accountingservice.repository.ArticleRepository;
import de.grado.accountingservice.repository.CustomerSnapshotRepository;
import de.grado.accountingservice.repository.InitialInvoiceRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Slf4j
public class InitialInvoiceService
{
    private final ArticleRepository articleRepository;
    private final CustomerSnapshotRepository customerSnapshotRepository;
    private final InitialInvoiceRepository initialInvoiceRepository;

    //@RabbitListener(queues = "order.queue")
    private void saveCustomerSnapshot(OrderEvent event)
    {
        CustomerSnapshot customerSnapshot = new CustomerSnapshot();

        customerSnapshot.setCustomerId(
                event.getPrefix() + event.getCustomerId()
        );
        customerSnapshot.setCompanyName(event.getCompanyName());
        customerSnapshot.setContactPerson(event.getContactPerson());
        customerSnapshot.setAddress(event.getAddress());
        customerSnapshot.setHouseNumber(event.getHouseNumber());
        customerSnapshot.setZipcode(event.getZipcode());
        customerSnapshot.setCity(event.getCity());
        customerSnapshot.setCountry(event.getCountry());
        customerSnapshot.setState(event.getState());
        customerSnapshot.setPaymentConditions(event.getPaymentConditions());

        customerSnapshotRepository.save(customerSnapshot);
    }

    //@RabbitListener(queues = "order.queue")
    public Map<String, Object> createInitialInvoice(CreateInitialInvoiceRequest request, OrderEvent event)
    {
        String customerId = event.getPrefix() + event.getCustomerId();
        CustomerSnapshot customer =
                customerSnapshotRepository.findByCustomerId(customerId)
                        .orElseThrow(() -> new EntityNotFoundException("Customer not found"));

        InitialInvoice initialInvoice = new InitialInvoice();

        initialInvoice.setInvoiceNumber(request.getInvoiceNumber());
        initialInvoice.setInvoiceDate(request.getInvoiceDate());
        initialInvoice.setDueDate(request.getDueDate());
        initialInvoice.setStatus(Status.DRAFT);

        initialInvoice.setCustomerId(customer.getCustomerId());
        initialInvoice.setCompanyName(customer.getCompanyName());
        initialInvoice.setContactPerson(customer.getContactPerson());
        initialInvoice.setAddress(customer.getAddress());
        initialInvoice.setHouseNumber(customer.getHouseNumber());
        initialInvoice.setZipcode(customer.getZipcode());
        initialInvoice.setCity(customer.getCity());
        initialInvoice.setCountry(customer.getCountry());
        initialInvoice.setState(customer.getState());
        initialInvoice.setPaymentConditions(
                request.getPaymentConditions() != null ? request.getPaymentConditions() : customer.getPaymentConditions()
        );

        List<InitialInvoicePosition> positions = new ArrayList<>();
        for (CreateInitialInvoiceRequest.InvoicePositionRequest positionRequest : request.getPositions()) {
            Article article = articleRepository.findByArticleNumber(positionRequest.getArticleNumber())
                    .orElseThrow(() -> new EntityNotFoundException("Article not found"));

            InitialInvoicePosition position = new InitialInvoicePosition();
            position.setInvoice(initialInvoice);
            position.setArticle(article);

            position.setAccountNumber(positionRequest.getAccountNumber());
            position.setAccountName(positionRequest.getAccountName());
            position.setQuantity(positionRequest.getQuantity());
            position.setUnitPrice(positionRequest.getUnitPrice());
            position.setDiscount(positionRequest.getDiscount());
            position.setVatRate(positionRequest.getVatRate());
            position.setDebit(positionRequest.getDebit());
            position.setCredit(positionRequest.getCredit());
            positions.add(position);
        }

        initialInvoice.setPositions(positions);

        return Map.of(
                "invoice", initialInvoice,
                "customer", customer,
                "positions", request.getPositions()
        );
    }

    public List<InitialInvoice> getInitialInvoices()
    {
        return initialInvoiceRepository.findAll();
    }

    public InitialInvoice getInitialInvoice(String invoiceNumber)
    {
        return initialInvoiceRepository.findByInvoiceNumber(invoiceNumber);
    }
}
