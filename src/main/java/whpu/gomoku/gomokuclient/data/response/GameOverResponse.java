package whpu.gomoku.gomokuclient.data.response;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.Data;
import lombok.EqualsAndHashCode;
import whpu.gomoku.gomokuclient.data.common.ActionEnum;

@Data
@EqualsAndHashCode(callSuper = true)
public class GameOverResponse extends AbstractResponse {
    String winner;
    @Override
    public String getActionType() {
        return ActionEnum.GAME_OVER.getAction();
    }
    public static GameOverResponse fromJson(JsonNode json) {
        GameOverResponse response = new GameOverResponse();
        JsonNode data = json.get("data");
        response.winner = data.get("winner").asText();
        return response;
    }
}
