package com.example.laptopstorebackend.controller;

import io.jsonwebtoken.io.IOException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

@RestController
@RequestMapping("/api/images")
public class ImageUploadController {

    private static final String UPLOAD_DIR = "D:\\LaptopStore\\LaptopStoreBackEnd\\images\\";

    @PostMapping("/upload")
    public ResponseEntity<String> uploadImage(@RequestParam("file") MultipartFile file) throws IOException, java.io.IOException {
        // Ensure directory exists
        File uploadDir = new File(UPLOAD_DIR);
        if (!uploadDir.exists()) uploadDir.mkdirs();

        // Save file
        String filePath = UPLOAD_DIR + file.getOriginalFilename();
        file.transferTo(new File(filePath));

        return ResponseEntity.ok("Image uploaded successfully: " + filePath);
    }
}
