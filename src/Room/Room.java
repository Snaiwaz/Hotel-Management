package Room;

public class Room {
    private String type;//房间类型
    private double price;//房间价格
    private int capacity;//房间余量
    private boolean isAvailable;//房间状态

    //构造函数初始化房间
    public Room (String type, double price,int capacity) {
        this.type = type;
        this.price = price;
        this.capacity = capacity;
        this.isAvailable = true;
    }

    public Room(RoomType roomType, double price) {
        this.type = roomType.toString();
    }

    public Room() {
        Room room = new Room();
    }

    //Getters和Setters
    public String getRoomType() {
        return type;
    }
    public double getPrice() {
        return price;
    }
    public int getCapacity() {
        return capacity;
    }
    public boolean isAvailable() {
        return isAvailable;
    }

    public void updateAvailable(boolean available) {
        isAvailable = available;
    }

    //重写方法
    @Override
    public String toString() {
        return "房间{" +
                "类型=" + type +
                ", 价格=" + price +
                ", 状态=" + isAvailable +
                '}';
    }
}

