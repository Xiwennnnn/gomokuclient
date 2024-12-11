package whpu.gomoku.gomokuclient.data.event;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GiveUpEvent implements Serializable {
    private final String action = "give_up";
    private Long gameId;
    private Long playerId;
}
