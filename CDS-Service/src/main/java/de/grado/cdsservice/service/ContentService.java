package de.grado.cdsservice.service;

import de.grado.cdsservice.config.S3Properties;
import io.sentry.Sentry;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.core.ResponseInputStream;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.*;

import java.io.IOException;
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

    public void uploadFile(String fileName, MultipartFile file) throws Exception
    {
        PutObjectRequest request = PutObjectRequest.builder()
                .bucket(s3Properties.getBucket())
                .key(fileName)
                .contentType(file.getContentType())
                .build();

        s3Client.putObject(
                request,
                RequestBody.fromInputStream(
                        file.getInputStream(),
                        file.getSize()
                )
        );
    }

    public void deleteVideo(String fileName) throws Exception
    {
        DeleteObjectRequest request = DeleteObjectRequest.builder()
                .bucket(s3Properties.getBucket())
                .key(fileName)
                .build();

        s3Client.deleteObject(request);
    }
}
