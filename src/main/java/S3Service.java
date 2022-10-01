
public interface S3Service {

  void listAllObjects(String bucketName);

  void putObject(String bucketName, String objectKey, String localPath);
}
