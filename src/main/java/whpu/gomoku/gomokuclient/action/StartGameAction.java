package whpu.gomoku.gomokuclient.action;

import whpu.gomoku.gomokuclient.GomokuContext;
import whpu.gomoku.gomokuclient.data.common.ActionEnum;
import whpu.gomoku.gomokuclient.data.response.StartGameResponse;
import whpu.gomoku.gomokuclient.fxmlrender.GameRender;

public class StartGameAction implements Action<StartGameResponse> {
    @Override
    public String getActionType() {
        return ActionEnum.START_GAME.getAction();
    }

    @Override
    public void doAction(StartGameResponse response) {
        GomokuContext.currentGame = response.getGame();
        GameRender.showGame();
    }
}
