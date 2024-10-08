package com.project.back.dto.request.auth;

import com.project.back.common.util.PatternUtil;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class FindPasswordRequestDto {

    @NotNull
    private String userId;

    @NotBlank
    private String userName;

    @NotNull
    @Pattern(regexp = PatternUtil.EMAIL_PATTERN)
    private String userEmail;

    @NotBlank
    private String authNumber;
}
