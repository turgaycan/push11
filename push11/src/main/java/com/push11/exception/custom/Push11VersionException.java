package com.push11.exception.custom;

public class Push11VersionException extends Push11Exception {
    private static final long serialVersionUID = -8261346259569195517L;

    public Push11VersionException(String message) {
        super(message);
    }

    public Push11VersionException(ErrorCode errorCode) {
        super(errorCode);
    }

    public Push11VersionException(String message, ErrorCode errorCode) {
        super(message, errorCode);
    }

}
