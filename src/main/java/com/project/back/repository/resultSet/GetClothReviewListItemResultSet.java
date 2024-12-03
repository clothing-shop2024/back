package com.project.back.repository.resultSet;

public interface GetClothReviewListItemResultSet {
    Double getRating();

    Integer getReviewNumber();

    Integer getReviewClothId();

    String getReviewDate();

    String getReviewImage();

    String getReviewContents();

    String getReviewWriterNickname();

    String getReviewClothName();
}
