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

    GetInventoryCategoryDTO updateInventoryCategory(InventoryCategoryDTO inventoryCategoryDTO, Integer inventoryId) throws Exception;

    GetInventoryCategoryDTO getCategoryById(Integer categoryId) throws Exception;

    GetInventoryCategoryDTO getInventoryCategoryByDescription(String description) throws DataNotFound;
}
