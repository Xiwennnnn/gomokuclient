package whpu.gomoku.gomokuclient.data.response;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.extern.slf4j.Slf4j;
import whpu.gomoku.gomokuclient.data.common.ActionEnum;
import whpu.gomoku.gomokuclient.data.view.Room;

import java.util.ArrayList;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
@Slf4j
public class GetRoomsResponse extends AbstractResponse {
    private List<Room> rooms;
    @Override
    public String getActionType() {
        return ActionEnum.UPDATE_ROOMS.getAction();
    }

    public static GetRoomsResponse fromJson(JsonNode json) {
        GetRoomsResponse response = new GetRoomsResponse();
        List<Room> roomList = new ArrayList<>();
        JsonNode roomsNode = json.get("data").get("rooms");
        roomsNode.elements().forEachRemaining(roomNode -> {
            Room room = new Room();
            room.setRoomId(roomNode.get("id").asLong());
            room.setOwnerId(roomNode.get("ownerId").asLong());
            room.setPlayerId(roomNode.get("playerId").isNull() ? null : roomNode.get("playerId").asLong());
            room.setOwnerHandle(roomNode.get("ownerHandle").asText());
            room.setCreateTime(roomNode.get("createTime").asLong());
            room.setPlayerHandle(roomNode.get("playerHandle").isNull() ? null : roomNode.get("playerHandle").asText());
            room.setPlayerCount(roomNode.get("playerCount").asInt());
            room.setIsPrivate(roomNode.get("isPrivate").asBoolean());
            room.setStatus(roomNode.get("status").asInt());
            roomList.add(room);
        });
        response.setRooms(roomList);
        return response;
    }
}
