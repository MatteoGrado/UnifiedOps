package de.grado.accountingservice.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "storage.s3")
@Getter
@Setter
public class S3Properties
{
    private String accessKey;
    private String secretKey;
    private String region;
    private String bucket;
}
