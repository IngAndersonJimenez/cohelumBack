package com.backend.web.controller;


import com.backend.domain.service.Impl.ImageService;
import com.backend.web.dto.Generic.ResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

    @PostMapping("/uploadImage")
    public ResponseEntity<String> createInventoryImage(
            @RequestPart(value = "image", required = false) MultipartFile file,
            @RequestParam(value = "folder", required = false) String folder) throws IOException {

        try {
            String rutaAlmacenamiento = this.imageService.storeImage(file, folder);
            return ResponseEntity.ok("Imagen almacenada exitosamente en: " + rutaAlmacenamiento);
        } catch (IOException ioException) {
            ioException.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al almacenar la imagen");
        }
    }

    @GetMapping
    public ResponseEntity<ResponseDTO> getInventoryByList(
            @RequestParam(value = "folder", required = false) String folder
    ) throws Exception {
        return ResponseEntity.ok(ResponseDTO
                .builder()
                .responseDTO(this.imageService.getImages(folder))
                .build());
    }


}
