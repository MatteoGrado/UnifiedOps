package de.grado.cdsservice.service;

import de.grado.cdsservice.config.S3Properties;
import io.sentry.Sentry;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.core.ResponseInputStream;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.*;

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

    public byte[] downloadFile(String fileName) throws Exception
    {
        try {
            ResponseInputStream<GetObjectResponse> response = s3Client.getObject(
                    GetObjectRequest.builder()
                            .bucket(s3Properties.getBucket())
                            .key(fileName)
                            .build());

            return response.readAllBytes();
        } catch (Exception e) {
            Sentry.captureException(e);
            throw new Exception("AWS Object not found!");
        }
    }

    //
}
