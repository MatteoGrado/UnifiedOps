package de.grado.documentationservice;

import de.grado.documentationservice.config.S3Properties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@EnableConfigurationProperties(S3Properties.class)
@ConfigurationPropertiesScan
@SpringBootApplication
public class DocumentationServiceApplication
{

    public static void main(String[] args)
    {
        SpringApplication.run(DocumentationServiceApplication.class, args);
    }

}
