package whpu.gomoku.gomokuclient.data.view;

import lombok.Data;

import java.util.Date;

@Data
public class Room {
    private Long roomId;
    private Long ownerId;
    private Long playerId;
    private Long createTime;
    private String ownerHandle;
    private String playerHandle;
    private Integer playerCount;
    private Boolean isPrivate;
    /**
     * 0：准备 1：游戏中 2：已关闭
     */
    private Integer status;
}
