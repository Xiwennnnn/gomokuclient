package whpu.gomoku.gomokuclient.data.event;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AcUndoEvent implements Serializable {
    private final String action = "ac_undo";
    private Long gameId;
    private Boolean isAgree;
}
