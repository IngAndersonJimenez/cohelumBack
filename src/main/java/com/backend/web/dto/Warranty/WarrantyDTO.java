package com.backend.web.dto.Warranty;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;


@Data
@Builder(toBuilder = true)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class WarrantyDTO {


    private String nameContact;
    private String typeDocument;
    private String document;
    private String email;
    private String attach;
    private String distributor;
    private String number_bill;
    private String cellphone;
    private String date;
    private String product;

}
