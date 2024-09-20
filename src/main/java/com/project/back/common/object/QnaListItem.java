package com.project.back.common.object;

import java.util.ArrayList;
import java.util.List;

import com.project.back.common.util.ChangeDateFormatUtil;
import com.project.back.entity.QnaEntity;

import lombok.Getter;

@Getter
public class QnaListItem {
    
    private Integer qnaNumber;
    private String qnaWriterId;
    private String qnaCategory;
    private String qnaDate;
    private boolean status;

    private QnaListItem(QnaEntity qnaEntity) throws Exception {

        String qnaDate = ChangeDateFormatUtil.changeYYYYMMDD(qnaEntity.getQnaDate());

        this.qnaNumber = qnaEntity.getQnaNumber();
        this.status = qnaEntity.getStatus();
        this.qnaWriterId = qnaEntity.getQnaWriterId();
        this.qnaDate = qnaDate;
        this.qnaCategory = qnaEntity.getQnaCategory();

    }

    public static List<QnaListItem> getList(List<QnaEntity> qnaEntities) throws Exception {

        List<QnaListItem> qnaList = new ArrayList<>();

        for (QnaEntity qnaEntity : qnaEntities) {
            QnaListItem qnaListItem = new QnaListItem(qnaEntity);
            qnaList.add(qnaListItem);

        }

        return qnaList;
    }

}
