package com.backend.web.controller;

import com.backend.domain.service.InventoryImageService;
import com.backend.web.dto.Generic.ResponseDTO;
import com.backend.web.dto.InventoryImage.GetInventoryImageDTO;
import com.backend.web.dto.InventoryImage.InventoryImageDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/v1/inventoryImage")
@CrossOrigin
public class InventoryImageController {

    @Autowired
    private InventoryImageService inventoryImageService;

    @PostMapping("/create/{idInventory}")
    public ResponseEntity<ResponseDTO> createInventoryImage(
            @RequestPart(value = "image", required = false) MultipartFile file,
            @PathVariable Integer idInventory,
            @RequestParam(value = "fileName") String fileName) throws Exception {
        return ResponseEntity.ok(ResponseDTO
                .builder()
                .responseDTO(this.inventoryImageService.createInventoryImage(file, idInventory, fileName))
                .build());
    }


    @GetMapping("/{idInventory}")
    public ResponseEntity<String> getImageById(@PathVariable Integer idInventory) throws Exception {
        GetInventoryImageDTO image = this.inventoryImageService.getImageById(idInventory);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_JPEG);
        return new ResponseEntity<>(image.getImage(), headers, HttpStatus.OK);
    }

    @PutMapping("/update/{idInventoryImage}")
    public ResponseEntity<ResponseDTO> updateInventoryImage(
            @RequestPart(value = "image", required = false) MultipartFile file,
            @PathVariable Integer idInventoryImage,
            @RequestParam(value = "fileName") String fileName) throws Exception {
        return ResponseEntity.ok(ResponseDTO
                .builder()
                .responseDTO(this.inventoryImageService.updateInventoryImage(file, idInventoryImage,fileName))
                .build());
    }

    @DeleteMapping("/delete/{idInventoryImage}")
    public ResponseEntity<ResponseDTO> deleteInventoryImage(@PathVariable Integer idInventoryImage) throws Exception {
        inventoryImageService.deleteByIdInventoryImage(idInventoryImage);
        return ResponseEntity.ok(ResponseDTO
                .builder()
                .build());
    }

}
