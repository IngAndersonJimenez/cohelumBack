package com.backend.domain.service;

import com.backend.domain.entity.InventoryCategory;
import com.backend.web.dto.InventoryCategory.InventoryCategoryDTO;

public interface InventoryCategoryService {

    String createCategory(InventoryCategoryDTO inventoryCategoryDTO);

}
