package com.example.webcrawler.services;

import com.example.webcrawler.exceptions.GSCOperationException;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.bigquery.BigQuery;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.google.cloud.storage.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

@Service
public class GoogleCloudStorageService {


    @Value("${gcp.bucket.id}")
    private String gcpBucketId;

    private Storage storage;

    public GoogleCloudStorageService(Storage storage) {
        this.storage = storage;
    }


    public void uploadFile(byte[] image, String fileName) throws GSCOperationException {
        try {
            storage
                    .get(gcpBucketId, Storage.BucketGetOption.fields())
                    .create(fileName, image);
        } catch (Exception exception) {
            throw new GSCOperationException("An error occurred while uploading data to GCS");
        }
    }


    public byte[] getFile(String filePathUri) throws GSCOperationException {
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            storage
                    .get(BlobId.fromGsUtilUri(filePathUri))
                    .downloadTo(byteArrayOutputStream);
            return new ByteArrayInputStream(byteArrayOutputStream.toByteArray()).readAllBytes();
        } catch (Exception exception) {
            throw new GSCOperationException("An error occurred while getting data to GCS");
        }
    }


}
