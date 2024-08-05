package com.project.back.dto.request.faq;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PutFaqRequestDto {
    
    @NotBlank
    private String faqQuestion;
    @NotBlank
    private String faqAnswer;
    @NotBlank
    private String faqCategory;
    
}
