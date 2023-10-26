package com.backend.web.dto.InventoryCategory;

import com.backend.web.dto.Inventory.InventoryDTO;
import lombok.*;

import java.util.Date;

@Data
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class GetInventoryCategoryDTO extends InventoryCategoryDTO {

    private Integer idCategory;
    private Date highDate;
    private Date modificationDate;
}
