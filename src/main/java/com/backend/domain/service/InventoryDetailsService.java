package com.backend.domain.service;

import com.backend.domain.exception.DataNotFound;
import com.backend.web.dto.InventoryDetails.GetInventoryDetailsDTO;
import com.backend.web.dto.InventoryDetails.InventoryDetailsDTO;

public interface InventoryDetailsService {

    GetInventoryDetailsDTO createInventoryDetails(InventoryDetailsDTO inventoryDetailsDTO) throws Exception;

    GetInventoryDetailsDTO updateDetails(InventoryDetailsDTO inventoryDetailsDTO, Integer idDetails) throws Exception;

    GetInventoryDetailsDTO getDetailsById(Integer categoryId) throws Exception;

    GetInventoryDetailsDTO getInventoryDetailsByCharacteristic(String characteristic) throws DataNotFound;

    GetInventoryDetailsDTO getInventoryDetailsByIdInventory(Integer idInventory);


}
