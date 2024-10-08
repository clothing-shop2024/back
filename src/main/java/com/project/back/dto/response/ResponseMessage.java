package com.project.back.dto.response;

// 200 성공 : SU/ Success
// 400 필수 데이터 미입력 VF/ Validation Failed.
// 400 중복된 아이디 : DI / Duplicated Id.
// 400 중복된 이메일 : DE / Duplicated Email.
// 401 로그인 정보 불일치 : SF/ Sign in Failed.
// 401 이메일 인증 실패 : AF / Authentication Failed.
// 500 토큰 생성 실패 : TF / Token creation Failed.
// 500 이메일 전송 실패: MF / Mail send Failed.
// 500 데이터베이스 오류 : DBE / Database Error.

// Response의 공통된 message 값
public interface ResponseMessage {

    String SUCCESS = "Success.";
    String VALIDATION_FAILED = "Validation Failed.";
    String DUPLICATED_ID = "Duplicated Id.";
    String DUPLICATED_NICKNAME = "Duplicated Nickname.";
    String DUPLICATED_EMAIL = "Duplicated Email.";
    String NO_EXIST_EMAIL = "No Exist Email.";
    String NO_EXIST_USER_ID = "No Exist User Id.";
    String NO_EXIST_BOARD = "No Exist Board.";
    String NO_EXIST_RESERVATION = "No Exist Reservation.";
    String NO_CANCEL_STATE = "No Cancel State.";
    String NO_WAITING_STATE = "No Waiting State.";
    String NO_EXIST_VEHICLE = "No Exist Vehicle.";
    String NO_EXIST_COMPANY = "No Exist Company.";
    String NO_EXIST_ADDRESS = "No Exist Address.";
    String REGISTRATION_COMPANY = "Registration Company.";
    String NO_EXIST_INFORMATION = "No Exist Information.";
    String NO_EXIST_USER = "No Exist User.";
    String SIGN_IN_FAILED = "Sign in Failed.";
    String AUTHENTICATION_FAILED = "Authentication Failed.";
    String AUTHORIZATION_FAILED = "Authorization Failed.";
    String NOT_FOUND = "Not Found.";
    String TOKEN_CREATION_FAILED = "Token creation Failed.";
    String MAIL_SEND_FAILED = "Mail send Failed.";
    String DATABASE_ERROR = "Database Error.";
    String INVALID_REQUEST = "Invalid Request";
}
