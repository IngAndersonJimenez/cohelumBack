package com.backend.web.controller;

import com.backend.domain.service.Impl.InventoryCommentServiceImpl;
import com.backend.web.dto.InventoryComment.GetInventoryCommentDTO;
import com.backend.web.dto.InventoryComment.InventoryCommentDTO;
import com.backend.web.dto.InventoryComment.ResponseInventoryCommentDTO;
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

    @PostMapping ("/update")
    public ResponseEntity<ResponseInventoryCommentDTO> updateInventory(@RequestBody InventoryCommentDTO inventoryCommentDTO, @RequestParam Integer inventoryId) throws Exception {
        GetInventoryCommentDTO updatedInventory = inventoryCommentService.updateInventoryComment(inventoryCommentDTO, inventoryId);
        ResponseInventoryCommentDTO response = ResponseInventoryCommentDTO.builder()
                .getInventoryCommentDTO(updatedInventory)
                .build();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{idInventory}")
    public ResponseEntity<InventoryCommentDTO> getInventoryById(@PathVariable Integer idInventory) throws Exception {
        InventoryCommentDTO inventoryCommentDTO = inventoryCommentService.getInventoryCommentByIdInventoryComment(idInventory);
        InventoryCommentDTO newInventoryDTO = inventoryCommentDTO.toBuilder().build();
        return ResponseEntity.ok(newInventoryDTO);
    }
}
