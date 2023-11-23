package com.backend.web.dto.CategoryImage;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

@Data
@Builder(toBuilder = true)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CategoryImageDTO {

    private MultipartFile image;

    private boolean active;

    private Integer idCategory;

}
