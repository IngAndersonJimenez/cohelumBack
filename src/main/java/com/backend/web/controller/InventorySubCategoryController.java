package com.backend.web.controller;

import com.backend.domain.service.InventorySubCategoryService;
import com.backend.web.dto.Generic.ResponseDTO;
import io.swagger.annotations.Api;
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

}
