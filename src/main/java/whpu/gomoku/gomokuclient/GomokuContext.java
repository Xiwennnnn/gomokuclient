package whpu.gomoku.gomokuclient;


import javafx.stage.Stage;
import okhttp3.WebSocket;
import whpu.gomoku.gomokuclient.controller.GameController;
import whpu.gomoku.gomokuclient.controller.HomeController;
import whpu.gomoku.gomokuclient.controller.RoomController;
import whpu.gomoku.gomokuclient.data.view.Game;
import whpu.gomoku.gomokuclient.data.view.Room;
import whpu.gomoku.gomokuclient.data.view.User;

import java.util.List;

public final class GomokuContext {
    public static WebSocket webSocket;
    public static User user;
    public static Stage stage;
    public static List<Room> rooms;
    public static Room currentRoom;
    public static Game currentGame;

    public static HomeController homeController;
    public static RoomController roomController;
    public static GameController gameController;
}
