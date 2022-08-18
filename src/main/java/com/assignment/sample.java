package com.assignment;

import software.amazon.awssdk.auth.credentials.AwsCredentials;
import software.amazon.awssdk.services.s3.S3Client;

import java.io.*;

public class sample {

    public static void main(String[] args) throws IOException {
        S3Client client = S3Client.builder().build();
    }
}
