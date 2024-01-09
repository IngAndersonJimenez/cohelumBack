package com.backend.web.controller;

import com.backend.domain.service.SettingTPService;
import com.backend.web.dto.Generic.ResponseDTO;
import com.backend.web.dto.Inventory.InventoryDTO;
import com.backend.web.dto.SettingTP.SettingTPDTO;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Api(tags = "SettingTP")
@RestController
@RequestMapping("/api/v1/SettingTP")
public class SettingTPController {

    @Autowired
    private SettingTPService settingTPService;

    @GetMapping("/{artefact}")
    public ResponseEntity<ResponseDTO> getSettingTPByArtefact(@PathVariable String artefact) throws Exception {
        return ResponseEntity.ok(ResponseDTO
                .builder()
                .responseDTO(this.settingTPService.getSettingTPByArtefact(artefact))
                .build());
    }

    @PostMapping("/create")
    public ResponseEntity<ResponseDTO> createSettingTP(@RequestBody SettingTPDTO settingTPDTO) throws Exception {
        return ResponseEntity.ok(ResponseDTO
                .builder()
                .responseDTO(this.settingTPService.createSettingTP(settingTPDTO))
                .build());
    }


}
