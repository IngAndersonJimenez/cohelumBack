package com.backend.domain.service.Impl;


import com.backend.domain.entity.InventoryCategory;
import com.backend.domain.repository.InventoryCategoryRepository;
import com.backend.domain.service.InventoryCategoryService;
import com.backend.web.dto.InventoryCategory.InventoryCategoryDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class InventoryCategoryServiceImpl implements InventoryCategoryService {

    @Autowired
    private InventoryCategoryRepository inventoryCategoryRepository;

    private ObjectMapper objectMapper = new ObjectMapper();


    public ResponseEntity<Object> createCategory(InventoryCategory category) {
        InventoryCategory inventoryCategory = inventoryCategoryRepository.findByDescription(category.getDescription());
        ResponseEntity<Object> response;

        if (inventoryCategory != null) {
            response = ResponseEntity.status(HttpStatus.CONFLICT).body("La categoría ya existe");
        } else {
            category.setHighDate(new Date());
            InventoryCategory createdCategory = inventoryCategoryRepository.save(category);
            response = ResponseEntity.status(HttpStatus.CREATED).body(createdCategory);
        }
        return response;
    }


    public ResponseEntity<Object> updateCategory(Integer categoryId, InventoryCategory updatedCategory) {
        Optional<InventoryCategory> existingCategory = inventoryCategoryRepository.findById(categoryId);
        ResponseEntity<Object> response;

        if (existingCategory.isPresent()) {

            InventoryCategory categoryToUpdate = existingCategory.get();
            categoryToUpdate.setDescription(updatedCategory.getDescription());
            categoryToUpdate.setActive(updatedCategory.isActive());

            InventoryCategory updatedCategoryResult = inventoryCategoryRepository.save(categoryToUpdate);

            response = ResponseEntity.ok(updatedCategoryResult);
        } else {
            response = ResponseEntity.status(HttpStatus.NOT_FOUND).body("Categoría no encontrada");
        }

        return response;
    }


    public List<InventoryCategory> getAllCategories() {
        return inventoryCategoryRepository.findAll();
    }

    public ResponseEntity<Object> getCategoryById(Integer categoryId) {
        Optional<InventoryCategory> existingCategory = inventoryCategoryRepository.findById(categoryId);
        ResponseEntity<Object> response;

        if (existingCategory.isPresent()) {
            response = ResponseEntity.status(HttpStatus.OK).body(existingCategory);
        } else {

            response = ResponseEntity.status(HttpStatus.NOT_FOUND).body("Categoría no encontrada");
        }

        return response;
    }

    @Override
    public String createCategory(InventoryCategoryDTO inventoryCategoryDTO) {
        InventoryCategory inventoryCategory = this.objectMapper.convertValue(inventoryCategoryDTO, InventoryCategory.class);
        inventoryCategory.setHighDate(new Date());
        this.inventoryCategoryRepository.save(inventoryCategory);
        return "Se creo la categoria " + inventoryCategoryDTO.getDescription();
    }


}
