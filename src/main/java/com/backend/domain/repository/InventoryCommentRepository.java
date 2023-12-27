package com.backend.domain.repository;

import com.backend.domain.entity.InventoryComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InventoryCommentRepository extends JpaRepository<InventoryComment, Integer> {

    InventoryComment findByIdInventoryComment(Integer idInventoryComment);
    InventoryComment findByQualification(Integer qualification);
}
