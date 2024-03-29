package com.backend.domain.service.Impl;

import com.backend.domain.entity.Inventory;
import com.backend.domain.entity.InventoryDetails;
import com.backend.domain.entity.InventoryImage;
import com.backend.domain.exception.DataNotFound;
import com.backend.domain.repository.InventoryCategoryRepository;
import com.backend.domain.repository.InventoryDetailsRepository;
import com.backend.domain.repository.InventoryImageRepository;
import com.backend.domain.repository.InventoryRepository;
import com.backend.domain.service.*;
import com.backend.web.dto.Inventory.GetInventoryDTO;
import com.backend.web.dto.Inventory.GetInventoryFullDTO;
import com.backend.web.dto.Inventory.InventoryDTO;
import com.backend.web.dto.Inventory.InventoryFullDTO;
import com.backend.web.dto.InventoryCategory.GetInventoryCategoryDTO;
import com.backend.web.dto.InventoryDetails.GetInventoryDetailsDTO;
import com.backend.web.dto.InventoryImage.GetInventoryImageDTO;
import com.backend.web.dto.InventorySubCategory.GetInventorySubCategoryDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.List;


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
    @Autowired
    private InventoryImageService inventoryImageService;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    private InventorySubCategoryService inventorySubCategoryService;

    @Autowired
    private ImageService imageService;

    @Autowired
    private PdfService pdfService;


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
    public GetInventoryDTO getInventoryByNameAndReference(String name,String reference) throws DataNotFound {
        Inventory inventory = this.inventoryRepository.findOneInventoryByNameAndReference(name,reference);
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
            GetInventorySubCategoryDTO getInventorySubCategoryDTO =
                    this.inventorySubCategoryService.getInventorySubCategoryByIdSubCategory(inventoryFullDTO.getIdSubCategory());

            Inventory inventory = new Inventory();
            inventory.setName(inventoryFullDTO.getName());
            inventory.setReference(inventoryFullDTO.getReference());
            inventory.setPrice(inventoryFullDTO.getPrice());
            inventory.setUnitsAvailable(inventoryFullDTO.getUnitsAvailable());
            inventory.setActive(true);
            inventory.setHighDate(new Date());
            inventory.setIdSubCategory(getInventorySubCategoryDTO.getIdSubCategory());
            inventoryRepository.save(inventory);

            InventoryDetails inventoryDetails = new InventoryDetails();
            inventoryDetails.setCharacteristic(inventoryFullDTO.getCharacteristic());
            inventoryDetails.setDatasheet(this.pdfService.storePdf(inventoryFullDTO.getDatasheet(), inventoryFullDTO.getName()));
            inventoryDetails.setHighDate(new Date());
            inventoryDetails.setIdInventory(inventory.getIdInventory());
            inventoryDetailsRepository.save(inventoryDetails);

            if (inventoryFullDTO.getImage() != null && !inventoryFullDTO.getImage().isEmpty()) {
                for (MultipartFile image : inventoryFullDTO.getImage()) {
                    InventoryImage inventoryImage = new InventoryImage();
                    inventoryImage.setImage(this.imageService.storeImage(image, inventoryFullDTO.getName()));
                    inventoryImage.setActive(true);
                    inventoryImage.setHighDate(new Date());
                    inventoryImage.setIdInventory(inventory.getIdInventory());
                    inventoryImageRepository.save(inventoryImage);
                }
            }

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


    @Override
    public List<GetInventoryFullDTO> getAllInventories() throws Exception {
        List<Inventory> inventories = inventoryRepository.findAll();
        List<GetInventoryFullDTO> getInventoryFullDTOS = new ArrayList<>();

        for (Inventory inventory : inventories) {
            if (inventory.isActive()) {
                GetInventoryFullDTO getInventoryFullDTO = new GetInventoryFullDTO();
                getInventoryFullDTO.setGetInventoryDTO(this.objectMapper.convertValue(inventory, GetInventoryDTO.class));
                getInventoryFullDTO.setGetInventorySubCategoryDTO(
                        this.inventorySubCategoryService.getInventorySubCategoryByIdSubCategory(inventory.getIdSubCategory()));
                if (getInventoryFullDTO.getGetInventorySubCategoryDTO().isActive()) {
                    getInventoryFullDTO.setGetInventoryCategoryDTO(
                            this.inventoryCategoryService.getCategoryById(
                                    getInventoryFullDTO.getGetInventorySubCategoryDTO().getIdCategory()));
                    if (getInventoryFullDTO.getGetInventoryCategoryDTO().isActive()) {
                        getInventoryFullDTO.setGetInventoryImageDTO(
                                this.inventoryImageService.getImagesByIdInventory(inventory.getIdInventory()));
                        getInventoryFullDTO.setGetInventoryDetailsDTO(
                                this.inventoryDetailsService.getInventoryDetailsByIdInventory(inventory.getIdInventory()));
                        getInventoryFullDTOS.add(getInventoryFullDTO);
                    }
                }
            }
        }

        return getInventoryFullDTOS;
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

            GetInventorySubCategoryDTO getInventorySubCategoryDTO =
                    this.inventorySubCategoryService.getInventorySubCategoryByIdSubCategory(getInventoryDTO.getIdSubCategory());

            if (getInventorySubCategoryDTO != null) {
                getInventoryFullDTO.setGetInventorySubCategoryDTO(getInventorySubCategoryDTO);

                GetInventoryCategoryDTO getInventoryCategoryDTO =
                        this.inventoryCategoryService.getCategoryById(getInventorySubCategoryDTO.getIdCategory());
                if (getInventoryCategoryDTO != null) {
                    getInventoryFullDTO.setGetInventoryCategoryDTO(getInventoryCategoryDTO);
                }

            }


            List<GetInventoryImageDTO> getInventoryImagesDTO =
                    this.inventoryImageService.getImagesByIdInventory(getInventoryDTO.getIdInventory());
            if (getInventoryImagesDTO != null) {
                getInventoryFullDTO.setGetInventoryImageDTO(getInventoryImagesDTO);
            }
        }

        return getInventoryFullDTO;
    }

    @Override
    public GetInventoryFullDTO getInventoryFullByName(String nameInventory) throws Exception {
        GetInventoryFullDTO getInventoryFullDTO = new GetInventoryFullDTO();

        GetInventoryDTO getInventoryDTO = this.getInventoryByName(nameInventory);

        if (getInventoryDTO != null) {
            getInventoryFullDTO.setGetInventoryDTO(getInventoryDTO);

            GetInventoryDetailsDTO getInventoryDetailsDTO =
                    this.inventoryDetailsService.getInventoryDetailsByIdInventory(getInventoryDTO.getIdInventory());

            if (getInventoryDetailsDTO != null) {
                getInventoryFullDTO.setGetInventoryDetailsDTO(getInventoryDetailsDTO);
            }

            GetInventorySubCategoryDTO getInventorySubCategoryDTO =
                    this.inventorySubCategoryService.getInventorySubCategoryByIdSubCategory(getInventoryDTO.getIdSubCategory());

            if (getInventorySubCategoryDTO != null) {
                getInventoryFullDTO.setGetInventorySubCategoryDTO(getInventorySubCategoryDTO);

                GetInventoryCategoryDTO getInventoryCategoryDTO =
                        this.inventoryCategoryService.getCategoryById(getInventorySubCategoryDTO.getIdCategory());
                if (getInventoryCategoryDTO != null) {
                    getInventoryFullDTO.setGetInventoryCategoryDTO(getInventoryCategoryDTO);
                }

            }


            List<GetInventoryImageDTO> getInventoryImagesDTO =
                    this.inventoryImageService.getImagesByIdInventory(getInventoryDTO.getIdInventory());

            if (getInventoryImagesDTO != null) {
                getInventoryFullDTO.setGetInventoryImageDTO(getInventoryImagesDTO);
            }
        }

        return getInventoryFullDTO;
    }

    @Override
    public void updateInventoryFUll(InventoryFullDTO inventoryFullDTO, Integer inventoryId) throws Exception {
        try {

            Inventory inventory = inventoryRepository.findById(inventoryId)
                    .orElseThrow(() -> new Exception("Inventario no encontrado"));

            inventory.setName(inventoryFullDTO.getName());
            inventory.setPrice(inventoryFullDTO.getPrice());
            inventory.setReference(inventoryFullDTO.getReference());
            inventory.setUnitsAvailable(inventoryFullDTO.getUnitsAvailable());
            inventory.setIdSubCategory(inventoryFullDTO.getIdSubCategory());
            InventoryDetails inventoryDetails = inventoryDetailsRepository.getInventoryDetailsByIdInventory(inventoryId);
            inventoryDetails.setCharacteristic(inventoryFullDTO.getCharacteristic());
            inventoryRepository.save(inventory);
            inventoryDetailsRepository.save(inventoryDetails);

        } catch (Exception e) {
            throw new Exception("Error al actualizar el inventario completo.", e);
        }
    }

    @Override
    public GetInventoryFullDTO getInventoryFullByNameAndReference(String nameInventory, String reference) throws Exception {
        GetInventoryFullDTO getInventoryFullDTO = new GetInventoryFullDTO();
        GetInventoryDTO getInventoryDTO = this.getInventoryByNameAndReference(nameInventory, reference);

        if (getInventoryDTO != null) {
            getInventoryFullDTO.setGetInventoryDTO(getInventoryDTO);

            GetInventoryDetailsDTO getInventoryDetailsDTO =
                    this.inventoryDetailsService.getInventoryDetailsByIdInventory(getInventoryDTO.getIdInventory());

            if (getInventoryDetailsDTO != null) {
                getInventoryFullDTO.setGetInventoryDetailsDTO(getInventoryDetailsDTO);
            }

            GetInventorySubCategoryDTO getInventorySubCategoryDTO =
                    this.inventorySubCategoryService.getInventorySubCategoryByIdSubCategory(getInventoryDTO.getIdSubCategory());

            if (getInventorySubCategoryDTO != null) {
                getInventoryFullDTO.setGetInventorySubCategoryDTO(getInventorySubCategoryDTO);

                GetInventoryCategoryDTO getInventoryCategoryDTO =
                        this.inventoryCategoryService.getCategoryById(getInventorySubCategoryDTO.getIdCategory());
                if (getInventoryCategoryDTO != null) {
                    getInventoryFullDTO.setGetInventoryCategoryDTO(getInventoryCategoryDTO);
                }

            }

            List<GetInventoryImageDTO> getInventoryImagesDTO =
                    this.inventoryImageService.getImagesByIdInventory(getInventoryDTO.getIdInventory());

            if (getInventoryImagesDTO != null) {
                getInventoryFullDTO.setGetInventoryImageDTO(getInventoryImagesDTO);
            }
        }

        return getInventoryFullDTO;
    }
    @Override
    public void updateInventoryDatasheet(MultipartFile datasheet, Integer inventoryId, String productName) throws Exception {
        InventoryDetails inventoryDetails = inventoryDetailsRepository.getInventoryDetailsByIdInventory(inventoryId);
        if (inventoryDetails == null) {
            throw new Exception("Detalles del inventario no encontrados para el ID: " + inventoryId);
        }

        if (datasheet != null && !datasheet.isEmpty()) {
            String datasheetUrl = pdfService.storePdf(datasheet, productName);
            inventoryDetails.setDatasheet(datasheetUrl);
            inventoryDetailsRepository.save(inventoryDetails);
        } else {
            throw new Exception("El archivo PDF proporcionado no es válido o está vacío.");
        }
    }

}
