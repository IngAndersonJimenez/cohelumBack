package com.backend.web.controller;

import com.backend.domain.service.InventorySubCategoryService;
import com.backend.web.dto.Generic.ResponseDTO;
import com.backend.web.dto.InventoryCategory.InventoryCategoryDTO;
import com.backend.web.dto.InventorySubCategory.InventorySubCategoryDTO;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Api(tags = "InventorySubCategory")
@RestController
@RequestMapping("/api/v1/inventorySubCategory")
@CrossOrigin
public class InventorySubCategoryController {

    @Autowired
    private InventorySubCategoryService inventorySubCategoryService;

    @GetMapping("/{idCategory}")
    public ResponseEntity<ResponseDTO> getInventorySubCategoryByIdSubCategory(@PathVariable Integer idCategory) throws Exception {
        return ResponseEntity.ok(ResponseDTO
                .builder()
                .responseDTO(this.inventorySubCategoryService.getInventorySubCategoryByIdCategory(idCategory))
                .build());
    }

    @GetMapping("/getSubCategoryAll")
    public ResponseEntity<ResponseDTO> getAllSubCategories() throws Exception {
        return ResponseEntity.ok(ResponseDTO
                .builder()
                .responseDTO(this.inventorySubCategoryService.getAllSubCategories())
                .build());
    }

    @PostMapping("/create")
    public ResponseEntity<ResponseDTO> createInventorySubCategory(@RequestBody InventorySubCategoryDTO inventorySubCategoryDTO) throws Exception {
        return ResponseEntity.ok(ResponseDTO
                .builder()
                .responseDTO(this.inventorySubCategoryService.createInventorySubCategory(inventorySubCategoryDTO))
                .build());
    }

    @Operation(summary = "Update InventorySubCategory", description = "Return InventorySubCategory updated")
    @PutMapping("/update")
    public ResponseEntity<ResponseDTO> updateCategory(
            @RequestBody InventorySubCategoryDTO inventorySubCategoryDTO,
            @RequestParam Integer idSubcategory
    ) throws Exception {
        return ResponseEntity.ok(ResponseDTO
                .builder()
                .responseDTO(this.inventorySubCategoryService.updateSubCategory(inventorySubCategoryDTO, idSubcategory))
                .build());
    }

}
