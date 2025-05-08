package Room;

import java.util.ArrayList;
import java.util.List;

public class RoomManagement {
    //存储房间信息的列表
    private List<Room> rooms;

    //构造函数
    public RoomManagement() {
        rooms = new ArrayList<>();
        initializeRooms(RoomType.SINGLE, 100.0, 5);
        initializeRooms(RoomType.DOUBLE, 150.0, 8);
    }

    //初始化房间
    public void initializeRooms(RoomType type, double price, int capacity) {
        // 初始化房间类型
        for (int i = 0; i < capacity; i++) {
            rooms.add(new Room(type, price,1));
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
    
    //再对可用房间类型某个房间进行状态修改
    public Room bookRoom(RoomType type) {
        List<Room> availableRooms = findAvailableRooms(type);
        //判断可用房间是否为空
        if (!availableRooms.isEmpty()) {
            Room room = availableRooms.get(0); //取第一个可用房间并且修改状态
            room.updateAvailable(false);
            return room;
        }
        return null;
    }
    
    //退房
    public boolean checkOutRoom(Room room) {
        if (rooms.contains(room) && !room.isAvailable()) {
            room.updateAvailable(true);
            return true;
        }
        return false;
    }
}
