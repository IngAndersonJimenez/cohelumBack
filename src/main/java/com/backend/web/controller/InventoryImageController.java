package com.backend.web.controller;

import com.backend.domain.service.InventoryImageService;
import com.backend.web.dto.Generic.ResponseDTO;
import com.backend.web.dto.InventoryImage.InventoryImageDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/v1/inventoryImage")
public class InventoryImageController {

    @Autowired
    private InventoryImageService inventoryImageService;

    @PostMapping("/create")
    public ResponseEntity<ResponseDTO> createInventoryImage(@RequestBody InventoryImageDTO inventoryImageDTO, @RequestParam("file") MultipartFile file) throws Exception {
        return ResponseEntity.ok(ResponseDTO
                .builder()
                .responseDTO(this.inventoryImageService.createInventoryImage(inventoryImageDTO,file))
                .build());
    }

    @GetMapping("/{idInventory}")
    public ResponseEntity<ResponseDTO> getImageById(@PathVariable Integer idInventory) throws Exception {
        return ResponseEntity.ok(ResponseDTO
                .builder()
                .responseDTO(this.inventoryImageService.getImageById(idInventory))
                .build());
    }
}
