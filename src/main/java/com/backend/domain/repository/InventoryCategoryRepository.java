package com.backend.domain.repository;

import com.backend.domain.entity.Inventory;
import com.backend.domain.entity.InventoryCategory;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InventoryCategoryRepository extends CrudRepository<InventoryCategory, Integer> {
}
