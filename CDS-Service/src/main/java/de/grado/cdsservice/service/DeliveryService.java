package de.grado.cdsservice.service;

import de.grado.cdsservice.config.S3Properties;
import de.grado.cdsservice.dto.ReleaseDTO;
import de.grado.cdsservice.dto.ReleaseType;
import de.grado.cdsservice.dto.Supporter;
import io.sentry.Sentry;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.core.ResponseInputStream;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;
import software.amazon.awssdk.services.s3.model.GetObjectResponse;

@Service
@AllArgsConstructor
@Slf4j
public class DeliveryService
{
    private final S3Properties s3Properties;
    private final S3Client s3Client;

    public void releaseVideo(String filename, ReleaseDTO releaseDto, ReleaseType releaseType) throws Exception
    {
        GetObjectRequest request = GetObjectRequest.builder()
                .bucket(s3Properties.getBucket())
                .key(filename)
                .build();

        s3Client.getObject(request);


        try {
            ResponseInputStream<GetObjectResponse> inputStream =
                    s3Client.getObject(request);

            byte[] content = inputStream.readAllBytes();

            if (releaseDto.getReleaseType() == ReleaseType.TIMED_RELEASE) {
                if (releaseDto.getSupporterOnly() == Supporter.ONLY_FOR_SUPPORTER) {
                    //Show only for supporter & Timed Release
                } else {
                    //Non Supporter Timed Release
                }
            } else {
                if (releaseDto.getSupporterOnly() == Supporter.ONLY_FOR_SUPPORTER) {
                    //Show only for supporter & Release now
                } else {
                    //Non Supporter Release now
                }
                //Direct Release
            }
        } catch (Exception exception) {
            Sentry.captureException(exception);
            throw new Exception("Some Error has happened!" + exception.getMessage());
        }
    }
}
