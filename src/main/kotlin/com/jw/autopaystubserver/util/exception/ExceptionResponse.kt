package com.jw.autopaystubserver.util.exception

import org.springframework.http.HttpStatus

enum class ExceptionResponse (
    val status: HttpStatus,
    val code: Int,
    val message: String
) {

    // 400 BAD REQUEST
    INVALID_BODY_FORMAT(
        status = HttpStatus.BAD_REQUEST,
        code = 100,
        message = "Body 형식이 잘못되었습니다."
    ),
    DUPLICATED_EMAIL(
        status = HttpStatus.BAD_REQUEST,
        code = 101,
        message = "이미 존재하는 계정입니다."
    ),
    DUPLICATED_AGENCY(
        status = HttpStatus.BAD_REQUEST,
        code = 102,
        message = "이미 존재하는 에이전시입니다."
    ),
    DUPLICATED_COMPANY(
        status = HttpStatus.BAD_REQUEST,
        code = 103,
        message = "이미 존재하는 회사입니다."
    ),
    DUPLICATED_WORKER(
        status = HttpStatus.BAD_REQUEST,
        code = 104,
        message = "이미 존재하는 근로자입니다."
    ),

    // 401 Unauthorized
    PASSWORD_MISMATCH(
        status = HttpStatus.UNAUTHORIZED,
        code = 200,
        message = "잘못된 비밀번호입니다."
    ),
    EXPIRED_TOKEN(
        status = HttpStatus.UNAUTHORIZED,
        code = 201,
        message = "만료된 토큰입니다."
    ),
    USER_NOT_FOUND(
        status = HttpStatus.UNAUTHORIZED,
        code = 202,
        message = "존재하지 않는 사용자입니다."
    ),
    AGENCY_NOT_FOUND(
        status = HttpStatus.UNAUTHORIZED,
        code = 203,
        message = "존재하지 않는 에이전시입니다."
    ),
    COMPANY_NOT_FOUND(
        status = HttpStatus.UNAUTHORIZED,
        code = 204,
        message = "존재하지 않는 회사입니다."
    ),
    WORKER_NOT_FOUND(
        status = HttpStatus.UNAUTHORIZED,
        code = 205,
        message = "존재하지 않는 근로자입니다."
    ),
    FORMAT_NOT_FOUND(
        status = HttpStatus.UNAUTHORIZED,
        code = 206,
        message = "존재하지 않는 포맷입니다."
    )

    ;

//    error response body
    val body = mapOf(
        "error" to mapOf(
            "code" to code,
            "message" to message
        )
    )
}