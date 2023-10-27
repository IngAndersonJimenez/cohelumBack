package com.backend.domain.service;

import com.backend.domain.exception.DataNotFound;
import com.backend.web.dto.InventaryComment.GetInventoryCommentDTO;
import com.backend.web.dto.InventaryComment.InventoryCommentDTO;
import com.backend.web.dto.Inventory.GetInventoryDTO;
import com.backend.web.dto.Inventory.InventoryDTO;

public interface InventoryCommentService {

    GetInventoryCommentDTO getInventoryCommentByIdInventoryComment(Integer idInventory) throws Exception;

    GetInventoryCommentDTO getInventoryCommentByQualification(Integer qualification) throws DataNotFound;

    GetInventoryCommentDTO createInventoryComment(InventoryCommentDTO InventoryCommentDTO) throws Exception;

    GetInventoryCommentDTO updateInventory(InventoryDTO inventoryDTO, Integer inventoryId) throws Exception;

}
