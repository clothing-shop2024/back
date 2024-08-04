package com.project.back.common.object;

import java.util.ArrayList;
import java.util.List;

import com.project.back.common.util.ChangeDateFormatUtil;
import com.project.back.entity.NoticeEntity;

import lombok.Getter;

@Getter
public class NoticeListItem {

    private Integer noticeNumber;
    private String noticeTitle;
    private String noticeDate;
    private Integer viewCount;
    private String noticeWriterId;

    private NoticeListItem(NoticeEntity noticeEntity) throws Exception {
        
        String noticeDate = ChangeDateFormatUtil.changeYYYYMMDD(noticeEntity.getNoticeDate());

        this.noticeNumber = noticeEntity.getNoticeNumber();
        this.noticeTitle = noticeEntity.getNoticeTitle();
        this.noticeDate = noticeDate;
        this.viewCount = noticeEntity.getViewCount();
        this.noticeWriterId = noticeEntity.getNoticeWriterId();

    }

    public static List<NoticeListItem> getList(List<NoticeEntity> noticeEntities) throws Exception {
        
        List<NoticeListItem> noticeList = new ArrayList<>();

        for (NoticeEntity noticeEntity : noticeEntities) {
            
            NoticeListItem noticeListItem = new NoticeListItem(noticeEntity);
            noticeList.add(noticeListItem);
            
        }

        return noticeList;
    }

}
