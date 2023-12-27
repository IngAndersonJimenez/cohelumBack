package com.backend.web.dto.InventoryComment;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import javax.persistence.Column;

@Data
@Builder(toBuilder = true)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class InventoryCommentDTO {

    private Integer qualification;
    private boolean active;
    private String review;
    private String name;
    private String email;
    private Integer idInventory;

}
