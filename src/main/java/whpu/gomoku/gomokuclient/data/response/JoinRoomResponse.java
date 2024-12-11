package whpu.gomoku.gomokuclient.data.response;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.Data;
import lombok.EqualsAndHashCode;
import whpu.gomoku.gomokuclient.data.common.ActionEnum;
import whpu.gomoku.gomokuclient.data.view.Room;


@Data
@EqualsAndHashCode(callSuper = true)
public class JoinRoomResponse extends AbstractResponse {
    private Room room;
    @Override
    public String getActionType() {
        return ActionEnum.JOIN_ROOM_SUCCESS.getAction();
    }
    public static JoinRoomResponse fromJson(JsonNode json) {
        JoinRoomResponse response = new JoinRoomResponse();
        JsonNode dataNode = json.get("data");
        Room data = new Room();
        data.setRoomId(dataNode.get("id").asLong());
        data.setOwnerId(dataNode.get("ownerId").asLong());
        data.setPlayerId(dataNode.get("playerId").asLong());
        data.setCreateTime(dataNode.get("createTime").asLong());
        data.setOwnerHandle(dataNode.get("ownerHandle").asText());
        data.setPlayerHandle(dataNode.get("playerHandle").asText());
        data.setPlayerCount(dataNode.get("playerCount").asInt());
        data.setIsPrivate(dataNode.get("isPrivate").asBoolean());
        data.setStatus(dataNode.get("status").asInt());
        response.setRoom(data);
        return response;

    }
}
