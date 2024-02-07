package com.example.Diplomna.services;

import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import com.google.firebase.FirebaseApp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

import com.google.cloud.storage.Bucket;
import com.google.cloud.storage.BucketInfo;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;

@Service
public class FirebaseService {
    @Autowired
    private FirebaseApp firebaseApp;
    private final Logger logger = LoggerFactory.getLogger(VideoService.class);

    public String uploadFile(MultipartFile file) throws IOException {
        // Получение ссылки на Firebase Cloud Storage
        Storage storage = com.google.cloud.storage.StorageOptions.getDefaultInstance().getService();
        logger.info("storage:  .... " + storage);
//        String bucketName = "login-c47f8.appspot.com"; // Замените на ваш Storage Bucket
//        BlobId blobId = BlobId.of(bucketName, file.getOriginalFilename());
//        logger.info("blobId:  .... " + blobId);
//        // Загрузка файла
//        BlobInfo blobInfo = storage.create(
//                BlobInfo.newBuilder(blobId)
//                        .setContentType(file.getContentType())
//                        .build(),
//                file.getBytes());
//
//        logger.info("blobInfo:  .... " + blobInfo);
//        // Возвращение URL загруженного файла
        String bucketName = "login-c47f8.appspot.com"; // Замініть на ваш Storage Bucket
        BlobId blobId = BlobId.of(bucketName, file.getOriginalFilename());
        logger.info("blobId:  .... " + blobId);
// Завантаження файлу
        BlobInfo blobInfo = storage.create(
                BlobInfo.newBuilder(blobId)
                        .setContentType(file.getContentType())
                        .build(),
                file.getBytes());
        logger.info("blobInfo:  .... " + blobInfo);
        return "blobInfo:  .... " + blobInfo;
    }
}
