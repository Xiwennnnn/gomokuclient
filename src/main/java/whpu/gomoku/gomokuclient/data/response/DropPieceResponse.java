package whpu.gomoku.gomokuclient.data.response;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.Data;
import lombok.EqualsAndHashCode;
import whpu.gomoku.gomokuclient.data.common.ActionEnum;

@Data
@EqualsAndHashCode(callSuper = true)
public class DropPieceResponse extends AbstractResponse {
    private Integer id;
    private Long gameId;
    private Integer moveNumber;
    private Integer movePosition;
    private Boolean isUndo;

    @Override
    public String getActionType() {
        return ActionEnum.DROP_PIECE.getAction();
    }

    public static DropPieceResponse fromJson(JsonNode json) {
        DropPieceResponse response = new DropPieceResponse();
        JsonNode data = json.get("data");
        response.id = data.get("id").asInt();
        response.gameId = data.get("gameId").asLong();
        response.moveNumber = data.get("moveNumber").asInt();
        response.movePosition = data.get("movePosition").asInt();
        response.isUndo = data.get("isUndo").asBoolean();
        return response;
    }
}
