package com.backend.domain.service;

import com.backend.web.dto.InventorySubCategory.GetInventorySubCategoryDTO;

import java.util.List;

public interface InventorySubCategoryService {

    List<GetInventorySubCategoryDTO> getInventorySubCategoryByIdCategory(Integer idCategory);

    GetInventorySubCategoryDTO getInventorySubCategoryByIdSubCategory(Integer idSubCategory);

}
