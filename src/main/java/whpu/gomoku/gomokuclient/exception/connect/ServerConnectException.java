package whpu.gomoku.gomokuclient.exception.connect;

public class ServerConnectException extends RuntimeException {
    public ServerConnectException() {
        super();
    }
    public ServerConnectException(String message) {
        super(message);
    }
    public ServerConnectException(String message, Throwable cause) {
        super(message, cause);
    }
}
