package de.grado.accountingservice.service;

import com.openhtmltopdf.pdfboxout.PdfRendererBuilder;
import de.grado.accountingservice.dto.Articles;
import de.grado.accountingservice.dto.CreateInvoiceRequest;
import de.grado.accountingservice.dto.CustomerQueue;
import de.grado.accountingservice.model.Accounting_Customer;
import de.grado.accountingservice.repository.AccountingCustomerRepository;
import de.grado.accountingservice.repository.InvoiceRepository;
import io.sentry.Sentry;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;
import software.amazon.awssdk.services.s3.S3Client;

import java.io.ByteArrayOutputStream;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class CreateOutboundInvoiceService
{
    private final S3Client s3Client;
    private final InvoiceRepository invoiceRepository;
    private final SpringTemplateEngine templateEngine;
    private final AccountingCustomerRepository accountingCustomerRepository;

    //@RabbitListener(queues = "customer.queue")
    public void updateCustomer(CustomerQueue customerQueue)
    {
        Accounting_Customer customer =
                accountingCustomerRepository
                        .findByCustomerId(customerQueue.getCustomerId())
                        .orElse(new Accounting_Customer());

        customer.setCustomerId(customerQueue.getCustomerId());
        customer.setCompanyName(customerQueue.getCompanyName());
        customer.setStreet(customerQueue.getStreet());
        customer.setPostalCode(customerQueue.getPostalCode());
        customer.setCity(customerQueue.getCity());

        accountingCustomerRepository.save(customer);

        log.info("Customer {} updated", customer.getCustomerId());
    }

    public byte[] createInvoice(CreateInvoiceRequest createInvoiceRequest, CustomerQueue customerQueue)
    {
        Accounting_Customer customer =
                accountingCustomerRepository
                        .findByCustomerId(customerQueue.getCustomerId())
                        .orElseThrow(() -> new RuntimeException("Customer not found"));

        Context context = new Context();

        context.setVariable("company", customer.getCompanyName());
        context.setVariable("address", customer.getStreet());
        context.setVariable("zip", customer.getPostalCode());
        context.setVariable("city", customer.getCity());

        context.setVariable("arNummer", createInvoiceRequest.getInvoiceNumber());
        context.setVariable("date", createInvoiceRequest.getInvoiceDate());

        List<Articles> articles = createInvoiceRequest.getArticles();
        context.setVariable("articles", articles);

        context.setVariable("gesamtbetragnetto", createInvoiceRequest.getNetAmount());
        context.setVariable("gesamtbetragtax", createInvoiceRequest.getTaxAmount());
        context.setVariable("gesamtbetragbrutto", createInvoiceRequest.getGrossAmount());

        String html =
                templateEngine.process("rechnung", context);

        try (ByteArrayOutputStream os = new ByteArrayOutputStream()) {

            PdfRendererBuilder builder = new PdfRendererBuilder();

            builder.withHtmlContent(html, null);
            builder.toStream(os);
            builder.run();

            return os.toByteArray();
        } catch (Exception e) {
            Sentry.captureException(e);
            throw new RuntimeException(e);
        }
    }
}
