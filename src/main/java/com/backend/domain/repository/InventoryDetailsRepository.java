package com.backend.domain.repository;

import com.backend.domain.entity.InventoryDetails;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InventoryDetailsRepository extends CrudRepository<InventoryDetails, Integer> {

    InventoryDetails findByIdInventoryDetails(Integer idDetails);

    InventoryDetails findByCharacteristic(String characteristic);

    InventoryDetails getInventoryDetailsByIdInventory(Integer idInventory);


}
