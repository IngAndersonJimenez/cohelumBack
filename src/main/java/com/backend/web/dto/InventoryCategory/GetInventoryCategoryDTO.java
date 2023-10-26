package com.backend.web.dto.InventoryCategory;

import com.backend.web.dto.Inventory.InventoryDTO;
import lombok.*;

@Data
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class GetInventoryCategoryDTO extends InventoryCategoryDTO {

    private Integer idInventoryCategory;

}
