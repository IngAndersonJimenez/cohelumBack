package com.backend.domain.service;

import com.backend.domain.exception.DataNotFound;
import com.backend.web.dto.InventoryCategory.GetInventoryCategoryDTO;
import com.backend.web.dto.InventoryCategory.InventoryCategoryDTO;
import com.backend.web.dto.InventorySubCategory.GetInventorySubCategoryDTO;
import com.backend.web.dto.InventorySubCategory.InventorySubCategoryDTO;

import java.util.List;

public interface InventorySubCategoryService {

    List<GetInventorySubCategoryDTO> getInventorySubCategoryByIdCategory(Integer idCategory);

    GetInventorySubCategoryDTO getInventorySubCategoryByIdSubCategory(Integer idSubCategory);

    List<GetInventorySubCategoryDTO> getAllSubCategories() throws Exception;

    GetInventorySubCategoryDTO createInventorySubCategory(InventorySubCategoryDTO inventorySubCategoryDTO) throws Exception;

    GetInventorySubCategoryDTO getInventorySubCategoryByDescription(String description) throws DataNotFound;

    GetInventorySubCategoryDTO updateSubCategory(InventorySubCategoryDTO inventorySubCategoryDTO, Integer idSubCategory) throws Exception;

}
