package whpu.gomoku.gomokuclient.action;

import javafx.application.Platform;
import javafx.scene.control.Alert;
import whpu.gomoku.gomokuclient.data.common.ActionEnum;
import whpu.gomoku.gomokuclient.data.response.GameOverResponse;
import whpu.gomoku.gomokuclient.fxmlrender.RoomRender;

public class GameOverAction implements Action<GameOverResponse> {
    @Override
    public String getActionType() {
        return ActionEnum.GAME_OVER.getAction();
    }

    @Override
    public void doAction(GameOverResponse response) {
        Platform.runLater(() -> {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("游戏结束");
            alert.setHeaderText(null);
            alert.setResizable(false);
            alert.setContentText("游戏赢家是：" + response.getWinner());
            alert.showAndWait();
            RoomRender.showRoom();
        });
    }
}
