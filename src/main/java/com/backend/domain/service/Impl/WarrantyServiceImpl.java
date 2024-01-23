package com.backend.domain.service.Impl;

import com.backend.domain.entity.InventorySubCategory;
import com.backend.domain.entity.RequestContact;
import com.backend.domain.entity.SettingTP;
import com.backend.domain.entity.Warranty;
import com.backend.domain.exception.DataNotFound;
import com.backend.domain.repository.WarrantyRepository;
import com.backend.domain.service.WarrantyService;
import com.backend.web.dto.InventorySubCategory.GetInventorySubCategoryDTO;
import com.backend.web.dto.RequestContact.GetRequestContactDTO;
import com.backend.web.dto.RequestContact.RequestContactDTO;
import com.backend.web.dto.SettingTP.GetSettingTPDTO;
import com.backend.web.dto.SettingTP.SettingTPDTO;
import com.backend.web.dto.Warranty.GetWarrantyDTO;
import com.backend.web.dto.Warranty.RequestWarrantyDTO;
import com.backend.web.dto.Warranty.WarrantyDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class WarrantyServiceImpl implements WarrantyService {

    @Autowired
    WarrantyRepository warrantyRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private PdfService pdfService;

    @Override
    public GetWarrantyDTO createWarranty(RequestWarrantyDTO requestWarrantyDTO) throws Exception {
        try {
            Warranty warranty = convertToEntity(requestWarrantyDTO);
            warranty.setAttach(this.pdfService.storePdf(requestWarrantyDTO.getAttach(),"warranty-pdf"));
            Warranty warranty1 = warrantyRepository.save(warranty);
            return convertToDto(warranty1);
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Error al procesar la solicitud de garantía extendida", e);
        }
    }


    @Override
    public List<GetWarrantyDTO> getWarranty() {
        List<Warranty> warranties = warrantyRepository.findAll();

        return warranties.stream()
                .map(warranty -> objectMapper.convertValue(warranty,GetWarrantyDTO.class))
                .collect(Collectors.toList());
    }

    private GetWarrantyDTO generateStructureResponse(Warranty warranty) throws DataNotFound {
        GetWarrantyDTO getWarrantyDTO;

        if (warranty != null) {
            getWarrantyDTO = this.objectMapper.convertValue(warranty, GetWarrantyDTO.class);
        } else {
            throw new DataNotFound("El parametro de configuracion no existe. ");
        }
        return getWarrantyDTO;
    }

    private GetWarrantyDTO convertToDto(Warranty warranty) {
        GetWarrantyDTO getWarrantyDTO = new GetWarrantyDTO();
        getWarrantyDTO.setIdRequestG(warranty.getIdRequestG());
        getWarrantyDTO.setNameContact(warranty.getNameContact());
        getWarrantyDTO.setTypeDocument(warranty.getTypeDocument());
        getWarrantyDTO.setDocument(warranty.getDocument());
        getWarrantyDTO.setEmail(warranty.getEmail());
        getWarrantyDTO.setDistributor(warranty.getDistributor());
        getWarrantyDTO.setNumber_bill(warranty.getNumber_bill());
        getWarrantyDTO.setCellphone(warranty.getCellphone());
        getWarrantyDTO.setDate(warranty.getDate());
        getWarrantyDTO.setProduct(warranty.getProduct());
        getWarrantyDTO.setAttach(warranty.getAttach());
        return getWarrantyDTO;
    }

    private Warranty convertToEntity(RequestWarrantyDTO requestWarrantyDTO) {
        Warranty warranty = new Warranty();
        warranty.setHighDate(new Date());
        warranty.setNameContact(requestWarrantyDTO.getNameContact());
        warranty.setTypeDocument(requestWarrantyDTO.getTypeDocument());
        warranty.setDocument(requestWarrantyDTO.getDocument());
        warranty.setEmail(requestWarrantyDTO.getEmail());
        warranty.setAttach(String.valueOf((requestWarrantyDTO.getAttach())));
        warranty.setDistributor(requestWarrantyDTO.getDistributor());
        warranty.setCellphone(requestWarrantyDTO.getCellphone());
        warranty.setDate(requestWarrantyDTO.getDate());
        warranty.setProduct(requestWarrantyDTO.getProduct());

        return warranty;
    }
}
