package com.backend.web.dto.InventoryDetails;

import lombok.Builder;
import lombok.Data;

@Data
@Builder(toBuilder = true)
public class ResponseInventoryDetailsDTO {

    private GetInventoryDetailsDetailsDTO getInventoryDetailsDTO;

}
