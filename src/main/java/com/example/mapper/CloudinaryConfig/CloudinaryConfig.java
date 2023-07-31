package com.example.mapper.CloudinaryConfig;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CloudinaryConfig {

    @Bean
    public Cloudinary cloudinary() {
        return new Cloudinary(ObjectUtils.asMap(
                "cloud_name", "dw1j2hha6",
                "api_key", "458949717878835",
                "api_secret", "RJ0mPFIzoYIuEK1aByxPGePn35s"));
    }
}
