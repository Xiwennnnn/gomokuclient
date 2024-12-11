package whpu.gomoku.gomokuclient.fxmlrender;

import cn.hutool.core.io.resource.ClassPathResource;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import org.kordamp.bootstrapfx.BootstrapFX;
import whpu.gomoku.gomokuclient.controller.LoginController;

import java.io.IOException;

import static whpu.gomoku.gomokuclient.GomokuContext.stage;

public class LoginRender {
    public static void showLoginFXML(String errorMsg) {
        Platform.runLater(() -> {
            Parent root = null;
            FXMLLoader fxmlLoader;
            try {
                fxmlLoader = new FXMLLoader(new ClassPathResource("fxml/login-scene.fxml").getUrl());
                root = fxmlLoader.load();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            LoginController loginController = fxmlLoader.getController();
            if (errorMsg != null && !errorMsg.isEmpty()) {
                loginController.errorMsg.setText(errorMsg);
                loginController.errorMsg.setVisible(true);
            }
            Scene scene = new Scene(root);
            scene.getStylesheets().add(BootstrapFX.bootstrapFXStylesheet());
            stage.setScene(scene);
            stage.setResizable(false);
            stage.show();
        });
    }

}
