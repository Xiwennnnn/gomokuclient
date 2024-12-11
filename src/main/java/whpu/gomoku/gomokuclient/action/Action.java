package whpu.gomoku.gomokuclient.action;

import whpu.gomoku.gomokuclient.data.response.AbstractResponse;

public interface Action<T extends AbstractResponse> {
    public String getActionType();
    public void doAction(T response);
}
