package whpu.gomoku.gomokuclient.service;

import lombok.extern.slf4j.Slf4j;
import whpu.gomoku.gomokuclient.GomokuContext;
import whpu.gomoku.gomokuclient.data.event.AcUndoEvent;
import whpu.gomoku.gomokuclient.data.event.DropPieceEvent;
import whpu.gomoku.gomokuclient.data.event.GiveUpEvent;
import whpu.gomoku.gomokuclient.data.event.UnDoEvent;
import whpu.gomoku.gomokuclient.util.JsonUtil;

@Slf4j
public class GameService {
    public static void dropPiece(int index) {
        DropPieceEvent event = new DropPieceEvent(GomokuContext.currentGame.getId(), index);
        try {
            GomokuContext.webSocket.send(JsonUtil.turnObjectToJson(event));
        } catch (Exception e) {
            log.error("发送落子失败", e);
        }
    }

    public static void undo() {
        Long gameId = GomokuContext.currentGame.getId();
        UnDoEvent event = new UnDoEvent(gameId);
        try {
            GomokuContext.webSocket.send(JsonUtil.turnObjectToJson(event));
        } catch (Exception e) {
            log.error("发送悔棋失败", e);
        }
    }

    public static void AgreeUndo(boolean isAgree) {
        Long gameId = GomokuContext.currentGame.getId();
        Boolean agree = isAgree;
        AcUndoEvent event = new AcUndoEvent(gameId, agree);
        try {
            GomokuContext.webSocket.send(JsonUtil.turnObjectToJson(event));
        } catch (Exception e) {
            log.error("发送回应悔棋消息失败", e);
        }
    }

    public static void giveUp() {
        Long gameId = GomokuContext.currentGame.getId();
        Long playerId = GomokuContext.user.getId();
        GiveUpEvent event = new GiveUpEvent(gameId, playerId);
        try {
            GomokuContext.webSocket.send(JsonUtil.turnObjectToJson(event));
        } catch (Exception e) {
            log.error("发送认输失败", e);
        }
    }
}
