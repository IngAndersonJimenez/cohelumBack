package com.backend.domain.service;

import com.backend.domain.exception.DataNotFound;
import com.backend.web.dto.InventoryImage.GetInventoryImageDTO;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;

public interface InventoryImageService {

    GetInventoryImageDTO createInventoryImage(MultipartFile file, Integer idInventory,String filename) throws Exception;
    GetInventoryImageDTO getImageById(Integer categoryId) throws Exception;

    List<GetInventoryImageDTO> getImagesByIdInventory (Integer idInventory) throws Exception;

    GetInventoryImageDTO updateInventoryImage(MultipartFile file,Integer idInventoryImage,String fileName)  throws Exception;

    void deleteByIdInventoryImage(Integer idInventoryImage) throws DataNotFound;
}
