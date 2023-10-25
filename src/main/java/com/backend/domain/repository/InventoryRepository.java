package com.backend.domain.repository;

import com.backend.domain.entity.Inventory;
import com.backend.domain.entity.Person;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InventoryRepository extends CrudRepository<Inventory, Integer> {
}
