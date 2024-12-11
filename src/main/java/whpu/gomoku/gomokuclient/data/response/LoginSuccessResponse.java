package whpu.gomoku.gomokuclient.data.response;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import whpu.gomoku.gomokuclient.data.common.ActionEnum;
import whpu.gomoku.gomokuclient.data.view.User;

@EqualsAndHashCode(callSuper = true)
@Data
public class LoginSuccessResponse extends AbstractResponse {
    private User user;
    @Override
    public String getActionType() {
        return ActionEnum.LOGIN_SUCCESS.getAction();
    }
    public static LoginSuccessResponse fromJson(JsonNode json) {
        LoginSuccessResponse response = new LoginSuccessResponse();
        JsonNode data = json.get("data");
        User user = new User();
        user.setId(data.get("id").asLong());
        user.setUsername(data.get("username").asText());
        user.setHandle(data.get("handle").asText());
        user.setRole(data.get("role").asText());
        user.setWinCount(data.get("winCount").asInt());
        user.setTotalCount(data.get("totalCount").asInt());
        user.setAvatarUrl(data.get("avatarUrl").isNull() ? null : data.get("avatarUrl").asText());
        user.setStatus(data.get("status").asInt());
        response.setUser(user);
        return response;
    }
}
