package com.project.back.dto.request.cloth.favorite;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PostFavoriteClothRequestDto {
    @NotBlank
    private String userId;
    // 찜 옷 고유번호
    @NotBlank
    private Integer clothId;
    // 찜한 옷 번호
    // @NotBlank
    // private Integer favoriteClothNumber;
    // 상품번호
    @NotBlank
    private String clothDetailNumber;
}
