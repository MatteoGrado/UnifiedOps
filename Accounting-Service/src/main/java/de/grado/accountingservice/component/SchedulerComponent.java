package de.grado.accountingservice.component;

import de.grado.accountingservice.dto.Status;
import de.grado.accountingservice.model.InitialInvoice;
import de.grado.accountingservice.model.Invoice;
import de.grado.accountingservice.repository.InitialInvoiceRepository;
import de.grado.accountingservice.repository.InvoiceRepository;
import de.grado.accountingservice.service.AccountingService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class SchedulerComponent
{
    private final InvoiceRepository invoiceRepository;
    private final InitialInvoiceRepository initialInvoiceRepository;
    private final AccountingService accountingService;

    @Scheduled(cron = "0 */2 * * * *")
    public void processInvoices()
    {
        List<Invoice> invoices =
                invoiceRepository.findByStatus(
                        List.of(Status.DRAFT, Status.PAID));

        List<InitialInvoice> initialInvoices =
                initialInvoiceRepository.findByStatus(
                        List.of(Status.DRAFT, Status.PAID));

        for (Invoice invoice : invoices) {
            if (invoice.getStatus() == Status.DRAFT) {
                accountingService.bookDraftedInvoice(invoice);
            } else {
                accountingService.bookPaidInvoice(invoice);
            }
        }

        for (InitialInvoice initialInvoice : initialInvoices) {
            if (initialInvoice.getStatus() == Status.DRAFT) {
                accountingService.bookDraftedInitialInvoice(initialInvoice);
            } else {
                accountingService.bookPaidInitialInvoice(initialInvoice);
            }
        }
    }
}
