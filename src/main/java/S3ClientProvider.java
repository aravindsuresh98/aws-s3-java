
import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;

public class S3ClientProvider {

  public static final String S3_ACCESS_KEY_ID = "test-access-key";

  public static final String S3_SECRET_ACCESS_KEY = "test-secret-key";

  public AmazonS3 getCustomS3Client() {

    BasicAWSCredentials basicAWSCredentials =
        new BasicAWSCredentials(S3_ACCESS_KEY_ID, S3_SECRET_ACCESS_KEY);

    AWSCredentialsProvider awsCredentialsProvider =
        new AWSStaticCredentialsProvider(basicAWSCredentials);

    return AmazonS3ClientBuilder.standard()
        .withRegion(Regions.AP_SOUTH_1)
        .withCredentials(awsCredentialsProvider)
        .build();
  }
}
