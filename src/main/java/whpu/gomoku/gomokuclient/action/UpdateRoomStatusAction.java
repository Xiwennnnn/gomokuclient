package whpu.gomoku.gomokuclient.action;

import whpu.gomoku.gomokuclient.data.common.ActionEnum;
import whpu.gomoku.gomokuclient.GomokuContext;
import whpu.gomoku.gomokuclient.data.response.UpdateRoomStatusResponse;

public class UpdateRoomStatusAction implements Action<UpdateRoomStatusResponse> {
    @Override
    public String getActionType() {
        return ActionEnum.UPDATE_ROOM_STATUS.getAction();
    }
    @Override
    public void doAction(UpdateRoomStatusResponse response) {
        GomokuContext.currentRoom = response.getRoom();
        if (GomokuContext.user.getStatus() == 2) {
            GomokuContext.roomController.updateRoomInfo();
        }
    }
}
