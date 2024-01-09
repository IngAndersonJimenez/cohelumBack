package com.backend.web.dto.SettingTP;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@Data
@Builder(toBuilder = true)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SettingTPDTO {

    private String artefact;
    private String description;
    private String value1;
    private String value2;
    private String value3;

}
