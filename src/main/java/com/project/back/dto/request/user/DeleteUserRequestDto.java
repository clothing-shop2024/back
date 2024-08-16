package com.project.back.dto.request.user;

import com.project.back.common.util.PatternUtil;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public class DeleteUserRequestDto {

    @NotBlank
    @Pattern(regexp = PatternUtil.PW_PATTERN)
    private String password;
}
