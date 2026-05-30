package de.grado.documentationservice.config;

import lombok.Getter;
import org.springframework.context.annotation.Configuration;

@Configuration
@Getter
public class S3Properties
{
    private String accessKey;
    private String secretKey;
    private String region;
    private String bucket;
}
