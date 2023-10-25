package com.backend.domain.repository;

import com.backend.domain.entity.Inventory;
import com.backend.domain.entity.InventoryCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InventoryCategoryRepository extends JpaRepository<InventoryCategory, Integer> {
    InventoryCategory findByDescription(String description);
}
