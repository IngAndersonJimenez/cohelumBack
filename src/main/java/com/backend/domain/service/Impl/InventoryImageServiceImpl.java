package com.backend.domain.service.Impl;

import com.backend.domain.entity.InventoryDetails;
import com.backend.domain.entity.InventoryImage;
import com.backend.domain.exception.DataNotFound;
import com.backend.domain.repository.InventoryImageRepository;
import com.backend.domain.service.InventoryImageService;
import com.backend.web.dto.InventoryDetails.GetInventoryDetailsDTO;
import com.backend.web.dto.InventoryDetails.InventoryDetailsDTO;
import com.backend.web.dto.InventoryImage.GetInventoryImageDTO;
import com.backend.web.dto.InventoryImage.InventoryImageDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;

@Service
public class InventoryImageServiceImpl implements InventoryImageService {

    @Autowired
    private InventoryImageRepository inventoryImageRepository;

    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public GetInventoryImageDTO getImageById(Integer imageId) throws Exception {
        InventoryImage inventoryImage = this.inventoryImageRepository.findByIdInventoryImage(imageId);
        return this.generateStructureResponse(inventoryImage);
    }

    @Override
    public GetInventoryImageDTO getImage(byte[] image) throws Exception {
        InventoryImage inventoryImage = this.inventoryImageRepository.findByImage(image);
        return this.generateStructureResponse(inventoryImage);
    }

    @Override
    public GetInventoryImageDTO createInventoryImage(InventoryImageDTO inventoryImageDTO, MultipartFile file) throws Exception {
        GetInventoryImageDTO getInventoryImageDTO;
        try {
            getInventoryImageDTO = this.getImage(inventoryImageDTO.getImage());
        } catch (DataNotFound dataNotFound) {
            InventoryImage inventoryImage = this.objectMapper.convertValue(inventoryImageDTO, InventoryImage.class);
            inventoryImage.setImage(file.getBytes());
            inventoryImage.setHighDate(new Date());
            getInventoryImageDTO = this.generateStructureResponse(
                    this.inventoryImageRepository.save(inventoryImage)
            );
        }
        return getInventoryImageDTO;
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
