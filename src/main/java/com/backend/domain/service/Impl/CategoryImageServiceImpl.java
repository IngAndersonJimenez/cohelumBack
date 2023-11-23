package com.backend.domain.service.Impl;

import com.backend.domain.entity.CategoryImage;
import com.backend.domain.repository.CategoryImageRepository;
import com.backend.domain.service.CategoryImageService;
import com.backend.web.dto.CategoryImage.CategoryImageDTO;
import com.backend.web.dto.CategoryImage.GetCategoryImageDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public GetCategoryImageDTO createCategoryImage(CategoryImageDTO categoryImageDTO) {
        CategoryImage categoryImage = this.categoryImageRepository.save(
                this.objectMapper.convertValue(categoryImageDTO, CategoryImage.class));
        return this.objectMapper.convertValue(categoryImage, GetCategoryImageDTO.class);
    }
}
