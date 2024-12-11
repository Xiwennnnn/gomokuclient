package whpu.gomoku.gomokuclient.exception.common;

public enum ErrorCode {

    // 客户端错误401 - INF
    TOKEN_FILE_CREATE_FAILED(401, "创建token文件失败"),
    TOKEN_FILE_READ_FAILED(402, "读取token文件失败"),
    TOKEN_FILE_WRITE_FAILED(403, "写入token文件失败"),
    USERNAME_OR_PASSWORD_ERROR(404, "用户名或密码错误"),
    USER_ALREADY_EXIST(405, "用户名已存在"),

    // websocekt连接关闭
    TOKEN_INVALID(1000, "Token无效，请重新登录"),
    TOKEN_EXPIRED(1000, "Token已过期，请重新登录"),
    USER_NOT_LOGIN(1000, "用户未登录，请先登录"),
    SERVER_ERROR(1000, "服务器出现错误，请稍后再试");

    private final Integer code;
    private final String message;
    ErrorCode(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
