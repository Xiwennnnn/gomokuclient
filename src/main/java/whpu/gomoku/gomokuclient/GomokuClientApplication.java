package whpu.gomoku.gomokuclient;

import cn.hutool.core.io.resource.ClassPathResource;
import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import lombok.extern.slf4j.Slf4j;
import whpu.gomoku.gomokuclient.data.event.OfflineEvent;
import whpu.gomoku.gomokuclient.fxmlrender.LoginRender;
import whpu.gomoku.gomokuclient.okhttp.GomokuWsClient;
import whpu.gomoku.gomokuclient.util.GomokuUtil;
import whpu.gomoku.gomokuclient.util.JsonUtil;

@Slf4j
public class GomokuClientApplication extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        GomokuContext.stage = stage;
        stage.getIcons().add(new Image(new ClassPathResource("static/icon/GomokuIron.png").getUrl().toString()));
        String token = GomokuUtil.loadToken();
        if (token == null || token.isEmpty()) {
            LoginRender.showLoginFXML(null);
        } else {
            GomokuWsClient.connect();
        }
    }

    @Override
    public void stop() throws Exception {
        log.info("GomokuClientApplication stop");
        GomokuWsClient.disconnect();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
