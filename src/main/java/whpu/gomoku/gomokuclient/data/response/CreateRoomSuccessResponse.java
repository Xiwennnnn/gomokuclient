package whpu.gomoku.gomokuclient.data.response;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.Data;
import lombok.EqualsAndHashCode;
import whpu.gomoku.gomokuclient.data.common.ActionEnum;
import whpu.gomoku.gomokuclient.data.view.Room;

@Data
@EqualsAndHashCode(callSuper = true)
public class CreateRoomSuccessResponse extends AbstractResponse {
    private Room room;
    @Override
    public String getActionType() {
        return ActionEnum.CREATE_ROOM_SUCCESS.getAction();
    }
    public static CreateRoomSuccessResponse fromJson(JsonNode json) {
        CreateRoomSuccessResponse response = new CreateRoomSuccessResponse();
        JsonNode dataNode = json.get("data").get("room");
        Room data = new Room();
        data.setRoomId(dataNode.get("id").asLong());
        data.setStatus(dataNode.get("status").asInt());
        data.setOwnerId(dataNode.get("ownerId").asLong());
        data.setPlayerId(dataNode.get("playerId").isNull() ? null : dataNode.get("playerId").asLong());
        data.setCreateTime(dataNode.get("createTime").asLong());
        data.setOwnerHandle(dataNode.get("ownerHandle").asText());
        data.setPlayerHandle(dataNode.get("playerHandle").isNull() ? null : dataNode.get("playerHandle").asText());
        data.setPlayerCount(dataNode.get("playerCount").asInt());
        data.setIsPrivate(dataNode.get("isPrivate").asBoolean());
        response.setRoom(data);
        return response;
    }
}
