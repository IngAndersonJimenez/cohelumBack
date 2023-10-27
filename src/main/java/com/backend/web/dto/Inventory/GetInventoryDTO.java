package com.backend.web.dto.Inventory;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;


@Data
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GetInventoryDTO extends InventoryDTO {

    private Integer idInventory;


}