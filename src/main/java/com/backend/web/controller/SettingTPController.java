package com.backend.web.controller;

import com.backend.domain.service.SettingTPService;
import com.backend.web.dto.Generic.ResponseDTO;
import com.backend.web.dto.SettingTP.SettingTPDTO;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Api(tags = "SettingTP")
@RestController
@RequestMapping("/api/v1/SettingTP")
@CrossOrigin
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
    public ResponseEntity<ResponseDTO> createSettingTP(
            @RequestBody SettingTPDTO settingTPDTO) throws Exception {
        return ResponseEntity.ok(ResponseDTO
                .builder()
                .responseDTO(this.settingTPService.createSettingTP(settingTPDTO))
                .build());
    }

    @PostMapping("/createImage/{idSettingTP}")
    public ResponseEntity<ResponseDTO> createImageSettingTP(
            @PathVariable Integer idSettingTP,
            @RequestParam(value = "storageFolder") String storageFolder,
            @RequestPart(value = "imageSettingTP", required = false) MultipartFile file) throws Exception {
        return ResponseEntity.ok(ResponseDTO
                .builder()
                .responseDTO(this.settingTPService.createImageSettingTP(idSettingTP, file, storageFolder))
                .build());
    }

    @PutMapping("/updateStatus/{idSettingTP}")
    public ResponseEntity<ResponseDTO> updateStatusSettingTP(
            @PathVariable Integer idSettingTP,
            @RequestParam(value = "statusSettingTP") Boolean statusSettingTP) throws Exception {
        return ResponseEntity.ok(ResponseDTO
                .builder()
                .responseDTO(this.settingTPService.updateStatusSettingTP(idSettingTP, statusSettingTP))
                .build());
    }

    @PutMapping("/updateSettingTP/{idSettingTP}")
    public ResponseEntity<ResponseDTO> updateSettingTP(
            @PathVariable Integer idSettingTP,
            @RequestBody SettingTPDTO settingTPDTO) throws Exception {
        return ResponseEntity.ok(ResponseDTO
                .builder()
                .responseDTO(this.settingTPService.updateSettingTP(idSettingTP, settingTPDTO))
                .build());
    }

}
