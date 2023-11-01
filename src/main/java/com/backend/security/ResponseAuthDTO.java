package com.backend.security;

import lombok.Builder;
import lombok.Data;

@Data
@Builder(toBuilder = true)
public class ResponseAuthDTO {

    private String token;

}
