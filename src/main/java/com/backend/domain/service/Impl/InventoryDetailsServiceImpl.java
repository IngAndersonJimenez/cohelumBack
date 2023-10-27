package com.backend.domain.service.Impl;


import com.backend.domain.entity.InventoryDetails;
import com.backend.domain.exception.DataNotFound;
import com.backend.domain.repository.InventoryDetailsRepository;
import com.backend.domain.service.InventoryDetailsService;
import com.backend.web.dto.InventoryDetails.GetInventoryDetailsDTO;
import com.backend.web.dto.InventoryDetails.InventoryDetailsDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class InventoryDetailsServiceImpl implements InventoryDetailsService {

    @Autowired
    private InventoryDetailsRepository inventoryDetailsRepository;

    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public GetInventoryDetailsDTO createInventoryDetails(InventoryDetailsDTO inventoryDetailsDTO) throws Exception {
        GetInventoryDetailsDTO getInventoryDetailsDTO;
        try {
            getInventoryDetailsDTO = this.getInventoryDetailsByCharacteristic(inventoryDetailsDTO.getCharacteristic());
        } catch (DataNotFound dataNotFound) {
            InventoryDetails inventoryDetails = this.objectMapper.convertValue(inventoryDetailsDTO, InventoryDetails.class);
            inventoryDetails.setHighDate(new Date());
            getInventoryDetailsDTO = this.generateStructureResponse(
                    this.inventoryDetailsRepository.save(this.objectMapper.convertValue(inventoryDetailsDTO, InventoryDetails.class))
            );
        }

        return getInventoryDetailsDTO;
    }

    @Override
    public GetInventoryDetailsDTO updateDetails(InventoryDetailsDTO inventoryDetailsDTO, Integer idDetails) throws Exception {
        GetInventoryDetailsDTO getInventoryDetailsDTO = this.getDetailsById(idDetails);
        InventoryDetails inventoryDetails = this.inventoryDetailsRepository.save(this.validationObject(getInventoryDetailsDTO));
        return this.generateStructureResponse(inventoryDetails);
    }

    @Override
    public GetInventoryDetailsDTO getDetailsById(Integer categoryId) throws Exception {
        InventoryDetails inventoryDetails = this.inventoryDetailsRepository.findByIdInventoryDetails(categoryId);
        return this.generateStructureResponse(inventoryDetails);
    }

    @Override
    public GetInventoryDetailsDTO getInventoryDetailsByCharacteristic(String characteristic) throws DataNotFound {
        InventoryDetails inventoryDetails = this.inventoryDetailsRepository.findByCharacteristic(characteristic);
        return this.generateStructureResponse(inventoryDetails);
    }

    private InventoryDetails validationObject(GetInventoryDetailsDTO getInventoryDetailsDTO) {

        InventoryDetails inventoryDetails = new InventoryDetails();
        inventoryDetails.setIdInventoryDetails(getInventoryDetailsDTO.getIdInventoryDetails());

        if (getInventoryDetailsDTO.getIdInventoryDetails() != null) {
            inventoryDetails.setCharacteristic(getInventoryDetailsDTO.getCharacteristic());
        }
        if (getInventoryDetailsDTO.getDatasheet() != null){
            inventoryDetails.setDatasheet(getInventoryDetailsDTO.getDatasheet());
        }

        inventoryDetails.setModificationDate(new Date());
        return inventoryDetails;
    }

    private GetInventoryDetailsDTO generateStructureResponse(InventoryDetails inventoryDetails) throws DataNotFound {
        GetInventoryDetailsDTO getInventoryDetailsDTO;

        if (inventoryDetails != null) {
            getInventoryDetailsDTO = this.objectMapper.convertValue(inventoryDetails, GetInventoryDetailsDTO.class);
        } else {
            throw new DataNotFound("El producto no existe en el inventario. ");
        }
        return getInventoryDetailsDTO;
    }
}
