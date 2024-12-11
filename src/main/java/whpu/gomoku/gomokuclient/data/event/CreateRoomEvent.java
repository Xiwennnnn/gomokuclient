package whpu.gomoku.gomokuclient.data.event;

import lombok.Data;

import java.io.Serializable;

@Data
public class CreateRoomEvent implements Serializable {
    private final String action = "create_room";
}
