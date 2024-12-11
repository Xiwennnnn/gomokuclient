package whpu.gomoku.gomokuclient.data.event;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OfflineEvent implements Serializable {
    private final String action = "offline";
    private Long userId;
}
