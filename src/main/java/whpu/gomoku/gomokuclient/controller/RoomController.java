package whpu.gomoku.gomokuclient.controller;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import lombok.extern.slf4j.Slf4j;
import whpu.gomoku.gomokuclient.GomokuContext;
import whpu.gomoku.gomokuclient.fxmlrender.HomeRender;
import whpu.gomoku.gomokuclient.service.RoomService;

import java.util.Objects;

import static whpu.gomoku.gomokuclient.GomokuContext.currentRoom;
import static whpu.gomoku.gomokuclient.GomokuContext.user;

@Slf4j
public class RoomController {
    public Button BeginBtn;
    public Button prepareBtn;
    public ImageView ownerAvatar;
    public ImageView playAvatar;
    public ImageView backHome;
    public Label playerId;
    public Label ownerId;
    public ChoiceBox<String> roomStatus;
    public Label ownerStatus;
    public Label playerStatus;
    public Rectangle player2;


    public void initialize() {
        GomokuContext.roomController = this;
        user.setStatus(2);
        BeginBtn.setDisable(!Objects.equals(currentRoom.getOwnerId(), user.getId()));
        updateRoomInfo();
    }

    public void updateRoomInfo() {
        Platform.runLater(() -> {
            log.info("update room info: {}", currentRoom);
            ownerId.setText(currentRoom.getOwnerHandle());
            playerId.setText(currentRoom.getPlayerHandle());
            if (currentRoom.getOwnerId() != null) {
                ownerStatus.setText("已连接");
                player2.setFill(Color.GREEN);
            } else {
                ownerStatus.setText("未连接");
                player2.setFill(Color.RED);
            }
            if (currentRoom.getPlayerId() != null) {
                playerStatus.setText("已连接");
                player2.setFill(Color.GREEN);
            } else {
                playerStatus.setText("未连接");
                player2.setFill(Color.RED);
            }
            if (Objects.equals(currentRoom.getOwnerId(), user.getId()) && currentRoom.getPlayerCount() == 2) {
                BeginBtn.setDisable(false);
                BeginBtn.setStyle("-fx-background-color: #00FF00");
            } else {
                BeginBtn.setDisable(true);
                BeginBtn.setStyle("-fx-background-color: rgba(246,246,244,0.5)");
            }
        });
    }

    public void StartGame(ActionEvent actionEvent) {
        RoomService.startGame();
    }

    public void returnHome(MouseEvent mouseEvent) {
        RoomService.quitRoom(currentRoom.getRoomId());
        HomeRender.showHome();
    }
}
