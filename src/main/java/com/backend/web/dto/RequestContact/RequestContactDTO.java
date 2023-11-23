package com.backend.web.dto.RequestContact;

import com.backend.domain.entity.generic.Reason;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

@Data
@Builder(toBuilder = true)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RequestContactDTO {

    private String nameContact;
    private String email ;
    private Reason reason;
    private MultipartFile attach;
    private String comment;
    private String cellphone;
    private String department;
    private String city;

}
