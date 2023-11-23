package com.backend.domain.service;

import com.backend.web.dto.CategoryImage.CategoryImageDTO;
import com.backend.web.dto.CategoryImage.GetCategoryImageDTO;

public interface CategoryImageService {

    GetCategoryImageDTO getCategoryImageByIdCategory(Integer idCategory);

    GetCategoryImageDTO createCategoryImage(CategoryImageDTO categoryImageDTO);

}
