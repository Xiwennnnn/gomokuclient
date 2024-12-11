package whpu.gomoku.gomokuclient.data.response;

import java.util.Date;

public abstract class AbstractResponse {
    protected Date time = new Date();
    public abstract String getActionType();
}
