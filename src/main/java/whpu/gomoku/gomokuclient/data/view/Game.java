package whpu.gomoku.gomokuclient.data.view;

import lombok.Data;

@Data
public class Game {
    private Long id;
    private Long blackPlayerId;
    private Long whitePlayerId;
    private StringBuilder gameStatus;
    private Integer moveCount;
    private Integer gameFormat;
}
