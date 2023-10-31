package com.backend.domain.service;


import com.backend.web.dto.InventoryImage.GetInventoryImageDTO;
import com.backend.web.dto.InventoryImage.InventoryImageDTO;
import org.springframework.web.multipart.MultipartFile;

public interface InventoryImageService {

    GetInventoryImageDTO createInventoryImage(InventoryImageDTO inventoryImageDTO, MultipartFile file) throws Exception;
    GetInventoryImageDTO getImageById(Integer categoryId) throws Exception;

    GetInventoryImageDTO getImage(byte[] image) throws Exception;
}
