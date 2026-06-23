package de.grado.accountingservice.config;

import io.sentry.Sentry;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@ConditionalOnProperty(prefix = "app.sentry-test", name = "enabled", havingValue = "true")
public class SentryConsoleTestRunner implements CommandLineRunner
{
    private final boolean throwAfterCapture;

    public SentryConsoleTestRunner(
            @Value("${app.sentry-test.throw-after-capture:false}") boolean throwAfterCapture
    ) {
        this.throwAfterCapture = throwAfterCapture;
    }

    @Override
    public void run(String... args)
    {
        RuntimeException testError = new RuntimeException("Sentry console test error");

        log.error("Triggering Sentry console test error", testError);
        Sentry.captureException(testError);
        Sentry.flush(5_000);

        if (throwAfterCapture) {
            throw testError;
        }
    }
}
