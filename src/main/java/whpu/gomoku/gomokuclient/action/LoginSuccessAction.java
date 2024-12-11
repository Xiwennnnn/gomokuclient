package whpu.gomoku.gomokuclient.action;

import whpu.gomoku.gomokuclient.data.common.ActionEnum;
import whpu.gomoku.gomokuclient.data.response.LoginSuccessResponse;
import whpu.gomoku.gomokuclient.fxmlrender.HomeRender;

import static whpu.gomoku.gomokuclient.GomokuContext.user;

public final class LoginSuccessAction implements Action<LoginSuccessResponse> {
    @Override
    public String getActionType() {
        return ActionEnum.LOGIN_SUCCESS.getAction();
    }

    @Override
    public void doAction(LoginSuccessResponse response) {
        user = response.getUser();
        user.setStatus(1);
        HomeRender.showHome();
    }
}
