package com.backend.domain.repository;

import com.backend.domain.entity.InventoryCategory;
import com.backend.domain.entity.InventorySubCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InventorySubCategoryRepository extends JpaRepository<InventorySubCategory, Integer> {

    List<InventorySubCategory> getInventorySubCategoryByIdCategory(Integer idCategory);

    InventorySubCategory getInventorySubCategoryByIdSubCategory(Integer idSubCategory);

    InventorySubCategory findByDescription(String description);

}
