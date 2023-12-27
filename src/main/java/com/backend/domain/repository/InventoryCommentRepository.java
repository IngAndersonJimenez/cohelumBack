package com.backend.domain.repository;

import com.backend.domain.entity.InventoryComment;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InventoryCommentRepository extends CrudRepository<InventoryComment, Integer> {

    List<InventoryComment> findByIdInventory(Integer idInventoryComment);
    InventoryComment findByQualification(Integer qualification);


}
