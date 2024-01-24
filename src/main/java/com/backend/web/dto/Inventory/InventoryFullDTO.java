package com.backend.web.dto.Inventory;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.util.List;

@Data
@Builder(toBuilder = true)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class InventoryFullDTO {

    private String name;
    private BigDecimal price;
    private Integer unitsAvailable;
    private Integer idSubCategory;
    private String characteristic;
    private MultipartFile datasheet;
    private List<MultipartFile> image;
    private Integer discount;
    private String reference;

}
