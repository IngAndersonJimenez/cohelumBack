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
                    this.inventoryRepository.save(inventory)
            );
        }

        return getInventoryDTO;
    }

    @Override
    public GetInventoryDTO updateInventory(InventoryDTO inventoryDTO, Integer inventoryId) throws Exception {
        GetInventoryDTO getInventoryDTO = this.getInventoryByIdInventory(inventoryId);
        Inventory inventoryUpdated = this.inventoryRepository.save(this.validationObject(getInventoryDTO));
        return this.generateStructureResponse(inventoryUpdated);
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

    private Inventory validationObject(GetInventoryDTO getInventoryDTO) {

        Inventory inventory = new Inventory();
        inventory.setIdInventory(getInventoryDTO.getIdInventory());

        if (getInventoryDTO.getName() != null) {
            inventory.setName(getInventoryDTO.getName());
        }

        if (getInventoryDTO.getPrice() != null) {
            inventory.setPrice(getInventoryDTO.getPrice());
        }

        if (getInventoryDTO.getUnitsAvailable() != null) {
            inventory.setUnitsAvailable(getInventoryDTO.getUnitsAvailable());
        }

        inventory.setActive(getInventoryDTO.isActive());

        inventory.setModificationDate(new Date());
        return inventory;
    }


}
