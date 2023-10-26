package com.backend.domain.service.Impl;

import com.backend.domain.entity.Inventory;
import com.backend.domain.exception.DataNotFound;
import com.backend.domain.repository.InventoryRepository;
import com.backend.domain.service.InventoryService;
import com.backend.web.dto.Inventory.GetInventoryDTO;
import com.backend.web.dto.Inventory.InventoryDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;


@Service
public class InventoryServiceImpl implements InventoryService {

    @Autowired
    private InventoryRepository inventoryRepository;

    private ObjectMapper objectMapper = new ObjectMapper();


    @Override
    public GetInventoryDTO getInventoryByIdInventory(Integer idInventory) throws Exception {
        Inventory inventory = this.inventoryRepository.findOneInventoryByIdInventory(idInventory);
        return this.generateStructureResponse(inventory);
    }

    @Override
    public GetInventoryDTO getInventoryByName(String name) throws DataNotFound {
        Inventory inventory = this.inventoryRepository.findOneInventoryByName(name);
        return this.generateStructureResponse(inventory);
    }

    @Override
    public GetInventoryDTO createInventory(InventoryDTO inventoryDTO) throws Exception {

        GetInventoryDTO getInventoryDTO;

        try {
            getInventoryDTO = this.getInventoryByName(inventoryDTO.getName());
        } catch (DataNotFound dataNotFound) {
            Inventory inventory = this.objectMapper.convertValue(inventoryDTO, Inventory.class);
            inventory.setHighDate(new Date());
            getInventoryDTO = this.generateStructureResponse(
                    this.inventoryRepository.save(this.objectMapper.convertValue(inventoryDTO, Inventory.class))
            );
        }

        return getInventoryDTO;
    }

    @Override
    public GetInventoryDTO updateInventory(InventoryDTO inventoryDTO, Integer inventoryId) throws Exception {
        GetInventoryDTO getInventoryDTO;

        try {
            getInventoryDTO = this.getInventoryByIdInventory(inventoryId);
            Inventory existingInventory = this.objectMapper.convertValue(getInventoryDTO, Inventory.class);
            existingInventory.setName(existingInventory.getName());
            Inventory updatedInventory = this.inventoryRepository.save(existingInventory);
            getInventoryDTO = this.generateStructureResponse(updatedInventory);
        } catch (DataNotFound dataNotFound) {
            throw new Exception("El inventario no existe y no se puede actualizar.");
        }

        return getInventoryDTO;
    }







    private GetInventoryDTO generateStructureResponse(Inventory inventory) throws DataNotFound {
        GetInventoryDTO getInventoryDTO;

        if (inventory != null) {
            getInventoryDTO = this.objectMapper.convertValue(inventory, GetInventoryDTO.class);
        } else {
            throw new DataNotFound("El producto no existe en el inventario. ");
        }
        return getInventoryDTO;
    }


}
