package com.backend.domain.repository;

import com.backend.domain.entity.InventoryCategory;
import com.backend.domain.entity.InventoryComment;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InventoryCommentRepository extends CrudRepository<InventoryComment, Integer> {

    InventoryComment findByIdInventoryComment(Integer idInventoryComment);
    InventoryComment findByQualification(Integer qualification);
}
