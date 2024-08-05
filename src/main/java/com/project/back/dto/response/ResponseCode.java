package com.project.back.dto.response;

public interface ResponseCode {
    String SUCCESS = "SU";
    String VALIDATION_FAILED = "VF";
    String DUPLICATED_ID = "DI";
    String DUPLICATED_NICKNAME = "DN";
    String DUPLICATED_EMAIL = "DE";
    String NO_EXIST_EMAIL = "NE";
    String NO_EXIST_USER_ID = "NUI";
    String NO_EXIST_BOARD = "NB";
    String NO_EXIST_RESERVATION = "NR";
    String NO_CANCEL_STATE = "NCS";
    String NO_WAITING_STATE = "NW";
    String NO_EXIST_VEHICLE = "NV";
    String NO_EXIST_COMPANY = "NC";
    String NO_EXIST_ADDRESS = "NA";
    String REGISTRATION_COMPANY = "RC";
    String NO_EXIST_INFORMATION = "NI";
    String NO_EXIST_USER = "NU";
    String WRITTEN_COMMENT = "WC";
    String SIGN_IN_FAILED = "SF";
    String AUTHENTICATION_FAILED = "AF";
    String AUTHORIZATION_FAILED = "AF";
    String NOT_FOUND = "NF";
    String TOKEN_CREATION_FAILED = "TF";
    String MAIL_SEND_FAILED = "MF";
    String DATABASE_ERROR = "DBE";
}

