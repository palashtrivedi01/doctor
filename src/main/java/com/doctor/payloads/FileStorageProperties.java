package com.doctor.payloads;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@ConfigurationProperties(prefix = "file")
@Component
public class FileStorageProperties {
    private String uploadDir;

    public void setUploadDir(String uploadDir) {
        this.uploadDir = uploadDir;
    }
}
