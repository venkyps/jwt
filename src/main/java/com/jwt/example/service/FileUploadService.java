package com.jwt.example.service;

import com.jwt.example.entity.File;
import com.jwt.example.model.FileUploadDTO;
import com.jwt.example.repository.FileRepository;
import com.jwt.example.util.AESUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;
import java.util.UUID;

@Service
public class FileUploadService {

    @Autowired
    private FileRepository fileRepository;

    private static SecretKey getSecretKey() throws Exception {
        return AESUtils.generateKey();
    }

    public FileUploadDTO saveFile(MultipartFile multipartFile) throws Exception {
        File file = new File();
        SecretKey secretKey = getSecretKey();
        String keyString = Base64.getEncoder().encodeToString(secretKey.getEncoded());
        file.setSecretKey(keyString);
        file.setFileData(encryptFile(multipartFile,secretKey));
        fileRepository.save(file);
        return FileUploadDTO.builder().message("File upload saved successfully in DB").build();
    }

    public FileUploadDTO getFile(UUID id) throws Exception {
        File file = fileRepository.findById(id).get();
        byte[] decodedKey = Base64.getDecoder().decode(file.getSecretKey());
        return FileUploadDTO.builder().message("get file retrieved from DB").fileData(decryptFile(file.getFileData(),new SecretKeySpec(decodedKey, "AES"))).build();
    }

    private byte[] encryptFile(MultipartFile file,SecretKey secretKey) throws Exception{
        return AESUtils.encrypt(file.getBytes(),secretKey);
    }

    private byte[] decryptFile(byte[] fileData,SecretKey secretKey) throws Exception{
        return AESUtils.deCrypt(fileData,secretKey);
    }
}
