package com.backend.web.controller;

import com.backend.domain.service.Impl.InventoryCategoryServiceImpl;
import com.backend.web.dto.Generic.ResponseDTO;
import com.backend.web.dto.InventoryCategory.InventoryCategoryDTO;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Api(tags = "InventoryCategory")
@RestController
@RequestMapping("/api/v1/inventoryCategory")
@CrossOrigin
public class InventoryCategoryController {

    @Autowired
    private InventoryCategoryServiceImpl inventaryCategoryService;

    @PostMapping("/create")
    public ResponseEntity<ResponseDTO> createInventoryCategory(@RequestBody InventoryCategoryDTO inventoryCategoryDTO) throws Exception {
        return ResponseEntity.ok(ResponseDTO
                .builder()
                .responseDTO(this.inventaryCategoryService.createInventoryCategory(inventoryCategoryDTO))
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
    public ResponseEntity<ResponseDTO> updateCategory(
            @RequestBody InventoryCategoryDTO inventoryCategoryDTO,
            @RequestParam Integer idCategory
    ) throws Exception {
        return ResponseEntity.ok(ResponseDTO
                .builder()
                .responseDTO(this.inventaryCategoryService.updateCategory(inventoryCategoryDTO, idCategory))
                .build());
    }

    @GetMapping("/getAll")
    public ResponseEntity<ResponseDTO> getInventoryByList() throws Exception {
        return ResponseEntity.ok(ResponseDTO
                .builder()
                .responseDTO(this.inventaryCategoryService.getAllCategories())
                .build());
    }

}
