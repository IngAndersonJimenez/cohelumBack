package com.backend.web.controller;

import com.backend.domain.service.Impl.InventoryCategoryServiceImpl;
import com.backend.web.dto.CategoryImage.CategoryImageDTO;
import com.backend.web.dto.Generic.ResponseDTO;
import com.backend.web.dto.InventoryCategory.InventoryCategoryDTO;
import com.backend.web.dto.InventoryCategory.RequestCreateCategoryAndImageDTO;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Api(tags = "InventoryCategory")
@RestController
@RequestMapping("/api/v1/inventoryCategory")
@CrossOrigin
public class InventoryCategoryController {

    @Autowired
    private InventoryCategoryServiceImpl inventoryCategoryService;

    @PostMapping("/create")
    public ResponseEntity<ResponseDTO> createInventoryCategory(@RequestBody InventoryCategoryDTO inventoryCategoryDTO) throws Exception {
        return ResponseEntity.ok(ResponseDTO
                .builder()
                .responseDTO(this.inventoryCategoryService.createInventoryCategory(inventoryCategoryDTO))
                .build());
    }

    @GetMapping("/{idInventory}")
    public ResponseEntity<ResponseDTO> getInventoryById(@PathVariable Integer idInventory) throws Exception {
        return ResponseEntity.ok(ResponseDTO
                .builder()
                .responseDTO(this.inventoryCategoryService.getCategoryById(idInventory))
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
                .responseDTO(this.inventoryCategoryService.updateCategory(inventoryCategoryDTO, idCategory))
                .build());
    }

    @GetMapping("/getAll")
    public ResponseEntity<ResponseDTO> getInventoryByList() throws Exception {
        return ResponseEntity.ok(ResponseDTO
                .builder()
                .responseDTO(this.inventoryCategoryService.getAllCategories())
                .build());
    }

    @PostMapping(value = "/create/categoryImage", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<ResponseDTO> createInventoryCategoryAndImage(
            @RequestParam String descriptionCategory,
            @RequestPart(value = "image", required = false) MultipartFile imageCategory) throws Exception {
        return ResponseEntity.ok(ResponseDTO.builder().responseDTO(
                this.inventoryCategoryService.createInventoryCategoryAndImage(
                        RequestCreateCategoryAndImageDTO.builder()
                                .inventoryCategoryDTO(new InventoryCategoryDTO(descriptionCategory,Boolean.TRUE))
                                .build(), imageCategory)).build());
    }

    @GetMapping("/getCategoryAll")
    public ResponseEntity<ResponseDTO> getCategoryAll() throws Exception {
        return ResponseEntity.ok(ResponseDTO
                .builder()
                .responseDTO(this.inventoryCategoryService.getCategoryAll())
                .build());
    }


}
