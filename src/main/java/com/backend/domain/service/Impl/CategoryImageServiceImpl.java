package com.backend.domain.service.Impl;

import com.backend.domain.entity.CategoryImage;
import com.backend.domain.repository.CategoryImageRepository;
import com.backend.domain.service.CategoryImageService;
import com.backend.web.dto.CategoryImage.CategoryImageDTO;
import com.backend.web.dto.CategoryImage.GetCategoryImageDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;
import java.util.Date;

@Service
public class CategoryImageServiceImpl implements CategoryImageService {

    @Autowired
    private CategoryImageRepository categoryImageRepository;

    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public GetCategoryImageDTO getCategoryImageByIdCategory(Integer idCategory) {
        return this.objectMapper.convertValue(
                this.categoryImageRepository.getCategoryImageByIdCategory(idCategory), GetCategoryImageDTO.class);
    }

    @Override
    public GetCategoryImageDTO createCategoryImage(CategoryImageDTO categoryImageDTO, MultipartFile imageCategory) throws IOException {
        CategoryImage categoryImage = this.objectMapper.convertValue(categoryImageDTO, CategoryImage.class);
        if (imageCategory != null){
            categoryImage.setPhoto(Base64.getEncoder().encodeToString(imageCategory.getBytes()));
        }
        categoryImage.setHighDate(new Date());
        CategoryImage categoryImageResult = this.categoryImageRepository.save(categoryImage);
        return this.objectMapper.convertValue(categoryImageResult, GetCategoryImageDTO.class);
    }
}
