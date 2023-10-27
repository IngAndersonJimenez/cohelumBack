package com.backend.web.controller;

import com.backend.domain.service.Impl.InventoryDetailsServiceImpl;
import com.backend.web.dto.Generic.ResponseDTO;
import com.backend.web.dto.InventoryDetails.InventoryDetailsDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1/inventoryDetails")
public class InventoryDetailsController {

    @Autowired
    private InventoryDetailsServiceImpl inventoryDetailsService;

    @PostMapping("/create")
    public ResponseEntity<ResponseDTO> createInventoryCategory(@RequestBody InventoryDetailsDTO inventoryDetailsDTO) throws Exception {
        return ResponseEntity.ok(ResponseDTO
                .builder()
                .responseDTO(this.inventoryDetailsService.createInventoryDetails(inventoryDetailsDTO))
                .build());
    }

    @PutMapping("/update")
    public ResponseEntity<ResponseDTO> updateCategory(
            @RequestBody InventoryDetailsDTO inventoryDetailsDTO,
            @RequestParam Integer idDetails
    ) throws Exception {
        return ResponseEntity.ok(ResponseDTO
                .builder()
                .responseDTO(this.inventoryDetailsService.updateDetails(inventoryDetailsDTO, idDetails))
                .build());
    }

    @GetMapping("/{idInventory}")
    public ResponseEntity<ResponseDTO> getInventoryById(@PathVariable Integer idInventory) throws Exception {
        return ResponseEntity.ok(ResponseDTO
                .builder()
                .responseDTO(this.inventoryDetailsService.getDetailsById(idInventory))
                .build());
    }

}
