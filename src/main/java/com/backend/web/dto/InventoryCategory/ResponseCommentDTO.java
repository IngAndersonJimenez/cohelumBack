package com.backend.web.dto.InventoryCategory;

import lombok.Builder;
import lombok.Data;

@Data
@Builder(toBuilder = true)
public class ResponseCommentDTO {

    private GetInventoryCategoryDTO getInventoryCategoryDTO;

}
