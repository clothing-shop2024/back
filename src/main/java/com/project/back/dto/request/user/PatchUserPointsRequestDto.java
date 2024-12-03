package com.project.back.dto.request.user;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PatchUserPointsRequestDto {
    
    @NotNull
    @Min(0)
    private int points;
}
