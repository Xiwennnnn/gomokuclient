package whpu.gomoku.gomokuclient.data.response;

import com.fasterxml.jackson.databind.JsonNode;
import whpu.gomoku.gomokuclient.data.common.ActionEnum;


public class RequestUndoResponse extends AbstractResponse {
    @Override
    public String getActionType() {
        return ActionEnum.REQUEST_UNDO.getAction();
    }
    public static RequestUndoResponse fromJson(JsonNode json) {
        return new RequestUndoResponse();
    }
}
