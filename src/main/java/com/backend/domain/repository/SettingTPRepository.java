package com.backend.domain.repository;

import com.backend.domain.entity.SettingTP;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SettingTPRepository extends JpaRepository<SettingTP, Integer> {

    List<SettingTP> getSettingTPByArtefactAndActive(String artefact, Boolean active);

}
