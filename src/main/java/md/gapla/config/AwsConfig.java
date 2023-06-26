package md.gapla.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;

import java.net.URI;
import java.net.URISyntaxException;

@Configuration
public class AwsConfig {
    private String awsAccessKey;
    private String awsAccessSecretKey;

    private String awsRegion;

    private String s3BaseUrl;


    public AwsConfig(@Value(value = "${access.key}") String awsAccessKey,
                     @Value(value = "${secret.key}") String awsAccessSecretKey,
                     @Value(value = "${s3.endpoint}") String s3BaseUrl,
                     @Value(value = "${aws.region}") String awsRegion) {
        this.awsAccessKey = awsAccessKey;
        this.awsAccessSecretKey = awsAccessSecretKey;
        this.s3BaseUrl=s3BaseUrl;
        this.awsRegion = awsRegion;
    }

    public AwsBasicCredentials getCredentials() {
        return AwsBasicCredentials.create(this.awsAccessKey, this.awsAccessSecretKey);
    }

    @Bean
    public S3Client getAmazonS3Client() throws URISyntaxException {
        URI uri=new URI(s3BaseUrl);
        return S3Client.builder()
                .endpointOverride(uri)
                .credentialsProvider(StaticCredentialsProvider.create(getCredentials()))
                .region(Region.of(this.awsRegion))
                .build();
    }
}
