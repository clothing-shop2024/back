package com.project.back.dto.request.cloth;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PatchClothInfoRequestDto {
    @NotBlank
    private String clothImage;
    @NotBlank
    private String clothCategory;
    // 제목
    @NotNull
    private String clothName;
    // 특징
    private String clothFeatures;
    private String clothSnsAddress;

    // 상품ID(고유식별코드)
    @NotNull
    private String clothNumber;

    @NotBlank
    private String colorName;
    private Integer colorNumber;
    @NotBlank
    private String sizeName;
    private Integer sizeNumber;

    @NotBlank
    private Integer price;
    private Integer discountPrice;
}
