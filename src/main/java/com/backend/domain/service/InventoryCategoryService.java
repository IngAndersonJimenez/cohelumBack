package com.backend.domain.service;

import com.backend.domain.entity.InventoryCategory;
import com.backend.web.dto.InventoryCategory.InventoryCategoryDTO;

import java.util.List;

public interface InventoryCategoryService {

    String createCategory(InventoryCategoryDTO inventoryCategoryDTO);

    String updateCategory(String description,InventoryCategoryDTO inventoryCategoryDTO);

    InventoryCategoryDTO getCategoryById(Integer categoryId);
}
