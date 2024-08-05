package com.project.back.common.util;

// 패턴 인터페이스
public interface PatternUtil {

    String PW_PATTERN =("^(?=.*[a-zA-Z])(?=.*[0-9]).{8,13}$");
    String EMAIL_PATTERN = ("^[a-zA-Z0-9]*@([-.]?[a-zA-Z0-9])*\\.[a-zA-Z]{2,4}$");
    String ADDRESS_PATTERN = ("(([가-힣A-Za-z·\\d~\\-\\.]{2,}(로|길)\\.\\d+)|([가-힣A-Za-z·\\d~\\-\\.]+(읍|동)\\s)\\d+)");
}
