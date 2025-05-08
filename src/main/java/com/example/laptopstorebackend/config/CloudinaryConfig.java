package com.example.laptopstorebackend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;

@Configuration
public class CloudinaryConfig {
    @Bean
    public Cloudinary cloudinary() {
        return new Cloudinary(ObjectUtils.asMap(
                "cloud_name", "drdv2fyi8",
                "api_key", "238772682472581",
                "api_secret", "BM6dwEBItuNaOp22H4MczFN2Opw",
                "secure", true
        ));
    }
}
