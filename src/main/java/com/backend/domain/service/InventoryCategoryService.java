package com.backend.domain.service;

import com.backend.domain.exception.DataNotFound;
import com.backend.web.dto.InventoryCategory.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface InventoryCategoryService {

    GetInventoryCategoryDTO createInventoryCategory(InventoryCategoryDTO inventoryCategoryDTO) throws Exception;

    GetInventoryCategoryDTO updateCategory(InventoryCategoryDTO inventoryCategoryDTO, Integer idCategory) throws Exception;

    GetInventoryCategoryDTO getCategoryById(Integer categoryId) throws Exception;

    GetInventoryCategoryDTO getInventoryCategoryByDescription(String description) throws DataNotFound;

    List<GetInventoryCategoryDTO> getAllCategories() throws Exception;

    GetInventoryCategoryDTO createInventoryCategoryAndImage(RequestCreateCategoryAndImageDTO requestCreateCategoryAndImageDTO, MultipartFile file) throws Exception;

    ResponseCategoryFullDTO getCategoryAll() throws Exception;
}
