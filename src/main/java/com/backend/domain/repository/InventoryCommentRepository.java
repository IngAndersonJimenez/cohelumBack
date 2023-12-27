package com.backend.domain.repository;

import com.backend.domain.entity.InventoryComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InventoryCommentRepository extends JpaRepository<InventoryComment, Integer> {

    List<InventoryComment> findByIdInventory(Integer idInventoryComment);
    InventoryComment findByQualification(Integer qualification);


}
