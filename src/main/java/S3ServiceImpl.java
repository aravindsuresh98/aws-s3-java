
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ListObjectsV2Result;
import java.io.File;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;

@Slf4j
public class S3ServiceImpl implements S3Service {

  private final AmazonS3 amazonS3Client;

  public S3ServiceImpl() {
    S3ClientProvider s3ClientProvider = new S3ClientProvider();
    amazonS3Client = s3ClientProvider.getCustomS3Client();
  }

  @Override
  public void listAllObjects(String bucketName) {
    final ListObjectsV2Result listObjectsV2Result = amazonS3Client.listObjectsV2(bucketName);
    if (listObjectsV2Result.getObjectSummaries().isEmpty()) {
      System.out.println("No objects found in the bucket :{}"+ bucketName);
    } else {
      listObjectsV2Result
          .getObjectSummaries()
          .forEach(
              s3ObjectSummary -> {
                log.info("Bucket Name : {}" , s3ObjectSummary.getBucketName());
                log.info("Object name : {}", s3ObjectSummary.getKey());
                log.info("Object storage class : {}", s3ObjectSummary.getStorageClass());
                log.info("Object last modified : {}", s3ObjectSummary.getLastModified());
              });
    }
    }

  @Override
  public void putObject(String bucketName, String objectKey, String localPath) {
    try {
      File reportFile = new File(localPath);
      amazonS3Client.putObject(bucketName, objectKey, reportFile);
    } catch (Exception e) {
      System.out.println(ExceptionUtils.getStackTrace(e));
    }
  }
}
