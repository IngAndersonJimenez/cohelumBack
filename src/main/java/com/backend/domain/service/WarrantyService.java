package com.backend.domain.service;

import com.backend.domain.exception.DataNotFound;
import com.backend.web.dto.Warranty.GetWarrantyDTO;
import com.backend.web.dto.Warranty.RequestWarrantyDTO;
import com.backend.web.dto.Warranty.WarrantyDTO;

import java.io.IOException;
import java.util.List;

public interface WarrantyService {

    GetWarrantyDTO createWarranty(RequestWarrantyDTO requestWarrantyDTO) throws Exception;
    List<GetWarrantyDTO> getWarranty();

}
