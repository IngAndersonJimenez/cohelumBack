package com.backend.web.controller;


import com.backend.domain.service.Impl.ImageService;
import com.backend.web.dto.Generic.ResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/v1/inventoryImages")
@CrossOrigin(origins = "http://localhost:4200")
public class ImageController {

    @Autowired
    private ImageService imageService;

    @PostMapping("/create")
    public ResponseEntity<ResponseDTO> createInventoryImage(
            @RequestPart(value = "image", required = false) MultipartFile file,
            @RequestParam(value = "customName", required = false) String customName)  throws IOException {
        this.imageService.createImage(file,customName);

        return ResponseEntity.ok(ResponseDTO
                .builder()
                .responseDTO("Image created successfully")
                .build());
    }

    @GetMapping
    public ResponseEntity<ResponseDTO> getInventoryByList() throws Exception {
        return ResponseEntity.ok(ResponseDTO
                .builder()
                .responseDTO(this.imageService.getImages())
                .build());
    }


}
