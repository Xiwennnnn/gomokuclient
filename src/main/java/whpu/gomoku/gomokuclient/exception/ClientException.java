package whpu.gomoku.gomokuclient.exception;

import whpu.gomoku.gomokuclient.exception.common.ErrorCode;

public class ClientException extends RuntimeException {
    private final Integer code;
    private String message;

    public ClientException(Integer code, String message) {
        super(message);
        this.code = code;
        this.message = message;
    }

    public ClientException(ErrorCode errorCode) {
        this.code = errorCode.getCode();
        this.message = errorCode.getMessage();
    }
}
