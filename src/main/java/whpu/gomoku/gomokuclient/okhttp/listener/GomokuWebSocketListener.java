package whpu.gomoku.gomokuclient.okhttp.listener;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import okhttp3.Response;
import okhttp3.WebSocket;
import okhttp3.WebSocketListener;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import whpu.gomoku.gomokuclient.GomokuContext;
import whpu.gomoku.gomokuclient.data.response.AbstractResponse;
import whpu.gomoku.gomokuclient.data.response.ResponseFactory;
import whpu.gomoku.gomokuclient.exception.common.ErrorCode;
import whpu.gomoku.gomokuclient.fxmlrender.ErrorAlert;
import whpu.gomoku.gomokuclient.fxmlrender.LoginRender;
import whpu.gomoku.gomokuclient.service.GomokuBusiness;
import whpu.gomoku.gomokuclient.service.RoomService;

@Slf4j
public class GomokuWebSocketListener extends WebSocketListener {
    ThreadLocal<ObjectMapper> objMapperThreadLocal = ThreadLocal.withInitial(ObjectMapper::new);
    @Override
    public void onOpen(@NotNull WebSocket webSocket, @NotNull Response response) {
        GomokuContext.webSocket = webSocket;
        log.info("WebSocket Opened: {}", response);
        RoomService.getRoomList();
    }

    @Override
    public void onMessage(@NotNull WebSocket webSocket, @NotNull String text) {
        try {
            log.info("WebSocket Message Received: {}", text);
            JsonNode json = objMapperThreadLocal.get().readTree(text);
            if (json.get("code").asInt() != 200) {
                log.error("WebSocket Message Error: {}", json.get("message").asText());
                ErrorAlert.show(json.get("data").asText());
                return;
            }
            AbstractResponse response = ResponseFactory.createResponse(json);
            GomokuBusiness.doBusiness(response);
        } catch (Exception e) {
            log.error("WebSocket Message Error: {}", e.getMessage());
        }
    }

    @Override
    public void onFailure(@NotNull WebSocket webSocket, @NotNull Throwable t, @Nullable Response response) {
        log.error("WebSocket Connection Failure: {}", response, t);
        if (response == null) {
            webSocket.close(ErrorCode.SERVER_ERROR.getCode(), ErrorCode.SERVER_ERROR.getMessage());
            return;
        }
        switch (response.code()) {
            case 401 -> LoginRender.showLoginFXML(null);
            case 402 -> LoginRender.showLoginFXML(ErrorCode.TOKEN_EXPIRED.getMessage());
            case 403 -> LoginRender.showLoginFXML(ErrorCode.TOKEN_INVALID.getMessage());
            default -> LoginRender.showLoginFXML(ErrorCode.SERVER_ERROR.getMessage());
        }
    }

    @Override
    public void onClosed(@NotNull WebSocket webSocket, int code, @NotNull String reason) {
        log.info("WebSocket Closed: {} - {}", code, reason);
    }

    @Override
    public void onClosing(@NotNull WebSocket webSocket, int code, @NotNull String reason) {
        log.info("WebSocket Closing: {} - {}", code, reason);
        webSocket.close(code, reason);
    }
}
