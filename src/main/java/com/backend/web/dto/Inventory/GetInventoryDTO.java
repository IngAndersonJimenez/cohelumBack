package com.backend.web.dto.Inventory;

import lombok.*;

@Data
@Builder(toBuilder = true)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class GetInventoryDTO extends InventoryDTO {

    private Integer idInventory;

}
