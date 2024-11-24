package com.project.back.dto.request.user;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PatchUserInfoRequestDto {
    // @NotBlank
    // @Pattern(regexp = PatternUtil.PW_PATTERN)
    // private String password;

    @NotBlank
    private String userName;

    @NotBlank
    private String nickname;

    // @NotBlank
    // @Pattern(regexp = PatternUtil.EMAIL_PATTERN)
    // private String userEmail;

    private String userBirthDay;

    private Boolean solarLunarCalendar;
}
