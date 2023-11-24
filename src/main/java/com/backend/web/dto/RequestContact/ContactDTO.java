package com.backend.web.dto.RequestContact;

import com.backend.domain.entity.generic.ReasonEnum;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.security.cert.CRLReason;

@Data
@Builder(toBuilder = true)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ContactDTO {

    private String nameContact;
    private String email;
    private ReasonEnum reason;
    private String attach;
    private String comment;
    private String cellphone;
    private String department;
    private String city;

}
