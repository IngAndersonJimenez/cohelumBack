package com.backend.domain.service;

import com.backend.web.dto.SettingTP.GetSettingTPDTO;
import com.backend.web.dto.SettingTP.SettingTPDTO;

import java.util.List;

public interface SettingTPService {

    List<GetSettingTPDTO> getSettingTPByArtefact(String artefact);

    GetSettingTPDTO createSettingTP(SettingTPDTO settingTPDTO) throws Exception;


}
