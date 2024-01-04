package com.backend.domain.service.Impl;

import com.backend.domain.entity.CategoryImage;
import com.backend.domain.repository.CategoryImageRepository;
import com.backend.domain.service.CategoryImageService;
import com.backend.web.dto.CategoryImage.CategoryImageDTO;
import com.backend.web.dto.CategoryImage.GetCategoryImageDTO;
import com.backend.web.dto.InventoryCategory.InventoryCategoryDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.Base64;
import java.util.Date;

@Service
@Slf4j
public class CategoryImageServiceImpl implements CategoryImageService {

    @Autowired
    private CategoryImageRepository categoryImageRepository;

    @Autowired
    private ImageService imageService;

    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public GetCategoryImageDTO getCategoryImageByIdCategory(Integer idCategory) {

        CategoryImage image = this.categoryImageRepository.getCategoryImageByIdCategory(idCategory);

        return this.objectMapper.convertValue(image
                , GetCategoryImageDTO.class);
    }

    @Override
    public GetCategoryImageDTO createCategoryImage(CategoryImageDTO categoryImageDTO, MultipartFile imageCategory) throws IOException {
        CategoryImage categoryImage = this.objectMapper.convertValue(categoryImageDTO, CategoryImage.class);
        if (imageCategory != null) {
            categoryImage.setImage(this.imageService.storeImage(imageCategory,"category-images"));
        }
        categoryImage.setHighDate(new Date());
        CategoryImage categoryImageResult = this.categoryImageRepository.save(categoryImage);
        return this.objectMapper.convertValue(categoryImageResult, GetCategoryImageDTO.class);
    }

    @Override
    public GetCategoryImageDTO updateCategoryImage(Integer categoryImageId, MultipartFile imageCategory) throws IOException {
        CategoryImage categoryImage = this.categoryImageRepository.getCategoryImageByIdCategory(categoryImageId);
        if (imageCategory != null && !imageCategory.isEmpty()) {
            categoryImage.setImage(this.imageService.storeImage(imageCategory,"category-images"));
        }
        CategoryImage updatedCategoryImage = this.categoryImageRepository.save(categoryImage);
        return this.objectMapper.convertValue(updatedCategoryImage, GetCategoryImageDTO.class);
    }

}
