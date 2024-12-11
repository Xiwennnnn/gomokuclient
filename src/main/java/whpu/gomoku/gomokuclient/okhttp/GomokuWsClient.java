package whpu.gomoku.gomokuclient.okhttp;

import cn.hutool.http.Header;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.*;
import whpu.gomoku.gomokuclient.data.common.GomokuConstant;
import whpu.gomoku.gomokuclient.exception.ClientException;
import whpu.gomoku.gomokuclient.exception.common.ErrorCode;
import whpu.gomoku.gomokuclient.okhttp.listener.GomokuWebSocketListener;
import whpu.gomoku.gomokuclient.util.GomokuUtil;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public final class GomokuWsClient {
    private static WebSocket webSocket;
    private static OkHttpClient client;
    private static final String WS_URL = GomokuConstant.WS_SERVER_HOST;
    private static final ThreadLocal<ObjectMapper> objMapperThreadLocal = ThreadLocal.withInitial(ObjectMapper::new);

    public static void connect() throws InterruptedException {
        client = getClient();
        String token = GomokuUtil.loadToken();
        Request request = new Request.Builder().get()
                .url(WS_URL)
                .addHeader(Header.AUTHORIZATION.getValue(), token)
                .build();
        webSocket = client.newWebSocket(request, new GomokuWebSocketListener());
    }

    public static String login(String username, String password) throws IOException, ClientException {
        OkHttpClient okHttpClient = getClient();
        Map<String, String> loginData = Map.of(
                "username", username,
                "password", password
        );
        String jsonBody = objMapperThreadLocal.get().writeValueAsString(loginData);
        RequestBody requestBody = RequestBody.create(jsonBody, MediaType.parse("application/json"));
        Request loginRequest = new Request.Builder()
                .url(GomokuConstant.LOGIN_URL)
                .post(requestBody)
                .build();
        try (Response response = okHttpClient.newCall(loginRequest).execute()) {
            if (response.isSuccessful()) {
                return objMapperThreadLocal.get().readTree(response.body().string())
                        .get("data")
                        .get("token")
                        .asText();
            } else {
                throw new ClientException(ErrorCode.USERNAME_OR_PASSWORD_ERROR);
            }
        } finally {
            okHttpClient.dispatcher().executorService().shutdown();
        }

    }

    public static String register(String username, String password) throws IOException, ClientException {
        OkHttpClient okHttpClient = getClient();
        Map<String, String> registerData = Map.of(
                "username", username,
                "password", password
        );
        String jsonBody = objMapperThreadLocal.get().writeValueAsString(registerData);
        RequestBody requestBody = RequestBody.create(jsonBody, MediaType.parse("application/json"));
        Request registerRequest = new Request.Builder()
                .url(GomokuConstant.REGISTER_URL)
                .post(requestBody)
                .build();
        try (Response response = okHttpClient.newCall(registerRequest).execute()) {
            if (response.isSuccessful()) {
                return objMapperThreadLocal.get().readTree(response.body().string())
                        .get("data")
                        .get("token")
                        .asText();
            } else {
                throw new ClientException(ErrorCode.USER_ALREADY_EXIST);
            }
        } finally {
            okHttpClient.dispatcher().executorService().shutdown();
        }
    }

    public static void disconnect() {
        if (webSocket != null)  webSocket.close(1000, "Client closed");
        if (client != null) client.dispatcher().executorService().shutdown();
    }

    private static OkHttpClient getClient() {
        return new OkHttpClient.Builder()
                .readTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS)
                .connectTimeout(10, TimeUnit.SECONDS)
                .build();
    }
}
