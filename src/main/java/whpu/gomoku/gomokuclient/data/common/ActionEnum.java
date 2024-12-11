package whpu.gomoku.gomokuclient.data.common;

public enum ActionEnum {
    LOGIN_SUCCESS("login_success"),
    UPDATE_ROOMS("update_rooms"),
    JOIN_ROOM_SUCCESS("join_room_success"),
    CREATE_ROOM_SUCCESS("create_room_success"),
    UPDATE_ROOM_STATUS("update_room_status"),
    QUIT_ROOM_SUCCESS("quit_room_success"),
    START_GAME("game_start"),
    GAME_OVER("game_over"),
    UNDO("undo"),
    REQUEST_UNDO("request_undo"),
    DROP_PIECE("drop_piece");

    private final String action;
    ActionEnum(String action) {
        this.action = action;
    }

    public String getAction() {
        return action;
    }
}
