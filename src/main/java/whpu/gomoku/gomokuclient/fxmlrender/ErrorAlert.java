package whpu.gomoku.gomokuclient.fxmlrender;

import javafx.application.Platform;
import javafx.scene.control.Alert;

public class ErrorAlert {
    public static void show(String message) {
        Platform.runLater(() -> {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("错误");
            alert.setResizable(false);
            alert.setHeaderText(null);
            alert.setContentText(message);
            alert.showAndWait();
        });
    }
}
