package com.backend.domain.service;

import com.backend.domain.exception.DataNotFound;
import com.backend.web.dto.Inventory.GetInventoryDTO;
import com.backend.web.dto.Inventory.GetInventoryFullDTO;
import com.backend.web.dto.Inventory.InventoryDTO;
import com.backend.web.dto.Inventory.InventoryFullDTO;

import java.util.List;

public interface InventoryService {

    GetInventoryDTO getInventoryByIdInventory(Integer idInventory) throws Exception;

    GetInventoryDTO getInventoryByName(String name) throws DataNotFound;

    GetInventoryDTO getInventoryByNameAndReference(String name,String reference) throws DataNotFound;

    GetInventoryDTO createInventory(InventoryDTO inventoryDTO) throws Exception;

    GetInventoryDTO updateInventory(InventoryDTO inventoryDTO, Integer inventoryId) throws Exception;

    void createFullInventory(InventoryFullDTO inventoryFullDTO) throws Exception;

    List<GetInventoryFullDTO> getAllInventories() throws Exception;

    GetInventoryFullDTO getInventoryFull(Integer idInventory) throws Exception;

    GetInventoryFullDTO getInventoryFullByName(String nameInventory) throws Exception;

    void updateInventoryFUll(InventoryFullDTO inventoryFullDTO, Integer InventoryId) throws Exception;

    GetInventoryFullDTO getInventoryFullByNameAndReference(String nameInventory,String reference) throws Exception;

}
