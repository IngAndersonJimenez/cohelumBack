package com.backend.web.controller;

import com.backend.domain.entity.InventoryCategory;
import com.backend.domain.service.RequestContactService;
import com.backend.web.dto.CategoryImage.CategoryImageDTO;
import com.backend.web.dto.Generic.ResponseDTO;
import com.backend.web.dto.InventoryCategory.InventoryCategoryDTO;
import com.backend.web.dto.RequestContact.RequestContactDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/v1/requestContact")
@CrossOrigin
public class RequestContactController {

    @Autowired
    private RequestContactService requestContactService;

    @PostMapping("/create")
    public ResponseEntity<ResponseDTO> createInventory(@ModelAttribute RequestContactDTO requestContactDTO) throws Exception {
        return ResponseEntity.ok(ResponseDTO
                .builder()
                .responseDTO(this.requestContactService.createContact(requestContactDTO))
                .build());
    }

    @GetMapping("/{idContact}")
    public ResponseEntity<ResponseDTO> getContactById(@PathVariable Integer idContact) throws Exception {
        return ResponseEntity.ok(ResponseDTO
                .builder()
                .responseDTO(this.requestContactService.getContactByIdContact(idContact))
                .build());
    }

    @GetMapping("/searchByName/{name}")
    public ResponseEntity<ResponseDTO> getContactByName(@PathVariable String name) throws Exception {
        return ResponseEntity.ok(ResponseDTO
                .builder()
                .responseDTO(this.requestContactService.getContactByName(name))
                .build());
    }

    @GetMapping("list")
    public ResponseEntity<ResponseDTO> getContactByList() throws Exception {
        return ResponseEntity.ok(ResponseDTO
                .builder()
                .responseDTO(this.requestContactService.getAllContact())
                .build());
    }

    @GetMapping("notifications/{isNotRead}")
    public ResponseEntity<ResponseDTO> getContactIsNotRead(@PathVariable boolean isNotRead) throws Exception {
        return ResponseEntity.ok(ResponseDTO
                .builder()
                .responseDTO(this.requestContactService.getContactIsNotRead(isNotRead))
                .build());
    }

    @PutMapping("notifications/update/{status}/{idContact}")
    public ResponseEntity<ResponseDTO> updateStatusRead(@PathVariable boolean status, @PathVariable Integer idContact) throws Exception {

        return ResponseEntity.ok(ResponseDTO
                .builder()
                .responseDTO(this.requestContactService.updateStatusRead(idContact,status))
                .build());
    }






}
