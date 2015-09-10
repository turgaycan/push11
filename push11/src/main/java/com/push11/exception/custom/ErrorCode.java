package com.push11.exception.custom;


public enum ErrorCode {

    UNKNOWN_EXCEPTION("unknown", "Unknown Exception", 1000),
    VERSION_NOT_SUPPORTED("version", "Version not supported any more..", 2000),
    IO_EXCEPTION("io", "IO Exception", 3000);

    private final String type;
    private final String message;
    private final int code;

    private ErrorCode(String errorType, String errorMessage, int errorCode) {
        this.type = errorType;
        this.message = errorMessage;
        this.code = errorCode;
    }

    public String getType() {
        return type;
    }

    public String getMessage() {
        return message;
    }

    public int getCode() {
        return code;
    }

    @Override
    public String toString() {
        return "ErrorCode{" +
                "type='" + type + '\'' +
                ", message='" + message + '\'' +
                ", code=" + code +
                '}';
    }
}
