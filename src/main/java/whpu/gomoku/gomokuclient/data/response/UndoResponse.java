package whpu.gomoku.gomokuclient.data.response;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.Data;
import lombok.EqualsAndHashCode;
import whpu.gomoku.gomokuclient.data.common.ActionEnum;
import whpu.gomoku.gomokuclient.data.view.Game;

@Data
@EqualsAndHashCode(callSuper = true)
public class UndoResponse extends AbstractResponse {
    private Game game;
    @Override
    public String getActionType() {
        return ActionEnum.UNDO.getAction();
    }
    public static UndoResponse fromJson(JsonNode json) {
        UndoResponse response = new UndoResponse();
        Game g = new Game();
        JsonNode data = json.get("data").get("gameDto");
        g.setId(data.get("id").asLong());
        g.setBlackPlayerId(data.get("blackPlayerId").asLong());
        g.setWhitePlayerId(data.get("whitePlayerId").asLong());
        g.setMoveCount(data.get("moveCount").asInt());
        g.setGameStatus(new StringBuilder(data.get("gameStatus").asText()));
        g.setGameFormat(data.get("gameFormat").asInt());
        response.setGame(g);
        return response;
    }
}
