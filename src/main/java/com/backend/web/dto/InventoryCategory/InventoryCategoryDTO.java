package com.backend.web.dto.InventoryCategory;

import lombok.*;

@Data
@Builder(toBuilder = true)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class InventoryCategoryDTO {

    private String description;

    private boolean active;

}
