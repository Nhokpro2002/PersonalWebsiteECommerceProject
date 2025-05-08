package com.example.laptopstorebackend.controller;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.example.laptopstorebackend.dto.response.ApiResponse;
import io.jsonwebtoken.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class ImageUploadController {

    @Autowired
    private Cloudinary cloudinary;

    /**
     *
     * @param file
     * @return
     * @throws IOException
     * @throws java.io.IOException
     */
    @PostMapping("/upload-image")
    public ApiResponse<String> uploadImage(@RequestParam("file") MultipartFile file) throws IOException, java.io.IOException {
        // Upload image into Cloudinary inside a specific folder
        Map<String, Object> options = ObjectUtils.asMap(
                "folder", "ImageLaptop"
        );
        Map<String, String> uploadResult = cloudinary.uploader().upload(file.getBytes(), options);

        return ApiResponse.<String>builder()
                .message("Upload Image successfully")
                .data(uploadResult.get("url"))
                .build();
    }

}
