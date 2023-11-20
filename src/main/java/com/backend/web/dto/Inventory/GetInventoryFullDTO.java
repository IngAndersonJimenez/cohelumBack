package com.backend.web.dto.Inventory;

import com.backend.web.dto.InventoryCategory.GetInventoryCategoryDTO;
import com.backend.web.dto.InventoryDetails.GetInventoryDetailsDTO;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class GetInventoryFullDTO {

    private GetInventoryDTO getInventoryDTO;
    private GetInventoryDetailsDTO getInventoryDetailsDTO;
    private GetInventoryCategoryDTO getInventoryCategoryDTO;

}
