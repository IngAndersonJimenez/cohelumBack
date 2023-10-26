package com.backend.web.dto.Inventory;

import lombok.Builder;
import lombok.Data;

@Data
@Builder(toBuilder = true)
public class ResponseInventoryDTO {

    private GetInventoryDTO getInventoryDTO;

}
