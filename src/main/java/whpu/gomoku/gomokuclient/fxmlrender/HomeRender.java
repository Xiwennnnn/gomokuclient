package whpu.gomoku.gomokuclient.fxmlrender;

import cn.hutool.core.io.resource.ClassPathResource;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import org.kordamp.bootstrapfx.BootstrapFX;

import java.io.IOException;

import static whpu.gomoku.gomokuclient.GomokuContext.stage;

public class HomeRender {
    public static void showHome() {
        Platform.runLater(() -> {
            Parent root = null;
            FXMLLoader loader = null;
            try {
                loader = new FXMLLoader(new ClassPathResource("fxml/home-scene.fxml").getUrl());
                root = loader.load();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            Scene scene = new Scene(root);
            scene.getStylesheets().add(BootstrapFX.bootstrapFXStylesheet());
            stage.setScene(scene);
            stage.setResizable(false);
            stage.show();
        });
    }
}
