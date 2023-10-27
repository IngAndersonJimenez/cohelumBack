package com.backend.web.controller;

import com.backend.domain.service.Impl.InventoryCommentServiceImpl;
import com.backend.web.dto.InventaryComment.InventoryCommentDTO;
import com.backend.web.dto.InventaryComment.ResponseInventoryCommentDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/inventoryComment")
public class InventoryCommentController {

    @Autowired
    private InventoryCommentServiceImpl inventoryCommentService;

    @PostMapping("/create")
    public ResponseEntity<ResponseInventoryCommentDTO> createInventoryCategory(@RequestBody InventoryCommentDTO inventoryCommentDTO) throws Exception {
        return ResponseEntity.ok(ResponseInventoryCommentDTO
                .builder()
                .getInventoryCommentDTO(this.inventoryCommentService.createInventoryComment(inventoryCommentDTO))
                .build());
    }

    @GetMapping("/{idInventory}")
    public ResponseEntity<InventoryCommentDTO> getInventoryById(@PathVariable Integer idInventory) throws Exception {
        InventoryCommentDTO inventoryCommentDTO = inventoryCommentService.getInventoryCommentByIdInventoryComment(idInventory);
        InventoryCommentDTO newInventoryDTO = inventoryCommentDTO.toBuilder().build();
        return ResponseEntity.ok(newInventoryDTO);
    }
}
