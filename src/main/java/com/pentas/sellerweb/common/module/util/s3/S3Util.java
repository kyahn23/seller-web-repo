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

    /**
     * S3 호환 스토리지 파일 (오브젝트) 업로드
     * @param endPoint S3 엔드포인트 주소
     * @param regionName S3 서버 지역
     * @param accessKey S3 액세스키
     * @param secretKey S3 비밀키
     * @param bucketName S3 저장버킷명
     * @param fis 파일 인풋스트림
     * @param fileTgt 파일 폴더명
     * @param fileSize 파일크기
     * @param fileType 파일타입
     * @param fileName 파일명
     * @throws IOException
     */
    public static void s3ObjectUpload(String endPoint, String regionName, String accessKey, String secretKey, String bucketName, InputStream fis, String fileTgt, Long fileSize, String fileType, String fileName) throws IOException {
        // S3 client
        final AmazonS3 s3 = AmazonS3ClientBuilder.standard()
                .withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration(endPoint, regionName))
                .withCredentials(new AWSStaticCredentialsProvider(new BasicAWSCredentials(accessKey, secretKey)))
                .build();

        // 해당 폴더 생성
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

        // 파일 업로드
        // 오브젝트(키)명 형식: '폴더명/파일명'
        String objectName = folderName + fileName;

        // 파일 메타데이터에 파일크기, 파일타입 주입
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

    /**
     * S3 호환 스토리지 파일 (오브젝트) 다운로드
     * @param response 파일 전달용 서블릿 응답
     * @param endPoint S3 엔드포인트 주소
     * @param regionName S3 서버 지역
     * @param accessKey S3 액세스키
     * @param secretKey S3 비밀키
     * @param bucketName S3 저장버킷명
     * @param filePath 파일 폴더명
     * @param fileName 파일명
     */
    public static void s3FileDownload(HttpServletResponse response, String endPoint, String regionName, String accessKey, String secretKey, String bucketName, String filePath, String fileName) {
        // S3 client
        final AmazonS3 s3 = AmazonS3ClientBuilder.standard()
                .withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration(endPoint, regionName))
                .withCredentials(new AWSStaticCredentialsProvider(new BasicAWSCredentials(accessKey, secretKey)))
                .build();

        // 파일 다운로드
        // 오브젝트(키)명 형식: '폴더명/파일명'
        String objectName = filePath + fileName;

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
