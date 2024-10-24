package com.project.back.dto.request.user;

import com.project.back.common.util.PatternUtil;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PutPasswordModifyRequestDto {

    @NotBlank
    @Pattern(regexp = PatternUtil.PW_PATTERN)
    private String password;
}
