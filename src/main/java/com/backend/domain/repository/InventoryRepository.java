package com.backend.domain.repository;

import com.backend.domain.entity.Inventory;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InventoryRepository extends CrudRepository<Inventory, Integer> {

    Inventory findOneInventoryByIdInventory(Integer idInventory);

    Inventory findOneInventoryByName(String name);



}
