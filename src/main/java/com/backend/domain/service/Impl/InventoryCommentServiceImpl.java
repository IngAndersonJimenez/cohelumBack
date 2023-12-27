package com.backend.domain.service.Impl;

import com.backend.domain.entity.InventoryComment;
import com.backend.domain.entity.InventorySubCategory;
import com.backend.domain.exception.DataNotFound;
import com.backend.domain.repository.InventoryCommentRepository;
import com.backend.domain.service.InventoryCommentService;
import com.backend.web.dto.InventoryComment.GetInventoryCommentDTO;
import com.backend.web.dto.InventoryComment.InventoryCommentDTO;
import com.backend.web.dto.InventorySubCategory.GetInventorySubCategoryDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class InventoryCommentServiceImpl implements InventoryCommentService {

    @Autowired
    private InventoryCommentRepository inventoryCommentRepository;

    private ObjectMapper objectMapper = new ObjectMapper();


    @Override
    public List<GetInventoryCommentDTO> getInventoryCommentByIdInventory(Integer idInventory) throws Exception {
        List<InventoryComment> inventoryComment = this.inventoryCommentRepository.findByIdInventory(idInventory);
        return inventoryComment.stream()
                .map(Comment -> objectMapper.convertValue(Comment, GetInventoryCommentDTO.class))
                .collect(Collectors.toList());
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
                    this.inventoryCommentRepository.save(inventoryComment));
        }

        return getInventoryCommentDTO;
    }


    @Override
    public List<GetInventoryCommentDTO> getAllInventoriesComment() throws Exception {
        List<InventoryComment> inventoryCommentList = (List<InventoryComment>) inventoryCommentRepository.findAll();
        return inventoryCommentList.stream()
                .map(Comment -> objectMapper.convertValue(Comment, GetInventoryCommentDTO.class))
                .collect(Collectors.toList());
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
