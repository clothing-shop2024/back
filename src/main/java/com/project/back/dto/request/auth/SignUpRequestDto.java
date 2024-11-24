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
public class SignUpRequestDto {

    @NotBlank
    private String userId;

    @NotNull
    @Pattern(regexp = PatternUtil.PW_PATTERN)
    private String password;

    @NotBlank
    private String nickname;

    @NotBlank
    private String userName;

    @NotBlank
    @Pattern(regexp = PatternUtil.EMAIL_PATTERN)
    private String userEmail;

    @NotBlank
    private String authNumber;

    private String userBirthDay;

    private Boolean solarLunarCalendar;

    @NotBlank
    private String joinPath;

}