package md.gapla.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import md.gapla.exception.CustomParseException;
import md.gapla.exception.IdentityProviderException;
import md.gapla.model.entity.lessons.LessonMaterialsEntity;
import md.gapla.service.S3BucketService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.CreateBucketRequest;
import software.amazon.awssdk.services.s3.model.HeadBucketRequest;
import software.amazon.awssdk.services.s3.model.NoSuchBucketException;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class S3BucketServiceImpl implements S3BucketService {

    private final S3Client s3Client;
//    private String bucketName;

    @Value(value = "${aws.region}")
    private String awsRegion;

    @Value(value = "${s3.endpoint}")
    private String s3BaseUrl;

    String bucketName = "test";

    @Override
    public String uploadFileToS3(MultipartFile multipartfile, LessonMaterialsEntity lessonMaterialsEntity, String filePathName) {
        String s3FileAccessUrl;
        try {

            Map<String, String> metadata = new HashMap<>();
            metadata.put("creation-date", String.valueOf(new Date()));
            metadata.put("lesson-material-id", lessonMaterialsEntity.getLessonMaterialId().toString());
//            metadata.put("name", account.getForename() + " " + account.getSurname());
//            metadata.put("account-id", String.valueOf(account.getAccountId()));
//            metadata.put("document-classification", "Confidential");

//            log.debug("Sending file in bucket name \"{}\" to folder \"{}\" with metadata: {}", bucketName, organisation.getOrganisationGuid() + "/" + filePathName, metadata.toString());
//            CreateBucketRequest createBucketRequest =  CreateBucketRequest.builder()
//                    .bucket(bucketName)
//                    .build();

            isExistedBucket(bucketName);
            s3Client.putObject(it -> it.bucket(bucketName)
                    .key(filePathName)
                    .metadata(metadata)
                    .build(), RequestBody.fromBytes(multipartfile.getBytes())
            );

            s3FileAccessUrl = s3BaseUrl.concat(bucketName).concat("?region=").concat(awsRegion).concat("&prefix=")
                    .concat("organisation.getOrganisationGuid()")
                    .concat("/")
                    .concat(filePathName);
            log.info("Document uploaded on " + s3FileAccessUrl);
        } catch (IOException e) {
            throw new CustomParseException("SAVE_FILE_ERROR");
        }
        return s3FileAccessUrl;
    }

    @Override
    public byte[] downloadFileFromS3(String key) {
        try {
            return s3Client.getObject(it -> it.key(key)
                            .bucket(bucketName))
                    .readAllBytes();

        } catch (IOException e) {
            throw new IdentityProviderException("Error a downloading file from S3", e);
        }
    }

//    public void deleteFile(String folderName, String fileName) {
//        s3Client.deleteObject(i -> i.bucket(bucketName).key(folderName + "/" + fileName));
//    }

    private void isExistedBucket(String bucketName) {
        HeadBucketRequest headBucketRequest = HeadBucketRequest.builder()
                .bucket(bucketName)
                .build();

        try {
            s3Client.headBucket(headBucketRequest);
        } catch (NoSuchBucketException e) {
            CreateBucketRequest createBucketRequest = CreateBucketRequest.builder()
                    .bucket(bucketName)
                    .build();
            s3Client.createBucket(createBucketRequest);
        }
    }
}
