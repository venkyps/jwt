package com.jwt.example.controller;

import com.jwt.example.model.FileUploadDTO;
import com.jwt.example.service.FileUploadService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@Tag(name="File upload API")
@RestController
public class FileUploadController {

    @Autowired
    private FileUploadService fileUploadService;

    @PostMapping("/upload")
    @Operation(
            summary = "Upload a single file",
            description = "Upload a file using multipart/form-data",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    required = true,
                    content = @Content(
                            mediaType = "multipart/form-data",
                            schema = @Schema(type = "string", format = "binary")
                    )
            ),
            responses = {
                    @ApiResponse(responseCode = "200", description = "File uploaded successfully")
            }
    )
    public ResponseEntity<FileUploadDTO> fileUpload(@RequestParam("file") MultipartFile multipartFile) throws  Exception{
        return ResponseEntity.ok(fileUploadService.saveFile(multipartFile));
    }

    @GetMapping("/getFile")
    @Operation(summary = "retrieve file", description = "retrieve file")
    public ResponseEntity<byte[]> getFile(@RequestParam("id") UUID id) throws  Exception{
        FileUploadDTO fileUploadDTO = fileUploadService.getFile(id);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"sample.txt\"")
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(fileUploadDTO.getFileData());
    }

}
