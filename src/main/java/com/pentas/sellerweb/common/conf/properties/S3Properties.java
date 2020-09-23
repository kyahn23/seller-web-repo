package com.pentas.sellerweb.common.conf.properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component("s3Properties")
@PropertySource({ "classpath:properties/s3.properties" })
public class S3Properties {

    @Value("${s3.endPoint}")
    private String endPoint;

    @Value("${s3.regionName}")
    private String regionName;

    @Value("${s3.accessKey}")
    private String accessKey;

    @Value("${s3.secretKey}")
    private String secretKey;

    @Value("${s3.bucketName}")
    private String bucketName;

    public String getEndPoint() { return endPoint; }

    public void setEndPoint(String endPoint) { this.endPoint = endPoint; }

    public String getRegionName() { return regionName; }

    public void setRegionName(String regionName) { this.regionName = regionName; }

    public String getAccessKey() { return accessKey; }

    public void setAccessKey(String accessKey) { this.accessKey = accessKey; }

    public String getSecretKey() { return secretKey; }

    public void setSecretKey(String secretKey) { this.secretKey = secretKey; }

    public String getBucketName() { return bucketName; }

    public void setBucketName(String bucketName) { this.bucketName = bucketName; }
}
