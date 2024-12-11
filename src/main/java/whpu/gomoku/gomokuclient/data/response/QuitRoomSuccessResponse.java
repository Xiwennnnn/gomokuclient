package whpu.gomoku.gomokuclient.data.response;

import com.fasterxml.jackson.databind.JsonNode;
import whpu.gomoku.gomokuclient.data.common.ActionEnum;

public class QuitRoomSuccessResponse extends AbstractResponse {
    @Override
    public String getActionType() {
        return ActionEnum.QUIT_ROOM_SUCCESS.getAction();
    }
    public static QuitRoomSuccessResponse fromJson(JsonNode json) {
        return new QuitRoomSuccessResponse();
    }
}
