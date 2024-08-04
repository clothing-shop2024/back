package com.project.back.dto.request.notice;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PutNoticeRequestDto {

    @NotBlank
    private String noticeTitle;
    @NotBlank
    private String noticeContents;
    private String noticeImageUrl;
    
}
