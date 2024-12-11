package whpu.gomoku.gomokuclient.controller;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;
import lombok.extern.slf4j.Slf4j;
import whpu.gomoku.gomokuclient.GomokuContext;
import whpu.gomoku.gomokuclient.service.GameService;

import java.util.Objects;
import java.util.Optional;

@Slf4j
public class GameController {
    public Label isTurnInfo;
    public Button UnDoButton;
    public Button GiveUpButton;
    @FXML
    private AnchorPane boardPane;
    @FXML
    private Rectangle Board;
    private GraphicsContext gc;
    private static final int BOARD_SIZE = 15;
    private static final int LEFT_MARGIN = 78;
    private static final int TOP_MARGIN = 65;
    private static final double CELL_SIZE = 40;
    private boolean isMyTurn = false;
    private boolean tempIsMyTurn = false;
    private static final Alert waitingAlert;

    static {
        // 创建信息提示框
        waitingAlert = new Alert(Alert.AlertType.INFORMATION);
        waitingAlert.setTitle("等待对方同意");
        waitingAlert.setHeaderText(null);
        waitingAlert.setContentText("正在等待对方同意，请耐心等待...");
    }

    public void initialize() {
        GomokuContext.gameController = this;
        if (Objects.equals(GomokuContext.currentGame.getBlackPlayerId(), GomokuContext.user.getId())) {
            isMyTurn = true;
        }
        if (isMyTurn) {
            boardPane.setStyle("-fx-background-color: #32D88C;");
            isTurnInfo.setText("当前轮玩家: 你");
        } else {
            boardPane.setStyle("-fx-background-color: #ffd246;");
            isTurnInfo.setText("当前轮玩家: 对方");
        }
        Canvas canvas = drawBoard();
        gc = canvas.getGraphicsContext2D();
        canvas.setOnMouseClicked(event -> {
            if (!isMyTurn) {
                return;
            }
            double mouseX = event.getX();
            double mouseY = event.getY();
            int gridY = (int) Math.round(mouseX / CELL_SIZE);
            int gridX = (int) Math.round(mouseY / CELL_SIZE);
            int index = turn(gridX, gridY);
            if (index == -1) return;
            StringBuilder gameStatus = GomokuContext.currentGame.getGameStatus();
            if (gameStatus.charAt(index) != 'N') {
                return;
            }
            GameService.dropPiece(index);
        });
    }

    private int turn(int gridX, int gridY) {
        return gridX + gridY * BOARD_SIZE;
    }

    public void drawPiece(int x, int y, Color color) {
        Platform.runLater(() -> {
            double left = x * CELL_SIZE;
            double top = y * CELL_SIZE;

            // 先绘制边框
            gc.setFill(Color.BLACK);
            gc.fillOval(left - 16, top - 16, 32, 32); // 边框稍大

            // 再绘制棋子
            gc.setFill(color);
            gc.fillOval(left - 15, top - 15, 30, 30);
        });
    }


    public void acUndo() {
        Platform.runLater(() -> {
            isMyTurn = tempIsMyTurn;
            waitingAlert.close();
        });
    }

    public void undo() {
        Platform.runLater(() -> {
            Canvas canvas = drawBoard();
            gc = canvas.getGraphicsContext2D();
            String gameStatus = GomokuContext.currentGame.getGameStatus().toString();
            for (int i = 0; i < gameStatus.length(); i++) {
                if (gameStatus.charAt(i) == 'B') {
                    drawPiece(i / BOARD_SIZE, i % BOARD_SIZE, Color.BLACK);
                } else if (gameStatus.charAt(i) == 'W') {
                    drawPiece(i / BOARD_SIZE, i % BOARD_SIZE, Color.WHITE);
                }
            }
            canvas.setOnMouseClicked(event -> {
                if (!isMyTurn) {
                    return;
                }
                double mouseX = event.getX();
                double mouseY = event.getY();
                int gridY = (int) Math.round(mouseX / CELL_SIZE);
                int gridX = (int) Math.round(mouseY / CELL_SIZE);
                int index = turn(gridX, gridY);
                if (index == -1) return;
                if (gameStatus.charAt(index) != 'N') {
                    return;
                }
                GameService.dropPiece(index);
            });
        } );
    }

    private Canvas drawBoard() {
        double width = Board.getWidth();
        double height = Board.getHeight();

        Canvas canvas = new Canvas(width, height);
        canvas.setLayoutX(LEFT_MARGIN);
        canvas.setLayoutY(TOP_MARGIN);
        boardPane.getChildren().add(canvas);

        GraphicsContext gc = canvas.getGraphicsContext2D();

        gc.setFill(Color.LIGHTYELLOW);
        gc.fillRect(0, 0, width, height);

        gc.setStroke(Color.BLACK);
        gc.setLineWidth(2);

        for (int i = 0; i < BOARD_SIZE; i++) {
            double position = i * CELL_SIZE;
            gc.strokeLine(position, 0, position, height);
            gc.strokeLine(0, position, width, position);
        }
        return canvas;
    }

    public void turnChange() {
        Platform.runLater(() -> {
            isMyTurn = !isMyTurn;
            if (isMyTurn) {
                boardPane.setStyle("-fx-background-color: #32D88C;");
                UnDoButton.setDisable(false);
                UnDoButton.setStyle("-fx-background-color: #c16fff;");
                isTurnInfo.setText("当前轮玩家: 你");
            } else {
                boardPane.setStyle("-fx-background-color: #ffd246;");
                UnDoButton.setDisable(true);
                UnDoButton.setStyle("-fx-background-color: rgba(211,152,255,0.43);");
                isTurnInfo.setText("当前轮玩家: 对方");
            }
        });
    }

    public void undoFunc(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("确认悔棋");
        alert.setHeaderText("确认要悔棋吗?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            tempIsMyTurn = isMyTurn;
            isMyTurn = false;
            watingBeforeConfirm();
            GameService.undo();
        }
    }

    private void watingBeforeConfirm() {
        waitingAlert.show();

    }

    public void giveUpFunc(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("确认认输");
        alert.setHeaderText("你确认要认输吗?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            GameService.giveUp();
        }
    }
}
