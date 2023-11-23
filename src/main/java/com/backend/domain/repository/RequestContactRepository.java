package com.backend.domain.repository;

import com.backend.domain.entity.RequestContact;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RequestContactRepository extends JpaRepository<RequestContact, Integer> {

    RequestContact findOneRequestContactByIdRequest(Integer idInventory);

    RequestContact findOneRequestContactByNameContact(String name);
}
