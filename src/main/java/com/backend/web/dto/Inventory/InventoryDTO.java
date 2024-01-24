package com.backend.web.dto.Inventory;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.math.BigDecimal;

@Data
@Builder(toBuilder = true)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class InventoryDTO {

    private String name;
    private boolean active;
    private BigDecimal price;
    private Integer unitsAvailable;
    private Integer idSubCategory;
    private Integer discount;
    private String reference;

}
