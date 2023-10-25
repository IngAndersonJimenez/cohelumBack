package com.backend.domain.repository;

import com.backend.domain.entity.Inventory;
import com.backend.domain.entity.InventoryImage;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InventoryImageRepository extends CrudRepository<InventoryImage, Integer> {
}
