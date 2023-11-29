package com.backend.web.dto.InventoryCategory;

import com.backend.web.dto.CategoryImage.GetCategoryImageDTO;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@Data
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@Builder(toBuilder = true)
public class CategoryFullDTO {
    private GetInventoryCategoryDTO getInventoryCategoryDTO;
    private GetCategoryImageDTO getCategoryImageDTO;
}
