package whpu.gomoku.gomokuclient.data.event;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DropPieceEvent implements Serializable {
    private final String action ="drop_piece";
    private Long gameId;
    private int index;

}
