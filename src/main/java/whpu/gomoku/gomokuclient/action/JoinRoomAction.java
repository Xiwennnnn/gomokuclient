package whpu.gomoku.gomokuclient.action;

import whpu.gomoku.gomokuclient.data.common.ActionEnum;
import whpu.gomoku.gomokuclient.data.response.JoinRoomResponse;
import whpu.gomoku.gomokuclient.fxmlrender.RoomRender;

import static whpu.gomoku.gomokuclient.GomokuContext.currentRoom;

public class JoinRoomAction implements Action<JoinRoomResponse> {
    @Override
    public String getActionType() {
        return ActionEnum.JOIN_ROOM_SUCCESS.getAction();
    }

    @Override
    public void doAction(JoinRoomResponse response) {
        currentRoom = response.getRoom();
        RoomRender.showRoom();
    }
}
