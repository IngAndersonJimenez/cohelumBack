package com.backend.domain.service;

import com.backend.web.dto.CategoryImage.CategoryImageDTO;
import com.backend.web.dto.CategoryImage.GetCategoryImageDTO;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface CategoryImageService {

    GetCategoryImageDTO getCategoryImageByIdCategory(Integer idCategory);

    GetCategoryImageDTO createCategoryImage(CategoryImageDTO categoryImageDTO, MultipartFile imageCategory) throws IOException;

    GetCategoryImageDTO updateCategoryImage(Integer categoryImageId,MultipartFile imageCategory) throws IOException;

}
