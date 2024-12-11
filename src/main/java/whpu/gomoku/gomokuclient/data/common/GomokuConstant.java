package whpu.gomoku.gomokuclient.data.common;

public final class GomokuConstant {
    public static final String HTTP_PROTOCOL = "http://";
    public static final String TOKEN_POSITION = "config/token";
//    public static final String SERVER_IP = "101.33.254.198";
public static final String SERVER_IP = "101.33.254.198";
    public static final int SERVER_PORT = 8848;
    public static final String WS_SERVER_HOST = "ws://" + SERVER_IP + ":" + SERVER_PORT + "/ws";
    public static final String LOGIN_URL =  HTTP_PROTOCOL + SERVER_IP + ":" + SERVER_PORT + "/login";
    public static final String REGISTER_URL = HTTP_PROTOCOL + SERVER_IP + ":" + SERVER_PORT + "/register";
}
