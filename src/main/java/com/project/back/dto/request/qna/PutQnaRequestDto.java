package com.project.back.dto.request.qna;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PutQnaRequestDto {

    @NotBlank
    private String qnaTitle;
    @NotBlank
    private String qnaContents;
    @NotBlank
    private String qnaCategory;
    private String qnaImageUrl;
    @NotNull
    private Boolean qnaPublic;
    
}
