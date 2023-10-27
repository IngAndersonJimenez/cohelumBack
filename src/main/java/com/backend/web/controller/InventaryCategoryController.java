package com.backend.web.controller;

import com.backend.domain.service.Impl.InventoryCategoryServiceImpl;
import com.backend.web.dto.Generic.ResponseDTO;
import com.backend.web.dto.InventoryCategory.InventoryCategoryDTO;
import com.backend.web.dto.InventoryCategory.ResponseCreateDTO;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Api(tags = "InventoryCategory")
@RestController
@RequestMapping("/api/v1/inventoryCategory")
public class InventaryCategoryController {

    @Autowired
    private InventoryCategoryServiceImpl inventaryCategoryService;

    @PostMapping("/create")
    public ResponseEntity<ResponseCreateDTO> createInventoryCategory(@RequestBody InventoryCategoryDTO InventoryCategoryDTO) throws Exception {
        return ResponseEntity.ok(ResponseCreateDTO
                .builder()
                .getInventoryCategoryDTO(this.inventaryCategoryService.createInventoryCategory(InventoryCategoryDTO))
                .build());
    }

    @GetMapping("/{idInventory}")
    public ResponseEntity<ResponseDTO> getInventoryById(@PathVariable Integer idInventory) throws Exception {
        return ResponseEntity.ok(ResponseDTO
                .builder()
                .responseDTO(this.inventaryCategoryService.getCategoryById(idInventory))
                .build());
    }

    @Operation(summary = "Update InventoryCategory", description = "Return InventoryCategory updated")
    @PutMapping("/update")
    public ResponseEntity<ResponseCreateDTO> updateCategory(
            @RequestBody InventoryCategoryDTO inventoryCategoryDTO,
            @RequestParam Integer idCategory
    ) throws Exception {
        return ResponseEntity.ok(ResponseCreateDTO
                .builder()
                .getInventoryCategoryDTO(this.inventaryCategoryService.updateCategory(inventoryCategoryDTO, idCategory))
                .build());
    }

}
