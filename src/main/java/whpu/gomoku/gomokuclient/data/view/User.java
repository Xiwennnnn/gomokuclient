package whpu.gomoku.gomokuclient.data.view;

import lombok.Data;

@Data
public class User {
    private Long id;
    private String username;
    private String handle;
    private String role;
    private Integer winCount;
    private Integer totalCount;
    private String avatarUrl;
    /**
     * 0标识离线，1标识在线，2标识房间中，3标识游戏中
     */
    private Integer status;
}
