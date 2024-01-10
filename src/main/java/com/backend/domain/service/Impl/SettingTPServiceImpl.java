package com.backend.domain.service.Impl;

import com.backend.domain.entity.SettingTP;
import com.backend.domain.exception.DataNotFound;
import com.backend.domain.repository.SettingTPRepository;
import com.backend.domain.service.SettingTPService;
import com.backend.web.dto.SettingTP.GetSettingTPDTO;
import com.backend.web.dto.SettingTP.SettingTPDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Service
public class SettingTPServiceImpl implements SettingTPService {

    @Autowired
    private SettingTPRepository settingTPRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private ImageService imageService;

    @Override
    public List<GetSettingTPDTO> getSettingTPByArtefact(String artefact) {

        List<SettingTP> settingTPS = this.settingTPRepository.getSettingTPByArtefactAndActive(artefact, Boolean.TRUE);
        List<GetSettingTPDTO> getSettingTPDTOS = new ArrayList<>();

        if (!settingTPS.isEmpty()) {
            for (SettingTP settingTP : settingTPS) {
                getSettingTPDTOS.add(this.objectMapper.convertValue(settingTP, GetSettingTPDTO.class));
            }
        }

        return getSettingTPDTOS;
    }

    @Override
    public GetSettingTPDTO createSettingTP(SettingTPDTO settingTPDTO) throws Exception {
        GetSettingTPDTO getSettingTPDTO;
        SettingTP settingTP = this.objectMapper.convertValue(settingTPDTO, SettingTP.class);
        settingTP.setActive(Boolean.TRUE);
        getSettingTPDTO = this.generateStructureResponse(this.settingTPRepository.save(settingTP));
        return getSettingTPDTO;
    }

    @Override
    public GetSettingTPDTO createImageSettingTP(Integer idSettingTP, MultipartFile file, String storageFolder) throws Exception {
        SettingTP settingTP = this.settingTPRepository.getById(idSettingTP);

        GetSettingTPDTO getSettingTPDTO = new GetSettingTPDTO();

        if (settingTP != null) {
            settingTP.setValue4(this.imageService.storeImage(file, storageFolder));
            settingTP.setActive(Boolean.TRUE);
            getSettingTPDTO = this.objectMapper.convertValue(this.settingTPRepository.save(settingTP), GetSettingTPDTO.class);
        }
        return getSettingTPDTO;
    }

    @Override
    public GetSettingTPDTO updateStatusSettingTP(Integer idSettingTP, Boolean statusSettingTP) {
        SettingTP settingTP = this.settingTPRepository.getById(idSettingTP);
        GetSettingTPDTO getSettingTPDTO = new GetSettingTPDTO();

        if (settingTP != null) {
            settingTP.setActive(statusSettingTP);
            getSettingTPDTO = this.objectMapper.convertValue(this.settingTPRepository.save(settingTP), GetSettingTPDTO.class);
        }
        return getSettingTPDTO;
    }

    private GetSettingTPDTO generateStructureResponse(SettingTP settingTP) throws DataNotFound {
        GetSettingTPDTO getSettingTPDTO;

        if (settingTP != null) {
            getSettingTPDTO = this.objectMapper.convertValue(settingTP, GetSettingTPDTO.class);
        } else {
            throw new DataNotFound("El parametro de configuracion no existe. ");
        }
        return getSettingTPDTO;
    }

}
