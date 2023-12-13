package com.backend.domain.service.Impl;


import com.backend.domain.entity.InventoryCategory;
import com.backend.domain.entity.InventorySubCategory;
import com.backend.domain.exception.DataNotFound;
import com.backend.domain.repository.InventorySubCategoryRepository;
import com.backend.domain.service.InventorySubCategoryService;
import com.backend.web.dto.InventoryCategory.GetInventoryCategoryDTO;
import com.backend.web.dto.InventoryCategory.InventoryCategoryDTO;
import com.backend.web.dto.InventorySubCategory.GetInventorySubCategoryDTO;
import com.backend.web.dto.InventorySubCategory.InventorySubCategoryDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

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

    @Override
    public List<GetInventorySubCategoryDTO> getAllSubCategories() throws Exception {
        List<InventorySubCategory> categories = inventorySubCategoryRepository.findAll();

        return categories.stream()
                .map(Subcategory -> objectMapper.convertValue(Subcategory, GetInventorySubCategoryDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public GetInventorySubCategoryDTO createInventorySubCategory(InventorySubCategoryDTO inventorySubCategoryDTO) throws Exception {
        GetInventorySubCategoryDTO getInventorySubCategoryDTO;
        try {
            getInventorySubCategoryDTO = this.getInventorySubCategoryByDescription(inventorySubCategoryDTO.getDescription());
        } catch (DataNotFound dataNotFound) {
            InventorySubCategory inventorySubCategory = this.objectMapper.convertValue(inventorySubCategoryDTO, InventorySubCategory.class);
            inventorySubCategory.setHighDate(new Date());
            getInventorySubCategoryDTO = this.generateStructureResponse(this.inventorySubCategoryRepository.save(inventorySubCategory)
            );
        }

        return getInventorySubCategoryDTO;
    }

    @Override
    public GetInventorySubCategoryDTO getInventorySubCategoryByDescription(String description) throws DataNotFound {
        InventorySubCategory inventorySubCategory = this.inventorySubCategoryRepository.findByDescription(description);
        return this.generateStructureResponse(inventorySubCategory);
    }

    @Override
    public GetInventorySubCategoryDTO updateSubCategory(InventorySubCategoryDTO inventorySubCategoryDTO, Integer idSubCategory) throws Exception {
        InventorySubCategory inventorySubCategory = this.inventorySubCategoryRepository.findById(idSubCategory)
                .orElseThrow(() -> new RuntimeException("SubCategoría no encontrada"));
        inventorySubCategory.setIdCategory(inventorySubCategoryDTO.getIdCategory());
        inventorySubCategory.setActive(inventorySubCategoryDTO.isActive());
        inventorySubCategory.setDescription(inventorySubCategoryDTO.getDescription());
        InventorySubCategory inventorySubCategory1 = this.inventorySubCategoryRepository.save(inventorySubCategory);
        return this.generateStructureResponse(inventorySubCategory1);
    }


    private GetInventorySubCategoryDTO generateStructureResponse(InventorySubCategory inventorySubCategory) throws DataNotFound {
        GetInventorySubCategoryDTO getInventorySubCategoryDTO;

        if (inventorySubCategory != null) {
            getInventorySubCategoryDTO = this.objectMapper.convertValue(inventorySubCategory, GetInventorySubCategoryDTO.class);
        } else {
            throw new DataNotFound("El producto no existe en el inventario. ");
        }
        return getInventorySubCategoryDTO;
    }
}
