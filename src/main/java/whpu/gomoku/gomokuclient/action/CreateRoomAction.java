package whpu.gomoku.gomokuclient.action;

import whpu.gomoku.gomokuclient.data.common.ActionEnum;
import whpu.gomoku.gomokuclient.data.response.CreateRoomSuccessResponse;
import whpu.gomoku.gomokuclient.fxmlrender.RoomRender;

import static whpu.gomoku.gomokuclient.GomokuContext.currentRoom;

public class CreateRoomAction implements Action<CreateRoomSuccessResponse> {
    @Override
    public String getActionType() {
        return ActionEnum.CREATE_ROOM_SUCCESS.getAction();
    }

    @Override
    public void doAction(CreateRoomSuccessResponse response) {
        currentRoom = response.getRoom();
        RoomRender.showRoom();
    }
}
