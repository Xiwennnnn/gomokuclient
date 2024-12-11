package whpu.gomoku.gomokuclient.controller;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import lombok.extern.slf4j.Slf4j;
import whpu.gomoku.gomokuclient.data.view.Room;
import whpu.gomoku.gomokuclient.data.view.RoomVo;
import whpu.gomoku.gomokuclient.fxmlrender.GameRender;
import whpu.gomoku.gomokuclient.fxmlrender.LoginRender;
import whpu.gomoku.gomokuclient.fxmlrender.RoomRender;
import whpu.gomoku.gomokuclient.okhttp.GomokuWsClient;
import whpu.gomoku.gomokuclient.service.RoomService;
import whpu.gomoku.gomokuclient.util.GomokuUtil;

import static whpu.gomoku.gomokuclient.GomokuContext.*;

@Slf4j
public class HomeController {
    public TableView<RoomVo> roomTable;
    public ImageView avatar;
    public Label username;
    public MenuButton menuButton;
    public TableColumn<RoomVo, Long> RoomId;
    public TableColumn<RoomVo, String> RoomOwner;
    public TableColumn<RoomVo, Integer> RoomPlayerNum;
    public TableColumn<RoomVo, String> RoomStatus;
    public TableColumn<RoomVo, String> CreateTime;
    public ImageView RefreshBtn;

    @FXML
    public void initialize() {
        homeController = this;
        roomTable.setStyle("-fx-hbar-policy: NEVER;");
        RoomId.setCellValueFactory(new PropertyValueFactory<>("roomId"));
        RoomOwner.setCellValueFactory(new PropertyValueFactory<>("roomOwner"));
        RoomPlayerNum.setCellValueFactory(new PropertyValueFactory<>("roomPlayerNum"));
        RoomStatus.setCellValueFactory(new PropertyValueFactory<>("roomStatus"));
        CreateTime.setCellValueFactory(new PropertyValueFactory<>("createTime"));
        setColumnTextAlignment(RoomId);
        setColumnTextAlignment(RoomOwner);
        setColumnTextAlignment(RoomPlayerNum);
        setColumnTextAlignment(RoomStatus);
        setColumnTextAlignment(CreateTime);
        user.setStatus(1);
        updateRoomData();
    }

    public void updateRoomData() {
        Platform.runLater(() -> {
            username.setText(user.getUsername());
            ObservableList<RoomVo> data = FXCollections.observableArrayList();
            if (rooms.isEmpty()) {
                roomTable.setItems(FXCollections.observableArrayList());
                roomTable.refresh();
                return;
            }
            for (Room room : rooms) {
                RoomVo roomVo = RoomVo.fromRoom(room);
                data.add(roomVo);
            }
            roomTable.setItems(data);
            roomTable.refresh();
        });
    }

    private <T> void setColumnTextAlignment(TableColumn<RoomVo, T> column) {
        column.setCellFactory(tc -> {
            TableCell<RoomVo, T> cell = new TableCell<>() {
                @Override
                protected void updateItem(T item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty) {
                        setText(null);
                    } else {
                        setText(item == null ? "" : item.toString());
                    }
                }
            };
            // 设置文本居中
            cell.setStyle("-fx-alignment: CENTER;");
            return cell;
        });
    }

    @FXML
    public void joinRoomEvent() {
        RoomVo roomVo = roomTable.getSelectionModel().getSelectedItem();
        if (roomVo != null) {
            RoomService.joinRoom(roomVo.getRoomId());
            RoomRender.showRoom();
        }

    }

    @FXML
    public void logout(ActionEvent actionEvent) {
        GomokuUtil.clearToken();
        GomokuWsClient.disconnect();
        LoginRender.showLoginFXML(null);
        user = null;
    }

    public void QuiRefreshBtn(MouseEvent mouseEvent) {
        RefreshBtn.setScaleX(1);
        RefreshBtn.setScaleY(1);
    }

    public void Refresh(MouseEvent mouseEvent) {
        RoomService.getRoomList();
    }

    public void  EnterRefreshBtn(MouseEvent mouseEvent) {
        RefreshBtn.setScaleX(1.2);
        RefreshBtn.setScaleY(1.2);
    }

    public void createRoomEvent(ActionEvent actionEvent) {
        RoomService.createRoom();
    }

    public void searchRoomEvent(ActionEvent actionEvent) {
        GameRender.showGame();
    }
}
