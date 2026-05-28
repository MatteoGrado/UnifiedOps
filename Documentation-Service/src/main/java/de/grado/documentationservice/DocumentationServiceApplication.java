package de.grado.documentationservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@ConfigurationPropertiesScan
@SpringBootApplication
public class DocumentationServiceApplication
{

    public static void main(String[] args)
    {
        SpringApplication.run(DocumentationServiceApplication.class, args);
    }

}
