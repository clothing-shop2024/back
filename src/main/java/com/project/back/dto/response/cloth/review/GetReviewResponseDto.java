package com.project.back.dto.response.cloth.review;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.project.back.dto.response.ResponseCode;
import com.project.back.dto.response.ResponseDto;
import com.project.back.dto.response.ResponseMessage;
import com.project.back.entity.ReviewEntity;

import lombok.Getter;

@Getter
public class GetReviewResponseDto extends ResponseDto {
    private Integer reviewNumber;
    private Integer reviewClothId;
    private Double rating;
    private String reviewContents;
    private String reviewWriterNickname;
    private String reviewDate;
    private String reviewImage;

    private GetReviewResponseDto(ReviewEntity reviewEntity) throws Exception {
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
        this.reviewNumber = reviewEntity.getReviewNumber();
        this.reviewClothId = reviewEntity.getReviewClothId();
        this.rating = reviewEntity.getRating();
        this.reviewContents = reviewEntity.getReviewContents();
        this.reviewWriterNickname = reviewEntity.getReviewWriterNickname();
        this.reviewDate = reviewEntity.getReviewDate();
        this.reviewImage = reviewEntity.getReviewImage();
    }

    public static ResponseEntity<GetReviewResponseDto> success(ReviewEntity reviewEntity) throws Exception {
        GetReviewResponseDto responseBody = new GetReviewResponseDto(reviewEntity);
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }
}
