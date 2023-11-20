package com.backend.domain.service.Impl;

import com.backend.domain.entity.Inventory;
import com.backend.domain.entity.InventoryCategory;
import com.backend.domain.entity.InventoryDetails;
import com.backend.domain.entity.InventoryImage;
import com.backend.domain.exception.DataNotFound;
import com.backend.domain.repository.InventoryCategoryRepository;
import com.backend.domain.repository.InventoryDetailsRepository;
import com.backend.domain.repository.InventoryImageRepository;
import com.backend.domain.repository.InventoryRepository;
import com.backend.domain.service.InventoryCategoryService;
import com.backend.domain.service.InventoryDetailsService;
import com.backend.domain.service.InventoryService;
import com.backend.web.dto.Inventory.GetInventoryDTO;
import com.backend.web.dto.Inventory.GetInventoryFullDTO;
import com.backend.web.dto.Inventory.InventoryDTO;
import com.backend.web.dto.Inventory.InventoryFullDTO;
import com.backend.web.dto.InventoryCategory.GetInventoryCategoryDTO;
import com.backend.web.dto.InventoryDetails.GetInventoryDetailsDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class InventoryServiceImpl implements InventoryService {

    @Autowired
    private InventoryRepository inventoryRepository;

    @Autowired
    private InventoryDetailsRepository inventoryDetailsRepository;

    @Autowired
    private InventoryImageRepository inventoryImageRepository;

    @Autowired
    private InventoryCategoryRepository inventoryCategoryRepository;
    @Autowired
    private InventoryDetailsService inventoryDetailsService;
    @Autowired
    private InventoryCategoryService inventoryCategoryService;
    private final ObjectMapper objectMapper = new ObjectMapper();


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

    @Override
    public void createFullInventory(InventoryFullDTO inventoryFullDTO) throws Exception {

        try {

            InventoryCategory savedCategory = inventoryCategoryRepository.findById(inventoryFullDTO.getCategoryId())
                    .orElseThrow(() -> new Exception("Categoría no encontrada"));

            Inventory inventory = new Inventory();
            inventory.setName(inventoryFullDTO.getName());
            inventory.setPrice(inventoryFullDTO.getPrice());
            inventory.setUnitsAvailable(inventoryFullDTO.getUnitsAvailable());
            inventory.setActive(true);
            inventory.setHighDate(new Date());
            inventory.setCategoryId(savedCategory.getIdCategory());
            inventoryRepository.save(inventory);

            InventoryDetails inventoryDetails = new InventoryDetails();
            inventoryDetails.setCharacteristic(inventoryFullDTO.getCharacteristic());
            inventoryDetails.setDatasheet(Base64.getEncoder().encodeToString(inventoryFullDTO.getDatasheet().getBytes()));
            inventoryDetails.setHighDate(new Date());
            inventoryDetails.setIdInventory(inventory.getIdInventory());
            inventoryDetailsRepository.save(inventoryDetails);

            byte[] imageBytes = convertMultipartFileToBytes(inventoryFullDTO.getImage());
            InventoryImage inventoryImage = new InventoryImage();
            inventoryImage.setImage(imageBytes);
            inventoryImage.setActive(true);
            inventoryImage.setHighDate(new Date());
            inventoryImage.setInventory(inventory);
            inventoryImageRepository.save(inventoryImage);

        } catch (Exception e) {
            throw new Exception("Error al crear el inventario completo.", e);
        }
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

    private byte[] convertMultipartFileToBytes(MultipartFile file) throws IOException {
        return file.getBytes();
    }

    @Override
    public List<InventoryFullDTO> getAllInventories() {
        List<Inventory> inventories = inventoryRepository.findAll();
        return inventories.stream()
                .map(inventory -> objectMapper.convertValue(inventory, InventoryFullDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public GetInventoryFullDTO getInventoryFull(Integer idInventory) throws Exception {
        GetInventoryFullDTO getInventoryFullDTO = new GetInventoryFullDTO();
        GetInventoryDTO getInventoryDTO = this.getInventoryByIdInventory(idInventory);

        if (getInventoryDTO != null) {
            getInventoryFullDTO.setGetInventoryDTO(getInventoryDTO);
            GetInventoryDetailsDTO getInventoryDetailsDTO =
                    this.inventoryDetailsService.getInventoryDetailsByIdInventory(getInventoryDTO.getIdInventory());
            if (getInventoryDetailsDTO != null) {
                getInventoryFullDTO.setGetInventoryDetailsDTO(getInventoryDetailsDTO);
            }

            GetInventoryCategoryDTO getInventoryCategoryDTO =
                    this.inventoryCategoryService.getCategoryById(getInventoryDTO.getCategoryId());

            if (getInventoryCategoryDTO != null) {
                getInventoryFullDTO.setGetInventoryCategoryDTO(getInventoryCategoryDTO);
            }
        }

        return getInventoryFullDTO;
    }

}
