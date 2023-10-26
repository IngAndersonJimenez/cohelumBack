package com.backend.web.controller;


import com.backend.domain.entity.InventoryCategory;
import com.backend.domain.service.Impl.InventoryCategoryServiceImpl;
import com.backend.web.dto.InventoryCategory.InventoryCategoryDTO;
import com.backend.web.dto.InventoryCategory.ResponseCreateDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/inventoryCategory")
public class InventaryCategoryController {

    @Autowired
    private InventoryCategoryServiceImpl inventaryCategoryService;

    @PostMapping("/create")
    public ResponseEntity<ResponseCreateDTO> createCategory(@RequestBody InventoryCategoryDTO inventoryCategoryDTO) {
        return ResponseEntity.ok(ResponseCreateDTO.builder()
                .message(this.inventaryCategoryService.createCategory(inventoryCategoryDTO))
                .build());
    }

    @PutMapping("/{categoryId}")
    public ResponseEntity<Object> updateCategory(@PathVariable Integer categoryId, @RequestBody InventoryCategory updatedCategory) {
        return inventaryCategoryService.updateCategory(categoryId, updatedCategory);



    }

    @GetMapping
    public List<InventoryCategory> getAllCategories() {
        return inventaryCategoryService.getAllCategories();
    }

    @GetMapping("/{categoryId}")
    public ResponseEntity<Object> getCategoryById(@PathVariable Integer categoryId) {
        return inventaryCategoryService.getCategoryById(categoryId);
    }
}
