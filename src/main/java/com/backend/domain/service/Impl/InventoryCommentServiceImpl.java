package com.backend.domain.service.Impl;

import com.backend.domain.entity.InventoryComment;
import com.backend.domain.exception.DataNotFound;
import com.backend.domain.repository.InventoryCommentRepository;
import com.backend.domain.service.InventoryCommentService;
import com.backend.web.dto.InventoryComment.GetInventoryCommentDTO;
import com.backend.web.dto.InventoryComment.InventoryCommentDTO;
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
    public GetInventoryCommentDTO createInventoryComment(InventoryCommentDTO inventoryCommentDTO) throws Exception {
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
    public GetInventoryCommentDTO updateInventoryComment(InventoryCommentDTO inventoryCommentDTO, Integer inventoryId) throws Exception {

        GetInventoryCommentDTO getInventoryCommentDTO = this.getInventoryCommentByIdInventoryComment(inventoryId);
        InventoryComment inventoryComment = this.inventoryCommentRepository.save(this.validationObject(getInventoryCommentDTO));
        return this.generateStructureResponse(inventoryComment);
    }


    private InventoryComment validationObject(GetInventoryCommentDTO getInventoryCommentDTO) {

        InventoryComment inventoryComment = new InventoryComment();
        inventoryComment.setIdInventoryComment(getInventoryCommentDTO.getIdInventoryComment());

        if (getInventoryCommentDTO.getQualification() != null) {
            inventoryComment.setQualification(getInventoryCommentDTO.getQualification());
        }
        inventoryComment.setActive(getInventoryCommentDTO.isActive());

        inventoryComment.setModificationDate(new Date());
        return inventoryComment;
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
