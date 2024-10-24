package com.project.back.dto.request.user;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PutEmailModifyRequestDto {

    @NotBlank
    String userEmail;

    @NotBlank
    String authNumber;
}
