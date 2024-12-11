package whpu.gomoku.gomokuclient.service;

import lombok.extern.slf4j.Slf4j;
import whpu.gomoku.gomokuclient.GomokuContext;
import whpu.gomoku.gomokuclient.data.event.*;
import whpu.gomoku.gomokuclient.util.JsonUtil;

import static whpu.gomoku.gomokuclient.GomokuContext.webSocket;

@Slf4j
public class RoomService {

    public static void startGame() {
        StartGameEvent event = new StartGameEvent();
        event.setRoomId(GomokuContext.currentRoom.getRoomId());
        try {
            webSocket.send(JsonUtil.turnObjectToJson(event));
        } catch (Exception e) {
            log.error("Error while sending start game event: {}", e.getMessage());
        }
    }

    public static void createRoom() {
        CreateRoomEvent event = new CreateRoomEvent();
        try {
            webSocket.send(JsonUtil.turnObjectToJson(event));
        } catch (Exception e) {
            log.error("Error while sending create room event: {}", e.getMessage());
        }
    }

    public static void joinRoom(Long roomId) {
        JoinRoomEvent event = new JoinRoomEvent(roomId);
        try {
            webSocket.send(JsonUtil.turnObjectToJson(event));
        } catch (Exception e) {
            log.error("Error while sending join room event: {}", e.getMessage());
        }
    }

    public static void quitRoom(Long roomId) {
        QuitRoomEvent event = new QuitRoomEvent(roomId);
        try {
            webSocket.send(JsonUtil.turnObjectToJson(event));
        } catch (Exception e) {
            log.error("Error while sending quit room event: {}", e.getMessage());
        }
    }

    public static void getRoomList() {
        GetRoomListEvent event = new GetRoomListEvent();
        try {
            webSocket.send(JsonUtil.turnObjectToJson(event));
        } catch (Exception e) {
            log.error("Error while sending get room list event: {}", e.getMessage());
        }
    }
}
