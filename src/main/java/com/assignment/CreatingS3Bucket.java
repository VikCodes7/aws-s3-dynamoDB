package com.assignment;

import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.AwsCredentialsProvider;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.*;

import java.io.File;

public class CreatingS3Bucket {

    /*
    Code referenced from AWS Documentation
    Link: https://docs.aws.amazon.com/sdk-for-java/latest/developer-guide/get-started.html
     */

    public static void main(String[] args) {
        createS3BucketAndUploadFile();

    }

    private static void createS3BucketAndUploadFile() {
        Region region  = Region.US_WEST_2;
        S3Client s3 = S3Client.builder().region(region).build();
        String bucket = "assignment1" + System.currentTimeMillis();
        String fileName = "Sai.txt";
        String filePath = "/Users/lib-user/Desktop/CSCI_5401/Sai.txt";

        bucketSetup(s3, region, bucket);
        System.out.println("Uploading file to AWS S3 : ");
        s3.putObject(PutObjectRequest.builder().bucket(bucket).key(fileName).build(),
                RequestBody.fromFile(new File(filePath)));
        System.out.println("Uploading complete");
        System.out.printf("%n");
    }

    private static void bucketSetup(S3Client s3, Region region, String bucket) {
        try {
            s3.createBucket(CreateBucketRequest.builder().bucket(bucket)
                    .createBucketConfiguration(CreateBucketConfiguration.builder()
                            .locationConstraint(region.id()).build()).build());
            System.out.println("Creating Bucket : " + bucket);
            s3.waiter().waitUntilBucketExists(HeadBucketRequest.builder().bucket(bucket).build());
            System.out.println(bucket + " is ready");
            System.out.println("%n");
        } catch (S3Exception e) {
            e.printStackTrace();
        }
    }
}
