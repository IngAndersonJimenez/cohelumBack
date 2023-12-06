package com.backend.domain.service;

import com.backend.web.dto.InventoryImage.GetInventoryImageDTO;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;

public interface InventoryImageService {

    GetInventoryImageDTO createInventoryImage(MultipartFile file, Integer idInventory) throws Exception;
    GetInventoryImageDTO getImageById(Integer categoryId) throws Exception;

    GetInventoryImageDTO getImage(byte[] image) throws Exception;

    List<GetInventoryImageDTO> getImagesByIdInventory (Integer idInventory) throws Exception;
}
