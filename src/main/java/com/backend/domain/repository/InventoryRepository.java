package com.backend.domain.repository;

import com.backend.domain.entity.Inventory;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InventoryRepository extends CrudRepository<Inventory, Integer>, JpaSpecificationExecutor<Inventory> {

    Inventory findOneInventoryByIdInventory(Integer idInventory);

    Inventory findOneInventoryByName(String name);

    List<Inventory> findAll();

}
