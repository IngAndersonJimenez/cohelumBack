package com.backend.domain.service.Impl;

import com.backend.domain.entity.*;
import com.backend.domain.exception.DataNotFound;
import com.backend.domain.repository.RequestContactRepository;
import com.backend.domain.service.RequestContactService;
import com.backend.web.dto.RequestContact.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.List;

@Service
public class RequestContactServiceImpl implements RequestContactService {

    @Autowired
    private RequestContactRepository requestContactRepository;

    @Autowired
    private PdfService pdfService;

    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public GetRequestContactDTO createContact(RequestContactDTO requestContactDTO) throws Exception {
        try {
            RequestContact requestContact = convertToEntity(requestContactDTO);
            requestContact.setAttach(this.pdfService.storePdf(requestContactDTO.getAttach(),"contact-pdf"));
            RequestContact savedRequestContact = requestContactRepository.save(requestContact);
            return convertToDto(savedRequestContact);
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Error al procesar la solicitud de contacto", e);
        }
    }

    @Override
    public GetRequestContactDTO getContactByIdContact(Integer idIRequest) throws Exception {
        RequestContact requestContact = this.requestContactRepository.findOneRequestContactByIdRequest(idIRequest);
        return this.generateStructureResponse(requestContact);
    }

    @Override
    public GetRequestContactDTO getContactByName(String name) throws DataNotFound {
        RequestContact requestContact = this.requestContactRepository.findOneRequestContactByNameContact(name);
        return this.generateStructureResponse(requestContact);
    }

    @Override
    public List<ContactDTO> getAllContact() throws Exception {
        List<RequestContact> requestContact = requestContactRepository.findAll();
        List<ContactDTO> contactDTOS = new ArrayList<>();

        for (RequestContact requestContact1: requestContact){
            contactDTOS.add(this.convertToDto(requestContact1));
        }

        return contactDTOS;
    }

    @Override
    public ResponseMessageDTO getContactIsNotRead(boolean isNotRead) throws Exception {
        List<RequestContact> requestContacts = this.requestContactRepository.findRequestContactByRead(isNotRead);
        ResponseMessageDTO responseMessageDTO = new ResponseMessageDTO();
        List<Integer> responseIntegers = new ArrayList<>();
        List<String> responseNames = new ArrayList<>();

        for (RequestContact requestContact : requestContacts) {
            responseIntegers.add(requestContact.getIdRequest());
            responseNames.add(requestContact.getNameContact());
        }

        responseMessageDTO.setName(responseNames);
        responseMessageDTO.setIdContact(responseIntegers);
        return responseMessageDTO;
    }

    @Override
    public ResponseUpdateMessageDTO updateStatusRead(Integer idRequestContact, boolean statusRead) throws Exception {
        RequestContact requestContact = this.requestContactRepository.findOneRequestContactByIdRequest(idRequestContact);
        ResponseUpdateMessageDTO responseUpdateMessageDTO = new ResponseUpdateMessageDTO();
        if(requestContact !=null){
            requestContact.setRead(statusRead);
            this.requestContactRepository.save(requestContact);
            responseUpdateMessageDTO.setResultUpdate("Status actualizado");
        } else {
            responseUpdateMessageDTO.setResultUpdate("El mensaje no existe");
        }
        return responseUpdateMessageDTO;
    }


    private GetRequestContactDTO generateStructureResponse(RequestContact requestContact) throws DataNotFound {
        GetRequestContactDTO getRequestContactDTO;

        if (requestContact != null) {
            getRequestContactDTO = this.objectMapper.convertValue(requestContact, GetRequestContactDTO.class);
        } else {
            throw new DataNotFound("El producto no existe en el inventario. ");
        }
        return getRequestContactDTO;
    }

    private RequestContact convertToEntity(RequestContactDTO requestContactDTO) {
        RequestContact requestContact = new RequestContact();
        requestContact.setHighDate(new Date());
        requestContact.setNameContact(requestContactDTO.getNameContact());
        requestContact.setEmail(requestContactDTO.getEmail());
        requestContact.setReason(requestContactDTO.getReason());
        requestContact.setAttach(convertAttachToBase64(requestContactDTO.getAttach()));
        requestContact.setComment(requestContactDTO.getComment());
        requestContact.setCellphone(requestContactDTO.getCellphone());
        requestContact.setDepartment(requestContactDTO.getDepartment());
        requestContact.setCity(requestContactDTO.getCity());

        return requestContact;
    }

    // Método para convertir MultipartFile a String (base64)
    private String convertAttachToBase64(MultipartFile attach) {
        if (attach != null) {
            try {
                return Base64.getEncoder().encodeToString(attach.getBytes());
            } catch (IOException e) {

                e.printStackTrace();
            }
        }
        return null;
    }
    private GetRequestContactDTO convertToDto(RequestContact requestContact) {
        GetRequestContactDTO getRequestContactDTO = new GetRequestContactDTO();
        getRequestContactDTO.setIdRequest(requestContact.getIdRequest());
        getRequestContactDTO.setNameContact(requestContact.getNameContact());
        getRequestContactDTO.setEmail(requestContact.getEmail());
        getRequestContactDTO.setReason(requestContact.getReason());
        getRequestContactDTO.setCity(requestContact.getCity());
        getRequestContactDTO.setCellphone(requestContact.getCellphone());
        getRequestContactDTO.setDepartment(requestContact.getDepartment());
        getRequestContactDTO.setComment(requestContact.getComment());
        getRequestContactDTO.setAttach(requestContact.getAttach());
        return getRequestContactDTO;
    }

}
