package whpu.gomoku.gomokuclient.controller;

import cn.hutool.core.io.resource.ClassPathResource;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import whpu.gomoku.gomokuclient.exception.ClientException;
import whpu.gomoku.gomokuclient.fxmlrender.HomeRender;
import whpu.gomoku.gomokuclient.okhttp.GomokuWsClient;
import whpu.gomoku.gomokuclient.util.GomokuUtil;

import java.io.IOException;

public class RegisterController {

    public ImageView returnButton;
    public TextField usernameField;
    public PasswordField passwordField;
    public PasswordField rePasswordField;
    public Label errorMsg;

    public void initialize() {
        usernameField.setTextFormatter(inputFormatter());
        passwordField.setTextFormatter(inputFormatter());
        rePasswordField.setTextFormatter(inputFormatter());
    }

    public void ReturnButtonMouseEntered(MouseEvent mouseEvent) {
        returnButton.setScaleX(1.2);
        returnButton.setScaleY(1.2);
    }

    public void ReturnButtonMouseExited(MouseEvent mouseEvent) {
        returnButton.setScaleX(1);
        returnButton.setScaleY(1);
    }

    public void GoBack(MouseEvent mouseEvent) throws IOException {
        Parent root = FXMLLoader.load(new ClassPathResource("fxml/login-scene.fxml").getUrl());
        Stage stage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }


    public void Register() {
        String username = usernameField.getText();
        String password = passwordField.getText();
        String rePassword = rePasswordField.getText();
        if (!password.equals(rePassword)) {
            errorMsg.setVisible(true);
            errorMsg.setText("两次输入的密码不一致！");
        }
        String token;
        try {
            token = GomokuWsClient.register(username, password);
        } catch (IOException e) {
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
        HomeRender.showHome();
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
