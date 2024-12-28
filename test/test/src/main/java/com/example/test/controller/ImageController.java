package com.example.test.controller;

import com.example.test.models.Images;
import com.example.test.services.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class ImageController {

    @Autowired
    private ImageService imageService;

    public ImageController(ImageService imageService) {
        this.imageService = imageService;
    }




    @GetMapping("/images/{fileName}")
    public ResponseEntity<byte[]> getImage(@PathVariable String fileName) {
        try {
            // Xác định đường dẫn file
            Path path = Paths.get("uploads/").resolve(fileName);

            // Đọc file thành byte array
            byte[] imageData = Files.readAllBytes(path);

            // Trả về file ảnh với header đúng
            return ResponseEntity.ok()
                    .contentType(MediaType.IMAGE_JPEG) // hoặc MediaType.IMAGE_PNG
                    .body(imageData);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }




    // API để lấy ảnh từ cơ sở dữ liệu

}
