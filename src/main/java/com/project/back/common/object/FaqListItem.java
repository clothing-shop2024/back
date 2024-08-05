package com.project.back.common.object;

import java.util.ArrayList;
import java.util.List;

import com.project.back.entity.FaqEntity;

import lombok.Getter;

@Getter
public class FaqListItem {
    
    private Integer faqNumber;
    private String faqQuestion;
    private String faqAnswer;
    private String faqCategory;
    private String faqDate;

    private FaqListItem(FaqEntity faqEntity) throws Exception {

        this.faqNumber = faqEntity.getFaqNumber();
        this.faqQuestion = faqEntity.getFaqQuestion();
        this.faqAnswer = faqEntity.getFaqAnswer();
        this.faqCategory = faqEntity.getFaqCategory();
        this.faqDate = faqEntity.getFaqDate();
    }

    public static List<FaqListItem> getFaqList(List<FaqEntity> FaqEntities) throws Exception {

        List<FaqListItem> faqList = new ArrayList<>();

        for (FaqEntity faqEntity : FaqEntities) {
            FaqListItem faqListItem = new FaqListItem(faqEntity);
            faqList.add(faqListItem);
        }

        return faqList;
    }
}
