package com.project.back.dto.request.qna;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PutQnaCommentRequestDto {

    @NotBlank
    private String qnaComment;
    
}
