package com.project.back.dto.request.user;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PostUserPointRequestDto {

    @NotNull
    @Min(0) // 포인트가 음수가 아니어야 함
    private int points;
}
