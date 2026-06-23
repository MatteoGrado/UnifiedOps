package de.grado.accountingservice.service;

import com.openhtmltopdf.pdfboxout.PdfRendererBuilder;
import de.grado.accountingservice.dto.Articles;
import de.grado.accountingservice.dto.CreateInvoiceRequest;
import de.grado.accountingservice.dto.CustomerQueue;
import de.grado.accountingservice.repository.InvoiceRepository;
import io.sentry.Sentry;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
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

    @RabbitListener(queues = "customer.queue")
    private byte[] createInvoice(CreateInvoiceRequest createInvoiceRequest, CustomerQueue queue)
    {
        Context context = new Context();

        context.setVariable("company", queue.getCompanyName());
        context.setVariable("address", queue.getStreet());
        context.setVariable("zip", queue.getPostalCode());
        context.setVariable("city", queue.getCity());

        context.setVariable("arNummer", createInvoiceRequest.getInvoiceNumber());
        context.setVariable("date", createInvoiceRequest.getInvoiceDate());

        List<Articles> articles = createInvoiceRequest.getArticles();

        for (Articles article : articles) {
            context.setVariable("productName", article.getProductName());
            context.setVariable("quantity", article.getQuantity());
            context.setVariable("singlePrice", article.getSinglePrice());
        }

        context.setVariable("gesamtbetragnetto", createInvoiceRequest.getNetAmount());
        context.setVariable("gesamtbetragtax", createInvoiceRequest.getTaxAmount();
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
