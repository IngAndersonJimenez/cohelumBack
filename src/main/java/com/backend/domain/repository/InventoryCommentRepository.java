package com.backend.domain.repository;

import com.backend.domain.entity.InventoryComment;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InventoryCommentRepository extends CrudRepository<InventoryComment, Integer> {
}
