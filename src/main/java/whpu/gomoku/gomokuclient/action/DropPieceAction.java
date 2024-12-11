package whpu.gomoku.gomokuclient.action;

import javafx.scene.paint.Color;
import lombok.extern.slf4j.Slf4j;
import whpu.gomoku.gomokuclient.GomokuContext;
import whpu.gomoku.gomokuclient.data.common.ActionEnum;
import whpu.gomoku.gomokuclient.data.response.DropPieceResponse;

@Slf4j
public class DropPieceAction implements Action<DropPieceResponse> {
    @Override
    public String getActionType() {
        return ActionEnum.DROP_PIECE.getAction();
    }

    @Override
    public void doAction(DropPieceResponse response) {
        Color color;
        char c;
        if (response.getMoveNumber() % 2 == 0) {
            color = Color.WHITE;
            c = 'W';
        } else {
            color = Color.BLACK;
            c = 'B';
        }
        GomokuContext.gameController.turnChange();
        int x = response.getMovePosition() / 15;
        int y = response.getMovePosition() % 15;
        GomokuContext.gameController.drawPiece(x, y, color);
        GomokuContext.currentGame.getGameStatus().setCharAt(response.getMovePosition(), c);
    }
}
