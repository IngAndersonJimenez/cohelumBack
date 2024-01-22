package com.backend.domain.service.Impl;

import com.backend.domain.entity.InventorySubCategory;
import com.backend.domain.entity.SettingTP;
import com.backend.domain.entity.Warranty;
import com.backend.domain.exception.DataNotFound;
import com.backend.domain.repository.WarrantyRepository;
import com.backend.domain.service.WarrantyService;
import com.backend.web.dto.InventorySubCategory.GetInventorySubCategoryDTO;
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
    public GetWarrantyDTO createWarranty(RequestWarrantyDTO requestWarrantyDTO) throws DataNotFound, IOException {
        GetWarrantyDTO getWarrantyDTO;
        Warranty warranty = this.objectMapper.convertValue(requestWarrantyDTO,Warranty.class);
        warranty.setHighDate(new Date());
        warranty.setAttach(this.pdfService.storePdf(requestWarrantyDTO.getAttach(),"warranty-pdf"));
        getWarrantyDTO = this.generateStructureResponse(this.warrantyRepository.save(warranty));
        return getWarrantyDTO;
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
}
