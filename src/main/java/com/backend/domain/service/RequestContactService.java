package com.backend.domain.service;

import com.backend.domain.exception.DataNotFound;
import com.backend.web.dto.Inventory.InventoryFullDTO;
import com.backend.web.dto.RequestContact.*;

import java.util.List;

public interface RequestContactService {

    GetRequestContactDTO createContact(RequestContactDTO requestContactDTO) throws Exception;

    GetRequestContactDTO getContactByIdContact(Integer idInventory) throws Exception;

    GetRequestContactDTO getContactByName(String name) throws DataNotFound;

    List<ContactDTO> getAllContact() throws Exception;

    ResponseMessageDTO getContactIsNotRead(boolean isNotRead) throws Exception;

    ResponseUpdateMessageDTO updateStatusRead(Integer idRequestContact, boolean statusRead) throws Exception;

}
