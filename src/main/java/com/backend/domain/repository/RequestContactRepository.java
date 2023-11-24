package com.backend.domain.repository;

import com.backend.domain.entity.Inventory;
import com.backend.domain.entity.RequestContact;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RequestContactRepository extends JpaRepository<RequestContact, Integer> {

    RequestContact findOneRequestContactByIdRequest(Integer idInventory);

    RequestContact findOneRequestContactByNameContact(String name);

}
