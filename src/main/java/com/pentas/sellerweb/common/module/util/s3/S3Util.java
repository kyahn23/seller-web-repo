package com.pentas.sellerweb.common.module.util.s3;

import com.amazonaws.SdkClientException;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.*;
import com.amazonaws.util.IOUtils;
import com.pentas.sellerweb.common.conf.properties.S3Properties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public final class S3Util {

    private S3Util() {}

    public static void s3ObjectUpload(String endPoint, String regionName, String accessKey, String secretKey, String bucketName, InputStream fis, String fileTgt, Long fileSize, String fileType, String fileName) throws IOException {
        // S3 client
        final AmazonS3 s3 = AmazonS3ClientBuilder.standard()
                .withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration(endPoint, regionName))
                .withCredentials(new AWSStaticCredentialsProvider(new BasicAWSCredentials(accessKey, secretKey)))
                .build();

        String folderName = fileTgt + "/";

        ObjectMetadata objectMetadata = new ObjectMetadata();
        objectMetadata.setContentLength(0L);
        objectMetadata.setContentType("application/x-directory");
        PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, folderName, new ByteArrayInputStream(new byte[0]), objectMetadata);

        try {
            s3.putObject(putObjectRequest);
        } catch (AmazonS3Exception e) {
            e.printStackTrace();
        } catch(SdkClientException e) {
            e.printStackTrace();
        }

        // upload local file
        String objectName = folderName + fileName;

        if(fileType == null || fileType.equals("")) {
            fileType = "application/octet-stream;";
        }

        ObjectMetadata fileObjectMetadata = new ObjectMetadata();
        fileObjectMetadata.setContentLength(fileSize);
        fileObjectMetadata.setContentType(fileType);

        try {
            s3.putObject(bucketName, objectName, fis, fileObjectMetadata);
        } catch (AmazonS3Exception e) {
            e.printStackTrace();
        } catch(SdkClientException e) {
            e.printStackTrace();
        }
    }

    public static void s3FileDownload(HttpServletResponse response, String endPoint, String regionName, String accessKey, String secretKey, String bucketName, String filePath, String fileName) {
        // S3 client
        final AmazonS3 s3 = AmazonS3ClientBuilder.standard()
                .withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration(endPoint, regionName))
                .withCredentials(new AWSStaticCredentialsProvider(new BasicAWSCredentials(accessKey, secretKey)))
                .build();

        String objectName = filePath + fileName;

        // download object
        try {
            S3Object s3Object = s3.getObject(bucketName, objectName);
            S3ObjectInputStream s3ObjectInputStream = s3Object.getObjectContent();

            OutputStream outputStream = new BufferedOutputStream(response.getOutputStream());
            byte[] bytesArray = new byte[4096];
            int bytesRead = -1;
            while ((bytesRead = s3ObjectInputStream.read(bytesArray)) != -1) {
                outputStream.write(bytesArray, 0, bytesRead);
            }

            outputStream.close();
            s3ObjectInputStream.close();
        } catch (AmazonS3Exception e) {
            e.printStackTrace();
        } catch(SdkClientException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
