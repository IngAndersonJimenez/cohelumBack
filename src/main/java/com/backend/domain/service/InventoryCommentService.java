package com.backend.domain.service;

import com.backend.domain.exception.DataNotFound;
import com.backend.web.dto.InventoryComment.GetInventoryCommentDTO;
import com.backend.web.dto.InventoryComment.InventoryCommentDTO;

public interface InventoryCommentService {

    GetInventoryCommentDTO getInventoryCommentByIdInventoryComment(Integer idInventory) throws Exception;

    GetInventoryCommentDTO getInventoryCommentByQualification(Integer qualification) throws DataNotFound;

    GetInventoryCommentDTO createInventoryComment(InventoryCommentDTO InventoryCommentDTO) throws Exception;

    GetInventoryCommentDTO updateInventoryComment(InventoryCommentDTO inventoryCommentDTO, Integer inventoryId) throws Exception;

}
