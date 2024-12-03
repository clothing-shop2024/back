package com.project.back.common.object;

import java.util.ArrayList;
import java.util.List;

import com.project.back.common.util.ChangeDateFormatUtil;
import com.project.back.repository.resultSet.GetClothReviewListItemResultSet;

public class ClothReviewListItem {
    private double rating;
    private Integer reviewNumber;
    private Integer reviewClothId;
    private String reviewDate;
    private String reviewImage;
    private String reviewContents;
    private String reviewWriterNickname;
    private String reviewClothName;

    private ClothReviewListItem(GetClothReviewListItemResultSet getClothReviewListItemResultSet) throws Exception {
        this.rating = getClothReviewListItemResultSet.getRating();
        this.reviewImage = getClothReviewListItemResultSet.getReviewImage();
        this.reviewNumber = getClothReviewListItemResultSet.getReviewNumber();
        this.reviewContents = getClothReviewListItemResultSet.getReviewContents();
        this.reviewClothId = getClothReviewListItemResultSet.getReviewClothId();
        this.reviewWriterNickname = getClothReviewListItemResultSet.getReviewWriterNickname();
        this.reviewClothName = getClothReviewListItemResultSet.getReviewClothName();

        if (getClothReviewListItemResultSet.getReviewDate() != null) {
            String writeDatetime = ChangeDateFormatUtil
                    .changeYYMMDD(getClothReviewListItemResultSet.getReviewDate());
            this.reviewDate = writeDatetime;
        }
    }

    public static List<ClothReviewListItem> getList(
            List<GetClothReviewListItemResultSet> getClothReviewListItemResultSetList) throws Exception {
        List<ClothReviewListItem> clothReviewList = new ArrayList<>();

        for (GetClothReviewListItemResultSet getClothReviewListItemResultSet : getClothReviewListItemResultSetList) {
            ClothReviewListItem clothReviewListItem = new ClothReviewListItem(getClothReviewListItemResultSet);
            clothReviewList.add(clothReviewListItem);
        }
        return clothReviewList;
    }
}