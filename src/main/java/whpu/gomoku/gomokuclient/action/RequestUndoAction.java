package whpu.gomoku.gomokuclient.action;

import javafx.application.Platform;
import javafx.event.Event;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;
import whpu.gomoku.gomokuclient.data.common.ActionEnum;
import whpu.gomoku.gomokuclient.data.response.RequestUndoResponse;
import whpu.gomoku.gomokuclient.service.GameService;

import java.util.Optional;

public class RequestUndoAction implements Action<RequestUndoResponse> {
    @Override
    public String getActionType() {
        return ActionEnum.REQUEST_UNDO.getAction();
    }
    @Override
    public void doAction(RequestUndoResponse response) {
        Platform.runLater(() -> {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("对方请求悔棋");
            alert.setHeaderText("对方请求悔棋，是否同意？");
            Optional<ButtonType> result = alert.showAndWait();
            GameService.AgreeUndo(result.isPresent() && result.get() == ButtonType.OK);
        });
    }
}
