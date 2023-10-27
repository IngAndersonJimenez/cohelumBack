package com.backend.web.dto.Generic;

import lombok.Builder;
import lombok.Data;

@Data
@Builder(toBuilder = true)
public class ResponseDTO {

    private Object responseDTO;

}
