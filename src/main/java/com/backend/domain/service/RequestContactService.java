package com.backend.domain.service;

import com.backend.domain.exception.DataNotFound;
import com.backend.web.dto.RequestContact.GetRequestContactDTO;
import com.backend.web.dto.RequestContact.RequestContactDTO;

public interface RequestContactService {

    GetRequestContactDTO createContact(RequestContactDTO requestContactDTO) throws Exception;

    GetRequestContactDTO getContactByIdContact(Integer idInventory) throws Exception;

    GetRequestContactDTO getContactByName(String name) throws DataNotFound;
}
