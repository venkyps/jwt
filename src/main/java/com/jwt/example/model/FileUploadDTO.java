package com.jwt.example.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FileUploadDTO {

    private String message;
    private byte[] fileData;
}
