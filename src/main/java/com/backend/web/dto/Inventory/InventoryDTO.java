package com.backend.web.dto.Inventory;

import lombok.*;

@Data
@Builder(toBuilder = true)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class InventoryDTO {

    private String name;
    private boolean active;
    private Integer price;
    private Integer unitsAvailable;

}
