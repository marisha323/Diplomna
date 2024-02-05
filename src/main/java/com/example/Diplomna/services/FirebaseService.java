package com.example.Diplomna.services;

import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
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
@Service
public class FirebaseService {
    @Autowired
    private FirebaseApp firebaseApp;
    public String uploadFile(MultipartFile file) throws IOException {
        // Получение ссылки на Firebase Cloud Storage
        Storage storage = com.google.cloud.storage.StorageOptions.getDefaultInstance().getService();
        String bucketName = "login-c47f8.appspot.com"; // Замените на ваш Storage Bucket
        BlobId blobId = BlobId.of(bucketName, file.getOriginalFilename());

        // Загрузка файла
        BlobInfo blobInfo = storage.create(
                BlobInfo.newBuilder(blobId)
                        .setContentType(file.getContentType())
                        .build(),
                file.getBytes());

        // Возвращение URL загруженного файла
        return blobInfo.getMediaLink();
    }
}
