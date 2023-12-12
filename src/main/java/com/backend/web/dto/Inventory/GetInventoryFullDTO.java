package com.backend.web.dto.Inventory;

import com.backend.web.dto.InventoryCategory.GetInventoryCategoryDTO;
import com.backend.web.dto.InventoryDetails.GetInventoryDetailsDTO;
import com.backend.web.dto.InventoryImage.GetInventoryImageDTO;
import com.backend.web.dto.InventorySubCategory.GetInventorySubCategoryDTO;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class GetInventoryFullDTO {

    private GetInventoryDTO getInventoryDTO;
    private GetInventoryDetailsDTO getInventoryDetailsDTO;
    private GetInventoryCategoryDTO getInventoryCategoryDTO;
    private GetInventorySubCategoryDTO getInventorySubCategoryDTO;
    private List<GetInventoryImageDTO> getInventoryImageDTO;

}
