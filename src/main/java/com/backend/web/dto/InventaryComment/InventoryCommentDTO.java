package com.backend.web.dto.InventaryComment;

import lombok.*;

@Data
@Builder(toBuilder = true)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class InventoryCommentDTO {

    private Integer qualification;
    private boolean active;

}
