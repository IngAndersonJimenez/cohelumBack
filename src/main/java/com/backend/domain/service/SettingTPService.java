package com.backend.domain.service;

import com.backend.domain.exception.DataNotFound;
import com.backend.web.dto.SettingTP.GetSettingTPDTO;
import com.backend.web.dto.SettingTP.SettingTPDTO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface SettingTPService {

    List<GetSettingTPDTO> getSettingTPByArtefact(String artefact);

    GetSettingTPDTO createSettingTP(SettingTPDTO settingTPDTO) throws Exception;

    GetSettingTPDTO createImageSettingTP(Integer idSettingTP, MultipartFile file, String storageFolder) throws Exception;

    GetSettingTPDTO updateStatusSettingTP(Integer idSettingTP, Boolean statusSettingTP);

    GetSettingTPDTO updateSettingTP(Integer idSettingTP, SettingTPDTO settingTPDTO) throws DataNotFound;


}
