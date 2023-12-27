package com.backend.domain.repository;

import com.backend.domain.entity.InventoryCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InventoryCategoryRepository extends JpaRepository<InventoryCategory, Integer> {
    InventoryCategory findByIdCategory(Integer idCategory);
    InventoryCategory findByDescription(String description);
}
