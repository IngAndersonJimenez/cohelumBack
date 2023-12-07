package com.backend.web.dto.Inventory;

import com.backend.web.dto.InventoryCategory.GetInventoryCategoryDTO;
import com.backend.web.dto.InventoryDetails.GetInventoryDetailsDTO;
import com.backend.web.dto.InventoryImage.GetInventoryImageDTO;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class GetInventoryFullDTO {

    private GetInventoryDTO getInventoryDTO;
    private GetInventoryDetailsDTO getInventoryDetailsDTO;
    private GetInventoryCategoryDTO getInventoryCategoryDTO;
    private List<GetInventoryImageDTO> getInventoryImageDTO;

}
