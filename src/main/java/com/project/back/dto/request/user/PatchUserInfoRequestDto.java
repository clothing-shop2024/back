package com.project.back.dto.request.user;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PatchUserInfoRequestDto {
    @NotNull
    @Pattern(regexp="^(?=.*[a-zA-Z])(?=.*[0-9]).{8,13}$")
    private String password;
    @NotBlank
    private String userName;
    @NotBlank
    private String nickname;
    @NotBlank
    @Pattern(regexp="^[a-zA-Z0-9]*@([-.]?[a-zA-Z0-9])*\\.[a-zA-Z]{2,4}$")
    private String userEmail;
    // @NotBlank
    // @Pattern(regexp="(([가-힣A-Za-z·\\d~\\-\\.]{2,}(로|길)\\.\\d+)|([가-힣A-Za-z·\\d~\\-\\.]+(읍|동)\\s)\\d+)")
    // private String userAddress;
    private String userBirthDay;
}
