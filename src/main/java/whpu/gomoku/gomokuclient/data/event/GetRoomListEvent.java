package whpu.gomoku.gomokuclient.data.event;

import lombok.Data;

import java.io.Serializable;


@Data
public class GetRoomListEvent implements Serializable {
    private final String action = "get_room_list";
}
