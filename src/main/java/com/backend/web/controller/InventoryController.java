package com.backend.web.controller;

import com.backend.domain.service.InventoryService;
import com.backend.web.dto.Generic.ResponseDTO;
import com.backend.web.dto.Inventory.InventoryDTO;
import com.backend.web.dto.Inventory.InventoryFullDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/inventory")
@CrossOrigin(origins = "http://localhost:4200")
public class InventoryController {

    @Autowired
    private InventoryService inventoryService;

    @PostMapping("/create")
    public ResponseEntity<ResponseDTO> createInventory(@RequestBody InventoryDTO inventoryDTO) throws Exception {
        return ResponseEntity.ok(ResponseDTO
                .builder()
                .responseDTO(this.inventoryService.createInventory(inventoryDTO))
                .build());
    }

    @PutMapping("/update")
    public ResponseEntity<ResponseDTO> updateCategory(
            @RequestBody InventoryDTO inventoryDTO,
            @RequestParam Integer idDetails
    ) throws Exception {
        return ResponseEntity.ok(ResponseDTO
                .builder()
                .responseDTO(this.inventoryService.updateInventory(inventoryDTO, idDetails))
                .build());
    }

    @GetMapping("/{idInventory}")
    public ResponseEntity<ResponseDTO> getInventoryById(@PathVariable Integer idInventory) throws Exception {
        return ResponseEntity.ok(ResponseDTO
                .builder()
                .responseDTO(this.inventoryService.getInventoryByIdInventory(idInventory))
                .build());
    }


    @PostMapping("/createFull")
    public ResponseEntity<ResponseDTO> createFullInventory(@ModelAttribute InventoryFullDTO inventoryFullDTO) throws Exception {
            inventoryService.createFullInventory(inventoryFullDTO);
            ResponseDTO responseDTO = ResponseDTO.builder()
                    .build();
            return ResponseEntity.ok(responseDTO);

    }

    @GetMapping
    public ResponseEntity<ResponseDTO> getInventoryByList() throws Exception {
        return ResponseEntity.ok(ResponseDTO
                .builder()
                .responseDTO(this.inventoryService.getAllInventories())
                .build());
    }

    @GetMapping("/full/{idInventory}")
    public ResponseEntity<ResponseDTO> getInventoryById(@PathVariable String idInventory) throws Exception {
        return ResponseEntity.ok(ResponseDTO
                .builder()
                .responseDTO(this.inventoryService.getInventoryFull(Integer.valueOf(idInventory)))
                .build());
    }

    @GetMapping("/searchByName/{name}")
    public ResponseEntity<ResponseDTO> getInventoryByName(@PathVariable String name) throws Exception {
        return ResponseEntity.ok(ResponseDTO
                .builder()
                .responseDTO(this.inventoryService.getInventoryFullByName(name))
                .build());
    }


}
