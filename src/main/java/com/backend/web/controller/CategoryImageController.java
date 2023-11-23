package com.backend.web.controller;

import com.backend.domain.service.CategoryImageService;
import com.backend.web.dto.CategoryImage.CategoryImageDTO;
import com.backend.web.dto.Generic.ResponseDTO;
import com.backend.web.dto.InventoryCategory.InventoryCategoryDTO;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Api(tags = "CategoryImage")
@RestController
@RequestMapping("/api/v1/categoryImage")
@CrossOrigin
public class CategoryImageController {

    @Autowired
    private CategoryImageService categoryImageService;

    @GetMapping("/{idCategory}")
    public ResponseEntity<ResponseDTO> getCategoryImageByIdCategory(@PathVariable Integer idCategory) throws Exception {
        return ResponseEntity.ok(ResponseDTO
                .builder()
                .responseDTO(this.categoryImageService.getCategoryImageByIdCategory(idCategory))
                .build());
    }

    @PostMapping("/create")
    public ResponseEntity<ResponseDTO> createCategoryImage(@ModelAttribute CategoryImageDTO categoryImageDTO) throws Exception {
        return ResponseEntity.ok(ResponseDTO
                .builder()
                .responseDTO(this.categoryImageService.createCategoryImage(categoryImageDTO))
                .build());
    }

}
