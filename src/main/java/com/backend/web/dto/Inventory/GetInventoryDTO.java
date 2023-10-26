package com.backend.web.dto.Inventory;

import lombok.*;

import java.util.Date;

@Data
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class GetInventoryDTO extends InventoryDTO {

    private Integer idInventory;
    private Date highDate;
    private Date modificationDate;

}
