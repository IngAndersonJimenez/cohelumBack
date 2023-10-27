package com.backend.domain.service.Impl;

import com.backend.domain.entity.InventoryCategory;
import com.backend.domain.exception.DataNotFound;
import com.backend.domain.repository.InventoryCategoryRepository;
import com.backend.domain.service.InventoryCategoryService;
import com.backend.web.dto.InventoryCategory.GetInventoryCategoryDTO;
import com.backend.web.dto.InventoryCategory.InventoryCategoryDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;;
import org.springframework.stereotype.Service;

import java.util.Date;


@Service
public class InventoryCategoryServiceImpl implements InventoryCategoryService {

    @Autowired
    private InventoryCategoryRepository inventoryCategoryRepository;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public GetInventoryCategoryDTO getInventoryCategoryByDescription(String description) throws DataNotFound {
        InventoryCategory inventoryCategory = this.inventoryCategoryRepository.findByDescription(description);
        return this.generateStructureResponse(inventoryCategory);
    }


    @Override
    public GetInventoryCategoryDTO createInventoryCategory(InventoryCategoryDTO inventoryCategoryDTO) throws Exception {

        GetInventoryCategoryDTO getInventoryCategoryDTO;

        try {
            getInventoryCategoryDTO = this.getInventoryCategoryByDescription(inventoryCategoryDTO.getDescription());
        } catch (DataNotFound dataNotFound) {
            InventoryCategory inventoryCategory = this.objectMapper.convertValue(inventoryCategoryDTO, InventoryCategory.class);
            inventoryCategory.setHighDate(new Date());
            getInventoryCategoryDTO = this.generateStructureResponse(this.inventoryCategoryRepository.save(inventoryCategory)
            );
        }

        return getInventoryCategoryDTO;
    }

    @Override
    public GetInventoryCategoryDTO updateCategory(InventoryCategoryDTO inventoryCategoryDTO, Integer idCategory) throws Exception {
        GetInventoryCategoryDTO getInventoryCategoryDTO = this.getCategoryById(idCategory);
        InventoryCategory inventoryCategoryUpdated = this.inventoryCategoryRepository.save(this.validationObject(getInventoryCategoryDTO));
        return this.generateStructureResponse(inventoryCategoryUpdated);
    }

    @Override
    public GetInventoryCategoryDTO getCategoryById(Integer categoryId) throws Exception {
        InventoryCategory inventoryCategory = inventoryCategoryRepository.findByIdCategory(categoryId);
        return this.generateStructureResponse(inventoryCategory);
    }


    private GetInventoryCategoryDTO generateStructureResponse(InventoryCategory inventoryCategory) throws DataNotFound {
        GetInventoryCategoryDTO getInventoryCategoryDTO;

        if (inventoryCategory != null) {
            getInventoryCategoryDTO = this.objectMapper.convertValue(inventoryCategory, GetInventoryCategoryDTO.class);
        } else {
            throw new DataNotFound("El producto no existe en el inventario. ");
        }
        return getInventoryCategoryDTO;
    }

    private InventoryCategory validationObject(GetInventoryCategoryDTO getInventoryCategoryDTO) {

        InventoryCategory inventoryCategory = new InventoryCategory();
        inventoryCategory.setIdCategory(getInventoryCategoryDTO.getIdCategory());

        if (getInventoryCategoryDTO.getDescription() != null) {
            inventoryCategory.setDescription(getInventoryCategoryDTO.getDescription());
        }

        inventoryCategory.setModificationDate(new Date());
        return inventoryCategory;
    }


}
