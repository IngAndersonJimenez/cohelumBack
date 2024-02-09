package com.backend.domain.service.Impl;

import com.backend.domain.entity.InventoryImage;
import com.backend.domain.exception.DataNotFound;
import com.backend.domain.repository.InventoryImageRepository;
import com.backend.domain.service.InventoryImageService;
import com.backend.web.dto.InventoryImage.GetInventoryImageDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.List;

@Service
public class InventoryImageServiceImpl implements InventoryImageService {

    @Autowired
    private InventoryImageRepository inventoryImageRepository;

    @Autowired
    private ImageService imageService;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public GetInventoryImageDTO getImageById(Integer imageId) throws Exception {
        InventoryImage inventoryImage = this.inventoryImageRepository.findByIdInventoryImage(imageId);
        return this.generateStructureResponse(inventoryImage);
    }



    @Override
    public List<GetInventoryImageDTO> getImagesByIdInventory(Integer idInventory) throws Exception {
        List<InventoryImage> inventoryImages =
                this.inventoryImageRepository.getInventoryImageByIdInventory(idInventory);
        List<GetInventoryImageDTO> getInventoryImageDTOS = new ArrayList<>();
        if (inventoryImages != null) {
            for (InventoryImage inventoryImageIter : inventoryImages) {
                getInventoryImageDTOS.add(this.objectMapper.convertValue(inventoryImageIter, GetInventoryImageDTO.class));
            }

        }

        return getInventoryImageDTOS;
    }

    @Override
    public GetInventoryImageDTO createInventoryImage(MultipartFile file, Integer idInventory, String fileName) throws Exception {
        GetInventoryImageDTO getInventoryImageDTO;
        InventoryImage inventoryImage = new InventoryImage();
        inventoryImage.setImage(this.imageService.storeImage(file, fileName));
        inventoryImage.setActive(true);
        inventoryImage.setIdInventory(idInventory);
        inventoryImage.setHighDate(new Date());
        getInventoryImageDTO = this.generateStructureResponse(
                this.inventoryImageRepository.save(inventoryImage)
        );

        return getInventoryImageDTO;
    }

    @Override
    public GetInventoryImageDTO updateInventoryImage(MultipartFile file, Integer idInventoryImage, String fileName) throws Exception {
        try {

            InventoryImage inventoryImage = inventoryImageRepository.findByIdInventoryImage(idInventoryImage);
            inventoryImage.setImage(this.imageService.storeImage(file, fileName));
            inventoryImage.setHighDate(new Date());
            inventoryImageRepository.save(inventoryImage);
            return generateStructureResponse(inventoryImage);

        } catch (Exception e) {
            throw new Exception("Error al actualizar la imagen del inventario.", e);
        }
    }

    public void deleteByIdInventoryImage(Integer idInventoryImage) throws DataNotFound {
        if (inventoryImageRepository.existsById(idInventoryImage)) {
            inventoryImageRepository.deleteById(idInventoryImage);
        } else {
            throw new DataNotFound("Imagen no encontrada con el ID: " + idInventoryImage);
        }
    }


    private GetInventoryImageDTO generateStructureResponse(InventoryImage inventoryImage) throws DataNotFound {
        GetInventoryImageDTO getInventoryImageDTO;

        if (inventoryImage != null) {
            getInventoryImageDTO = this.objectMapper.convertValue(inventoryImage, GetInventoryImageDTO.class);
        } else {
            throw new DataNotFound("El producto no existe en el inventario. ");
        }
        return getInventoryImageDTO;
    }
}
