package whpu.gomoku.gomokuclient.data.event;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UnDoEvent implements Serializable {
    private final String action = "undo";
    private Long gameId;
}
