package whpu.gomoku.gomokuclient.fxmlrender;

import cn.hutool.core.io.resource.ClassPathResource;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static whpu.gomoku.gomokuclient.GomokuContext.stage;

public class RoomRender {
    private static final Logger log = LoggerFactory.getLogger(RoomRender.class);

    public static void showRoom() {
        Platform.runLater(() -> {
            Parent root = null;
            try {
                root = FXMLLoader.load(new ClassPathResource("fxml/room-scene.fxml").getUrl());
            } catch (Exception e) {
                log.error("Failed to load room scene", e);
                return;
            }
            Scene scene = new Scene(root);
            log.info("Showing room scene");
            stage.setScene(scene);
            stage.show();
        });
    }
}
