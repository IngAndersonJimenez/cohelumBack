package com.backend.domain.service.Impl;

import com.backend.domain.entity.InventoryCategory;
import com.backend.domain.entity.InventoryComment;
import com.backend.domain.exception.DataNotFound;
import com.backend.domain.repository.InventoryCommentRepository;
import com.backend.domain.service.InventoryCommentService;
import com.backend.web.dto.InventaryComment.GetInventoryCommentDTO;
import com.backend.web.dto.InventaryComment.InventoryCommentDTO;
import com.backend.web.dto.Inventory.GetInventoryDTO;
import com.backend.web.dto.Inventory.InventoryDTO;
import com.backend.web.dto.InventoryCategory.GetInventoryCategoryDTO;
import com.backend.web.dto.InventoryCategory.InventoryCategoryDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class InventoryCommentServiceImpl implements InventoryCommentService {

    @Autowired
    private InventoryCommentRepository inventoryCommentRepository;

    private ObjectMapper objectMapper = new ObjectMapper();


    @Override
    public GetInventoryCommentDTO getInventoryCommentByIdInventoryComment(Integer idInventory) throws Exception {
        InventoryComment inventoryComment = this.inventoryCommentRepository.findByIdInventoryComment(idInventory);
        return this.generateStructureResponse(inventoryComment);
    }

    @Override
    public GetInventoryCommentDTO getInventoryCommentByQualification(Integer qualification) throws DataNotFound {
        InventoryComment inventoryComment = this.inventoryCommentRepository.findByQualification(qualification);
        return this.generateStructureResponse(inventoryComment);
    }

    @Override
    public GetInventoryCommentDTO createInventory(InventoryCommentDTO inventoryCommentDTO) throws Exception {
        GetInventoryCommentDTO getInventoryCommentDTO;

        try {
            getInventoryCommentDTO = this.getInventoryCommentByQualification(inventoryCommentDTO.getQualification());
        } catch (DataNotFound dataNotFound) {
            InventoryComment inventoryComment = this.objectMapper.convertValue(inventoryCommentDTO, InventoryComment.class);
            inventoryComment.setHighDate(new Date());
            getInventoryCommentDTO = this.generateStructureResponse(
                    this.inventoryCommentRepository.save(this.objectMapper.convertValue(inventoryCommentDTO, InventoryComment.class))
            );
        }

        return getInventoryCommentDTO;
    }

    @Override
    public GetInventoryCommentDTO updateInventory(InventoryDTO inventoryDTO, Integer inventoryId) throws Exception {
        return null;
    }

    private GetInventoryCommentDTO generateStructureResponse(InventoryComment inventoryComment) throws DataNotFound {
        GetInventoryCommentDTO getInventoryCommentDTO;

        if (inventoryComment != null) {
            getInventoryCommentDTO = this.objectMapper.convertValue(inventoryComment, GetInventoryCommentDTO.class);
        } else {
            throw new DataNotFound("El producto no existe en el inventario. ");
        }
        return getInventoryCommentDTO;
    }
}
