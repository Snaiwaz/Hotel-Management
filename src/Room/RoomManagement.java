package Room;

import java.util.ArrayList;
import java.util.List;

public class RoomManagement {
    //存储房间信息的列表
    private List<Room> rooms;

    //构造函数
    public RoomManagement() {
        rooms = new ArrayList<>();
        initializeRooms();
    }

    //初始化房间
    private void initializeRooms() {
        // 初始化单人间
        for (int i = 0; i < 25; i++) {
            rooms.add(new Room(RoomType.SINGLE, 100.0));
        }
        // 初始化双人间
        for (int i = 0; i < 50; i++) {
            rooms.add(new Room(RoomType.DOUBLE, 200.0));
        }
    }

    //显示所有房间信息
    public void displayRooms() {
        for (Room room : rooms) {
            System.out.println(room);
        }
    }

    //根据房间类型查找可用的房间
    public List<Room> findAvailableRooms(RoomType type) {
        List<Room> availableRooms = new ArrayList<>();
        for (Room room : rooms) {
            if (room.getRoomType().equals(type) && room.isAvailable()) {
                availableRooms.add(room);
            }
        }
        return availableRooms;
    }

    //开房(查看类型房间是否还有余量)
    public boolean bookRoom(RoomType type) {
        List<Room> availableRooms = findAvailableRooms(type);
        if (availableRooms.isEmpty()) {
            System.out.println("没有可订的" + type + "房间");
            return false;
        } else {
            Room room = availableRooms.get(0);
            room.updateAvailable(false);
            System.out.println("房间预订成功！");
            return true;
        }
    }

    //退房
    //public boolean bookRoom(RoomType type) {
    //}
}
