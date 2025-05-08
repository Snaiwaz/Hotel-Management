package Room;

public class Room {
    private RoomType type;//房间类型
    private double price;//房间价格
    private int capacity;//房间余量
    private boolean isAvailable;//房间状态

    //构造函数初始化房间
    public Room (RoomType type, double price,int capacity) {
        this.type = type;
        this.price = price;
        this.capacity = capacity;
        this.isAvailable = true;
    }

    //Getters和Setters
    public RoomType getRoomType() {
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

