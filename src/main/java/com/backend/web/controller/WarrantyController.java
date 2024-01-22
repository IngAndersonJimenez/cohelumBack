package com.backend.web.controller;

import com.backend.domain.service.Impl.WarrantyServiceImpl;
import com.backend.web.dto.Generic.ResponseDTO;
import com.backend.web.dto.Warranty.RequestWarrantyDTO;
import com.backend.web.dto.Warranty.WarrantyDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/warranty")
@CrossOrigin
public class WarrantyController {

    @Autowired
    WarrantyServiceImpl warrantyService;

    @PostMapping("/create")
    public ResponseEntity<ResponseDTO> createWarranty(
            @ModelAttribute RequestWarrantyDTO requestWarrantyDTO) throws Exception {
        return ResponseEntity.ok(ResponseDTO
                .builder()
                .responseDTO(this.warrantyService.createWarranty(requestWarrantyDTO))
                .build());
    }

    @GetMapping("/getWarrantyAll")
    public ResponseEntity<ResponseDTO> getAllWarranty() {
        return ResponseEntity.ok(ResponseDTO
                .builder()
                .responseDTO(this.warrantyService.getWarranty())
                .build());
    }

}
