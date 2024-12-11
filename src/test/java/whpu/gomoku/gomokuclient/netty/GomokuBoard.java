package whpu.gomoku.gomokuclient.netty;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class GomokuBoard extends Application {

    // 棋盘的大小和格子大小
    private final int boardSize = 15;
    private final double cellSize = 40;

    // 用于记录鼠标当前位置的交叉点坐标
    private int currentX = -1;
    private int currentY = -1;

    @Override
    public void start(Stage primaryStage) {
        // 创建一个组，容纳所有控件
        Group root = new Group();

        // 创建一个 Canvas 用于绘制棋盘
        Canvas canvas = new Canvas(600, 600);
        root.getChildren().add(canvas);

        // 获取 GraphicsContext 对象进行绘制
        GraphicsContext gc = canvas.getGraphicsContext2D();

        // 设置背景颜色为浅棕色，模拟棋盘
        gc.setFill(Color.LIGHTYELLOW);
        gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());

        // 绘制棋盘格线
        gc.setStroke(Color.BLACK);
        gc.setLineWidth(2);

        // 绘制纵向和横向的棋盘线
        for (int i = 0; i <= boardSize; i++) {
            double linePosition = i * cellSize;
            gc.strokeLine(linePosition, 0, linePosition, boardSize * cellSize); // 垂直线
            gc.strokeLine(0, linePosition, boardSize * cellSize, linePosition); // 水平线
        }

        // 监听鼠标移动事件
        canvas.addEventHandler(MouseEvent.MOUSE_MOVED, e -> {
            // 获取鼠标当前的坐标
            double mouseX = e.getX();
            double mouseY = e.getY();

            // 计算鼠标所在的交叉点坐标（边界中点）
            int gridX = (int) Math.round(mouseX / cellSize);
            int gridY = (int) Math.round(mouseY / cellSize);

            // 如果鼠标在棋盘范围内并且不是上次的坐标，则更新当前交叉点坐标
            if (gridX >= 0 && gridX < boardSize && gridY >= 0 && gridY < boardSize) {
                currentX = gridX;
                currentY = gridY;
                drawBoard(gc);  // 重新绘制棋盘
                drawPiece(gc, currentX, currentY);  // 绘制棋子
            }
        });

        // 创建场景并显示
        Scene scene = new Scene(root, 600, 600);
        primaryStage.setTitle("五子棋棋盘");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    // 绘制棋盘
    private void drawBoard(GraphicsContext gc) {
        gc.setFill(Color.LIGHTYELLOW);
        gc.fillRect(0, 0, 600, 600);  // 重绘背景

        // 绘制棋盘格线
        gc.setStroke(Color.BLACK);
        gc.setLineWidth(2);

        // 绘制纵向和横向的棋盘线
        for (int i = 0; i <= boardSize; i++) {
            double linePosition = i * cellSize;
            gc.strokeLine(linePosition, 0, linePosition, boardSize * cellSize); // 垂直线
            gc.strokeLine(0, linePosition, boardSize * cellSize, linePosition); // 水平线
        }
    }

    // 绘制棋子（在交叉点处绘制一个圆形代表棋子）
    private void drawPiece(GraphicsContext gc, int x, int y) {
        double pieceX = x * cellSize;
        double pieceY = y * cellSize;

        // 绘制一个黑色棋子模型（可以根据需求调整颜色或大小）
        gc.setFill(Color.BLACK);  // 这里绘制黑色棋子
        gc.fillOval(pieceX - 15, pieceY - 15, 30, 30);  // 棋子半径为 15
    }

    public static void main(String[] args) {
        launch(args);
    }
}
