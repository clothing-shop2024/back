package com.project.back.dto.request.qna;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PutQnaRequestDto {

    @NotBlank
    private String qnaContents;
    @NotBlank
    private String qnaCategory;
    private String qnaImageUrl;
    
}
