package de.grado.documentationservice.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@ConfigurationProperties(prefix = "storage.s3")
public class S3Properties
{
    private String accessKey;
    private String secretKey;
    private String region;
    private String bucket;
}
