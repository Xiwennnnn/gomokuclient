package whpu.gomoku.gomokuclient.data.view;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;

@Data
public class RoomVo {
    private final String createTime;
    private final Long roomId;
    private final String roomOwner;
    private final Integer roomPlayerNum;
    private final String roomStatus;

    public RoomVo(String createTime, long roomId, String roomOwner, int roomPlayerNum, String roomStatus) {
        this.createTime = createTime;
        this.roomId = roomId;
        this.roomOwner = roomOwner;
        this.roomPlayerNum = roomPlayerNum;
        this.roomStatus = roomStatus;
    }

    public static RoomVo fromRoom(Room room) {
        SimpleDateFormat formatter = new SimpleDateFormat("MM月dd日 HH:mm");
        String createTime = formatter.format(room.getCreateTime());
        Long roomId = room.getRoomId();
        String roomOwner = room.getOwnerHandle();
        int roomPlayerNum = room.getPlayerCount();
        String roomStatus;
        switch (room.getStatus()) {
            case 0 -> roomStatus = "等待中";
            case 1 -> roomStatus = "进行中";
            default -> roomStatus = "已结束";
        }
        return new RoomVo(createTime, roomId, roomOwner, roomPlayerNum, roomStatus);
    }
}
