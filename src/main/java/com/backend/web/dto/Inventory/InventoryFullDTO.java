package com.backend.web.dto.Inventory;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

@Data
@Builder(toBuilder = true)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class InventoryFullDTO {

    private String name;
    private Integer price;
    private Integer unitsAvailable;
    private Integer categoryId;
    private String characteristic;
    private String datasheet;
    private MultipartFile image;

}
