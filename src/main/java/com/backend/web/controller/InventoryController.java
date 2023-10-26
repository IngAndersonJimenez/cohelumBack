package com.backend.web.controller;

import com.backend.domain.service.InventoryService;
import com.backend.web.dto.Inventory.GetInventoryDTO;
import com.backend.web.dto.Inventory.InventoryDTO;
import com.backend.web.dto.Inventory.ResponseInventoryDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping ("/update")
    public ResponseEntity<ResponseInventoryDTO> updateInventory(@RequestBody InventoryDTO inventoryDTO, @RequestParam Integer inventoryId) throws Exception {
        GetInventoryDTO updatedInventory = inventoryService.updateInventory(inventoryDTO, inventoryId);
        ResponseInventoryDTO response = ResponseInventoryDTO.builder()
                .getInventoryDTO(updatedInventory)
                .build();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{idInventory}")
    public ResponseEntity<InventoryDTO> getInventoryById(@PathVariable Integer idInventory) throws Exception {
        InventoryDTO inventoryDTO = inventoryService.getInventoryByIdInventory(idInventory);
        InventoryDTO newInventoryDTO = inventoryDTO.toBuilder().build();
        return ResponseEntity.ok(newInventoryDTO);
    }


}
