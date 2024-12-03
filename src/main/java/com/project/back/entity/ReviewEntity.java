package com.project.back.entity;

import com.project.back.common.util.ChangeDateFormatUtil;
import com.project.back.dto.request.cloth.review.PatchReviewRequestDto;
import com.project.back.dto.request.cloth.review.PostReviewRequestDto;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "review")
@Table(name = "review")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReviewEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer reviewNumber;
    private Integer reviewClothId;
    private Double rating;
    private String reviewDate;
    private String reviewImage;
    private String reviewContents;
    private String reviewWriterId;
    private String reviewWriterNickname;
    private String reviewClothName;

    public ReviewEntity(PostReviewRequestDto dto, String userId, int clothId, UserEntity userEntity,
            ClothEntity clothEntity) {
        String dateNow = ChangeDateFormatUtil.nowYYYYMMDD();
        this.reviewDate = dateNow;

        this.reviewWriterId = userId;
        this.reviewClothId = clothId;

        this.rating = dto.getRating();
        this.reviewImage = dto.getReviewImage();
        this.reviewContents = dto.getReviewContents();
        this.reviewWriterNickname = userEntity.getNickname();
        this.reviewClothName = clothEntity.getClothName();
    }

    public void updateReview(PatchReviewRequestDto dto) {
        this.rating = dto.getRating();
        this.reviewImage = dto.getReviewImage();
        this.reviewContents = dto.getReviewContents();
    }
}
