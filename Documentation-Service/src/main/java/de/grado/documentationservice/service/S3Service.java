package de.grado.documentationservice.service;

import de.grado.documentationservice.config.S3Properties;
import io.sentry.Sentry;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.core.exception.SdkClientException;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.core.sync.ResponseTransformer;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.*;

import java.nio.file.Path;
import java.util.List;

@Service
@AllArgsConstructor
public class S3Service
{
    private final S3Properties s3Properties;
    private final S3Client s3Client;

    public void uploadFile(String key, byte[] data)
    {
        s3Client.putObject(
                PutObjectRequest.builder()
                        .bucket(s3Properties.getBucket())
                        .key(key)
                        .build(),
                RequestBody.fromBytes(data)
        );
    }

    public List<String> listFiles()
    {
        try {
            ListObjectsV2Response response = s3Client.listObjectsV2(
                    ListObjectsV2Request.builder()
                            .bucket(s3Properties.getBucket())
                            .build());

            return response.contents()
                    .stream()
                    .map(S3Object::key)
                    .toList();
        } catch (SdkClientException e) {
            Sentry.captureException(e);
            System.out.println(e.getMessage());
        }
        return null;
    }

    public byte[] getFile(String filename)
    {

        GetObjectRequest request = GetObjectRequest.builder()
                .bucket(s3Properties.getBucket())
                .key(filename)
                .build();

        return s3Client.getObjectAsBytes(request)
                .asByteArray();
    }

    public void deleteFile(String filename)
    {
        DeleteObjectRequest request =
                DeleteObjectRequest.builder()
                        .bucket(s3Properties.getBucket())
                        .key(filename)
                        .build();

        s3Client.deleteObject(request);
    }

    public void createFolder(String folderName)
    {
        s3Client.putObject(
                PutObjectRequest.builder()
                        .bucket(s3Properties.getBucket())
                        .key(folderName)
                        .build(),
                RequestBody.empty()
        );
    }

    public void downloadFile(String fileName)
    {
        Path target = Path.of("downloads", fileName);

        s3Client.getObject(
                GetObjectRequest.builder()
                        .bucket(s3Properties.getBucket())
                        .key(fileName)
                        .build(),
                ResponseTransformer.toFile(target)
        );
    }

    public void deleteFolder(String folderName)
    {
        DeleteObjectRequest request =
                DeleteObjectRequest.builder()
                        .bucket(s3Properties.getBucket())
                        .key(folderName)
                        .build();
        s3Client.deleteObject(request);
    }
}
