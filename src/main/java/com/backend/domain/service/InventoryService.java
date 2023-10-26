package com.backend.domain.service;

import com.backend.domain.exception.DataNotFound;
import com.backend.web.dto.Inventory.GetInventoryDTO;
import com.backend.web.dto.Inventory.InventoryDTO;

public interface InventoryService {

    GetInventoryDTO getInventoryByIdInventory(Integer idInventory) throws Exception;

    GetInventoryDTO getInventoryByName(String name) throws DataNotFound;

    GetInventoryDTO createInventory(InventoryDTO inventoryDTO) throws Exception;

}
