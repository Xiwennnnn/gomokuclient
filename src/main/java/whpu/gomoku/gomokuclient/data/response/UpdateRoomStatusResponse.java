package whpu.gomoku.gomokuclient.data.response;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.Data;
import lombok.EqualsAndHashCode;
import whpu.gomoku.gomokuclient.data.common.ActionEnum;
import whpu.gomoku.gomokuclient.data.view.Room;

@Data
@EqualsAndHashCode(callSuper = true)
public class UpdateRoomStatusResponse extends AbstractResponse {
    private Room room;
    @Override
    public String getActionType() {
        return ActionEnum.UPDATE_ROOM_STATUS.getAction();
    }
    public static UpdateRoomStatusResponse fromJson(JsonNode json) {
        UpdateRoomStatusResponse response = new UpdateRoomStatusResponse();
        Room room = new Room();
        JsonNode data = json.get("data");
        room.setRoomId(data.get("id").asLong());
        room.setStatus(data.get("status").asInt());
        room.setOwnerId(data.get("ownerId").asLong());
        room.setPlayerId(data.get("playerId").isNull() ? null : data.get("playerId").asLong());
        room.setCreateTime(data.get("createTime").asLong());
        room.setOwnerHandle(data.get("ownerHandle").asText());
        room.setPlayerHandle(data.get("playerHandle").isNull() ? null : data.get("playerHandle").asText());
        room.setPlayerCount(data.get("playerCount").asInt());
        room.setIsPrivate(data.get("isPrivate").asBoolean());
        response.setRoom(room);
        return response;
    }
}
