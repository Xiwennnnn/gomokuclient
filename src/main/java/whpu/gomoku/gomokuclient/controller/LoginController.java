package whpu.gomoku.gomokuclient.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import lombok.extern.slf4j.Slf4j;
import whpu.gomoku.gomokuclient.exception.ClientException;
import whpu.gomoku.gomokuclient.fxmlrender.HomeRender;
import whpu.gomoku.gomokuclient.okhttp.GomokuWsClient;
import whpu.gomoku.gomokuclient.util.GomokuUtil;

import java.io.IOException;

import static whpu.gomoku.gomokuclient.GomokuContext.stage;

@Slf4j
public class LoginController {
    public Button loginButton;
    public Button registerButton;
    public TextField usernameField;
    public PasswordField passwordField;
    public Label errorMsg;

    public void initialize() {
        usernameField.setTextFormatter(inputFormatter());
        passwordField.setTextFormatter(inputFormatter());
    }

    public void handleLogin(ActionEvent actionEvent) {
        String username = usernameField.getText();
        String password = passwordField.getText();
        if (username.isEmpty() || password.isEmpty()) {
            errorMsg.setVisible(true);
            errorMsg.setText("用户名或密码不能为空");
            return;
        }
        String token;
        try {
            token = GomokuWsClient.login(username, password);
        } catch (IOException ioException) {
            errorMsg.setVisible(true);
            errorMsg.setText("网络连接失败，请检查网络连接或稍后再试");
            return;
        } catch (ClientException clientException) {
            errorMsg.setVisible(true);
            errorMsg.setText(clientException.getMessage());
            return;
        }
        GomokuUtil.saveToken(token);
        try {
            GomokuWsClient.connect();
        } catch (Exception exception) {
            errorMsg.setVisible(true);
            errorMsg.setText("服务器连接失败，请稍后再试");
            return;
        }
    }

    public void handleRegister() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/register-scene.fxml"));
        Parent root = loader.load();
        RegisterController registerController = loader.getController();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    private TextFormatter<String> inputFormatter() {
        return new TextFormatter<>(change -> {
            String newText = change.getControlNewText();
            if (newText.length() > 20) {
                errorMsg.setText("用户名或密码最长为20个字符");
                errorMsg.setVisible(true);
                return null;
            }
            if (newText.matches("[a-zA-Z0-9]*")) {
                errorMsg.setVisible(false);
                return change;
            }
            if (!newText.isEmpty()) {
                errorMsg.setText("用户名或密码只能包含字母和数字");
                errorMsg.setVisible(true);
            }
            return null;
        });
    }

}
