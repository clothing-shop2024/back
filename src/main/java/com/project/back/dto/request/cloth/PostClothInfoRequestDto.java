package com.project.back.dto.request.cloth;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostClothInfoRequestDto {
    // 상품ID (고유식별코드)
    @NotNull
    private Integer clothNumber;
    @NotBlank
    private Integer sizeNumber;
    @NotBlank
    private Integer colorNumber;
    // 수량
    // @NotNull
    // private Integer quantity;
    // private String couponCode;
}
