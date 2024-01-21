package com.backend.domain.repository;

import com.backend.domain.entity.SettingTP;
import com.backend.domain.entity.Warranty;
import com.backend.web.dto.Warranty.GetWarrantyDTO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WarrantyRepository extends JpaRepository<Warranty, Integer> {


}
