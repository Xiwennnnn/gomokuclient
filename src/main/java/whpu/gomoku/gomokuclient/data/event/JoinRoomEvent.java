package whpu.gomoku.gomokuclient.data.event;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JoinRoomEvent implements Serializable {
    private final String action = "join_room";
    private Long roomId;
}
