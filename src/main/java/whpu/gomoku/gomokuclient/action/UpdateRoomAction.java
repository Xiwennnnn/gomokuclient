package whpu.gomoku.gomokuclient.action;

import lombok.extern.slf4j.Slf4j;
import whpu.gomoku.gomokuclient.data.common.ActionEnum;
import whpu.gomoku.gomokuclient.GomokuContext;
import whpu.gomoku.gomokuclient.data.response.GetRoomsResponse;

import static whpu.gomoku.gomokuclient.GomokuContext.user;

@Slf4j
public class UpdateRoomAction implements Action<GetRoomsResponse> {
    @Override
    public String getActionType() {
        return ActionEnum.UPDATE_ROOMS.getAction();
    }

    @Override
    public void doAction(GetRoomsResponse response) {
        GomokuContext.rooms = response.getRooms();
        if (user.getStatus() == 1 && GomokuContext.homeController!= null) {
            try {
                GomokuContext.homeController.updateRoomData();
            } catch (Exception e) {
                log.error("更新房间数据失败", e);
            }
        }
    }
}
