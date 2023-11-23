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

import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.List;

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
    public List<GetInventoryImageDTO> getImagesByIdInventory(Integer idInventory) throws Exception {
        List<InventoryImage> inventoryImages =
                this.inventoryImageRepository.getInventoryImageByIdInventory(idInventory);
        List<GetInventoryImageDTO> getInventoryImageDTOS = new ArrayList<>();
        if (inventoryImages != null){
            for (InventoryImage inventoryImageIter: inventoryImages){
              getInventoryImageDTOS.add(this.objectMapper.convertValue(inventoryImageIter, GetInventoryImageDTO.class));
            }

        }

        return getInventoryImageDTOS;
    }

    @Override
    public GetInventoryImageDTO createInventoryImage(MultipartFile file) throws Exception {
        GetInventoryImageDTO getInventoryImageDTO;
        InventoryImage inventoryImageDTO = new InventoryImage();
        try {
            getInventoryImageDTO = this.getImage(inventoryImageDTO.getImage().getBytes());
        } catch (DataNotFound dataNotFound) {
            InventoryImage inventoryImage = this.objectMapper.convertValue(inventoryImageDTO, InventoryImage.class);
            inventoryImage.setImage(Base64.getEncoder().encodeToString(inventoryImageDTO.getImage().getBytes()));
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
