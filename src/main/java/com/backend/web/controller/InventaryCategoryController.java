package com.backend.web.controller;


import com.backend.domain.entity.InventoryCategory;
import com.backend.domain.service.Impl.InventaryCategoryServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/inventory")
public class InventaryCategoryController {

    @Autowired
    private InventaryCategoryServiceImpl inventaryCategoryService;

    @PostMapping("/create")
    public ResponseEntity<Object> createCategory(@RequestBody InventoryCategory category) {
        return inventaryCategoryService.createCategory(category);
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
