package com.backend.web.controller;

import com.backend.domain.service.CategoryImageService;
import com.backend.web.dto.CategoryImage.CategoryImageDTO;
import com.backend.web.dto.Generic.ResponseDTO;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Api(tags = "CategoryImage")
@RestController
@RequestMapping("/api/v1/categoryImage")
@CrossOrigin
public class CategoryImageController {

    @Autowired
    private CategoryImageService categoryImageService;

    @GetMapping("/{idCategory}")
    public ResponseEntity<ResponseDTO> getCategoryImageByIdCategory(@PathVariable Integer idCategory) throws Exception {
        return ResponseEntity.ok(ResponseDTO.builder().responseDTO(
                this.categoryImageService.getCategoryImageByIdCategory(idCategory)).build());
    }

    @PostMapping(value = "/create", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<ResponseDTO> createCategoryImage(
            @RequestParam Boolean active,
            @RequestParam Integer idCategory,
            @RequestPart(value = "image", required = false) MultipartFile imageCategory) throws Exception {
        CategoryImageDTO categoryImageDTO = new CategoryImageDTO(active, idCategory, null);
        return ResponseEntity.ok(ResponseDTO.builder().responseDTO(
                this.categoryImageService.createCategoryImage(categoryImageDTO, imageCategory)).build());
    }

    @PutMapping(value = "/update/{idCategory}", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<ResponseDTO> updateCategoryImage(
            @PathVariable Integer idCategory,
            @RequestPart(value = "image", required = false) MultipartFile imageCategory) throws Exception {
        return ResponseEntity.ok(ResponseDTO.builder().responseDTO(
                this.categoryImageService.updateCategoryImage(idCategory, imageCategory)).build());
    }

}
