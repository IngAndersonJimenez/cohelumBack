package com.backend.domain.service;

import com.backend.domain.exception.DataNotFound;
import com.backend.web.dto.InventoryComment.GetInventoryCommentDTO;
import com.backend.web.dto.InventoryComment.InventoryCommentDTO;

import java.util.List;

public interface InventoryCommentService {

    List<GetInventoryCommentDTO> getInventoryCommentByIdInventory(Integer idInventory) throws Exception;

    GetInventoryCommentDTO getInventoryCommentByQualification(Integer qualification) throws DataNotFound;

    GetInventoryCommentDTO createInventoryComment(InventoryCommentDTO InventoryCommentDTO) throws Exception;

    List<GetInventoryCommentDTO> getAllInventoriesComment() throws Exception;



}
