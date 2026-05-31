package de.grado.cdsservice.service;

import de.grado.cdsservice.config.S3Properties;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.ListObjectsV2Request;
import software.amazon.awssdk.services.s3.model.ListObjectsV2Response;
import software.amazon.awssdk.services.s3.model.S3Object;

import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class ContentService
{
    private final S3Properties s3Properties;
    private final S3Client s3Client;

    public List<String> getFiles()
    {
        ListObjectsV2Response response = s3Client.listObjectsV2(
                ListObjectsV2Request.builder()
                        .bucket(s3Properties.getBucket())
                        .build());

        return response.contents()
                .stream()
                .map(S3Object::key)
                .toList();
    }

    public void getFile(String fileName)
    {
    }
}
