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
public class PatchUserInfoRequestDto {
    @NotBlank
    @Pattern(regexp = PatternUtil.PW_PATTERN)
    private String password;
    // @NotBlank
    // private String userName;
    @NotBlank
    private String nickname;
    @NotBlank
    @Pattern(regexp = PatternUtil.EMAIL_PATTERN)
    private String userEmail;
    // @NotBlank
    // @Pattern(regexp = PatternUtil.ADDRESS_PATTERN)
    // private String userAddress;
    private String userBirthDay;
}
