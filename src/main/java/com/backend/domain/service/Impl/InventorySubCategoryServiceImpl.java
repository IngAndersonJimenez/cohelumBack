package com.backend.domain.service.Impl;

import com.backend.domain.entity.InventorySubCategory;
import com.backend.domain.repository.InventorySubCategoryRepository;
import com.backend.domain.service.InventorySubCategoryService;
import com.backend.web.dto.InventorySubCategory.GetInventorySubCategoryDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class InventorySubCategoryServiceImpl implements InventorySubCategoryService {

    @Autowired
    private InventorySubCategoryRepository inventorySubCategoryRepository;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public List<GetInventorySubCategoryDTO> getInventorySubCategoryByIdCategory(Integer idCategory) {

        List<InventorySubCategory> inventorySubCategories =
                this.inventorySubCategoryRepository.getInventorySubCategoryByIdCategory(idCategory);

        List<GetInventorySubCategoryDTO> getInventorySubCategoryDTOS = new ArrayList<>();

        if (!inventorySubCategories.isEmpty()) {
            for (InventorySubCategory inventorySubCategory : inventorySubCategories) {
                getInventorySubCategoryDTOS.add(
                        this.objectMapper.convertValue(inventorySubCategory, GetInventorySubCategoryDTO.class));
            }
        }

        return getInventorySubCategoryDTOS;
    }

    @Override
    public GetInventorySubCategoryDTO getInventorySubCategoryByIdSubCategory(Integer idSubCategory) {
        InventorySubCategory inventorySubCategory =
                this.inventorySubCategoryRepository.getInventorySubCategoryByIdSubCategory(idSubCategory);
        return this.objectMapper.convertValue(inventorySubCategory, GetInventorySubCategoryDTO.class);
    }
}
