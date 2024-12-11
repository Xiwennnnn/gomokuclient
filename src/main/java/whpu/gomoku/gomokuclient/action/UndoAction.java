package whpu.gomoku.gomokuclient.action;

import whpu.gomoku.gomokuclient.GomokuContext;
import whpu.gomoku.gomokuclient.data.common.ActionEnum;
import whpu.gomoku.gomokuclient.data.response.UndoResponse;

public class UndoAction implements Action<UndoResponse> {
    @Override
    public String getActionType() {
        return ActionEnum.UNDO.getAction();
    }

    @Override
    public void doAction(UndoResponse response) {
        GomokuContext.currentGame = response.getGame();
        GomokuContext.gameController.acUndo();
        GomokuContext.gameController.undo();
    }
}
