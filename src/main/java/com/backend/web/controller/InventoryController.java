package com.backend.web.controller;

import com.backend.domain.service.InventoryService;
import com.backend.web.dto.Inventory.InventoryDTO;
import com.backend.web.dto.Inventory.ResponseInventoryDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/inventory")
public class InventoryController {

    @Autowired
    private InventoryService inventoryService;

    @PostMapping("/create")
    public ResponseEntity<ResponseInventoryDTO> createInventory(@RequestBody InventoryDTO inventoryDTO) throws Exception {
        return ResponseEntity.ok(ResponseInventoryDTO
                .builder()
                .getInventoryDTO(this.inventoryService.createInventory(inventoryDTO))
                .build());
    }


}
