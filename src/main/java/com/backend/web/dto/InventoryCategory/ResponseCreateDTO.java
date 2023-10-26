package com.backend.web.dto.InventoryCategory;

import com.backend.web.dto.Inventory.GetInventoryDTO;
import lombok.Builder;
import lombok.Data;

@Data
@Builder(toBuilder = true)
public class ResponseCreateDTO {

    private GetInventoryCategoryDTO getInventoryCategoryDTO;

}
