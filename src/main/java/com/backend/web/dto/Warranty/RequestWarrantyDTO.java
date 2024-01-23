package com.backend.web.dto.Warranty;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;


@Data
@Builder(toBuilder = true)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RequestWarrantyDTO {

    private String nameContact;
    private String typeDocument;
    private String document;
    private String email;
    private MultipartFile attach;
    private String distributor;
    private String number_bill;
    private String cellphone;
    private String date;
    private String product;
}
