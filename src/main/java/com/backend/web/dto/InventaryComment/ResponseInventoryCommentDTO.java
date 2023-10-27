package com.backend.web.dto.InventaryComment;

import lombok.Builder;
import lombok.Data;

@Data
@Builder(toBuilder = true)
public class ResponseInventoryCommentDTO {

    private GetInventoryCommentDTO getInventoryCommentDTO;

}
