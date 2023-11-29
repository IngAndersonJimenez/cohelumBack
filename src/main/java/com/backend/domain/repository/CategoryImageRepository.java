package com.backend.domain.repository;

import com.backend.domain.entity.CategoryImage;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryImageRepository extends CrudRepository<CategoryImage, Integer> {

    CategoryImage getCategoryImageByIdCategory(Integer idCategory);

}
