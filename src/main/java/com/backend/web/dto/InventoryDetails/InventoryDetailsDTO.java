package com.backend.web.dto.InventoryDetails;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
@Data
@Builder(toBuilder = true)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class InventoryDetailsDTO {

    private String characteristic;
    private String datasheet;
    private Integer idInventory;

}
