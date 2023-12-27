package com.backend.web.controller;

import com.backend.domain.service.InventoryCommentService;
import com.backend.web.dto.Generic.ResponseDTO;
import com.backend.web.dto.InventoryComment.InventoryCommentDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/inventoryComment")
public class InventoryCommentController {

    @Autowired
    private InventoryCommentService inventoryCommentService;

    @PostMapping("/create")
    public ResponseEntity<ResponseDTO> createInventoryCategory(@RequestBody InventoryCommentDTO inventoryCommentDTO) throws Exception {
        return ResponseEntity.ok(ResponseDTO
                .builder()
                .responseDTO(this.inventoryCommentService.createInventoryComment(inventoryCommentDTO))
                .build());
    }

    @GetMapping("/{idInventory}")
    public ResponseEntity<ResponseDTO> getInventoryById(@PathVariable Integer idInventory) throws Exception {
        return ResponseEntity.ok(ResponseDTO
                .builder()
                .responseDTO(this.inventoryCommentService.getInventoryCommentByIdInventoryComment(idInventory))
                .build());
    }

    @PutMapping("/update")
    public ResponseEntity<ResponseDTO> updateCategory(
            @RequestBody InventoryCommentDTO inventoryCommentDTO,
            @RequestParam Integer idCategory
    ) throws Exception {
        return ResponseEntity.ok(ResponseDTO
                .builder()
                .responseDTO(this.inventoryCommentService.updateInventoryComment(inventoryCommentDTO, idCategory))
                .build());
    }
}
