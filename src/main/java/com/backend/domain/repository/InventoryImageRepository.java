package com.backend.domain.repository;

import com.backend.domain.entity.InventoryImage;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InventoryImageRepository extends CrudRepository<InventoryImage, Integer> {

    InventoryImage findByIdInventoryImage(Integer idInventoryComment);

    InventoryImage findByImage(byte[] image);

    List<InventoryImage> getInventoryImageByIdInventory (Integer idInventory);

}
