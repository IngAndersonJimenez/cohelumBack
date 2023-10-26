package com.backend.domain.service;

import com.backend.domain.entity.InventoryCategory;
import com.backend.domain.exception.DataNotFound;
import com.backend.web.dto.Inventory.GetInventoryDTO;
import com.backend.web.dto.Inventory.InventoryDTO;
import com.backend.web.dto.InventoryCategory.GetInventoryCategoryDTO;
import com.backend.web.dto.InventoryCategory.InventoryCategoryDTO;

import java.util.List;

public interface InventoryCategoryService {

    GetInventoryCategoryDTO createInventoryCategory(InventoryCategoryDTO inventoryCategoryDTO) throws Exception;

    String updateCategory(String description,InventoryCategoryDTO inventoryCategoryDTO);

    GetInventoryCategoryDTO getCategoryById(Integer categoryId) throws Exception;

    GetInventoryCategoryDTO getInventoryCategoryByDescription(String description) throws DataNotFound;
}
