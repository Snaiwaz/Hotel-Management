package User;

import Room.RoomManagement;
import Room.RoomType;

import java.util.List;
import java.util.Scanner;

public class Admin extends User {
    private List<Customer> customers;
    Scanner sc = new Scanner(System.in);

    //预设管理员账号密码构造函数
    public Admin(List<Customer> customers) {
        super("admin","admin123");
        this.customers = customers;
    }

    //实例化room
    RoomManagement rm = new RoomManagement();

    //管理员界面
    public void adminMenu(String userId) {
        
        while (true) {
            System.out.println("=====管理员界面=====");
            System.out.println("欢迎管理员：" + userId);
            System.out.println("1.查看顾客入住信息");
            System.out.println("2.显示所有房间信息");
            System.out.println("3.添加房间");
            System.out.println("4.删除房间");
            System.out.println("0.退出登录");
            System.out.println("================");
            System.out.print("请输入选项 >");

            int choice = sc.nextInt();
            sc.nextLine();
            switch (choice) {
                case 1:
                    showCustomer();
                    break;
                case 2:
                    rm.displayRooms();
                    break;
                case 3:
                    String input;
                    double price;
                    int capacity;
                    System.out.println("请输入要添加的房间类型(SINGLE,DOUBLE)：");
                    input = sc.next();
                    RoomType type1 = RoomType.valueOf(input);
                    
                    System.out.println("请输入对应房间价格：");
                    price = sc.nextDouble();
                    
                    System.out.println("请输入对应房间数量：");
                    capacity = sc.nextInt();
                    rm.addRoom(type1,price,capacity);
                    break;
                case 4:
                    System.out.println("请输入删除的房间类型(SINGLE,DOUBLE)：");
                    input = sc.next();
                    RoomType type2 = RoomType.valueOf(input);
                    
                    System.out.println("请输入删除的对应房间数量：");
                    capacity = sc.nextInt();
                    rm.removeRoom(type2,capacity);
                    break;
                case 0:
                    System.out.println("返回主界面...");
                    return;
                default:
                    System.out.println("无效操作");
            }
        }
    }


    //查看顾客入住信息（id，顾客类型，住房类型）
    public void showCustomer () {
        if (customers == null || customers.isEmpty()) {
           System.out.println("当前没有顾客信息");
           return;
        }

        for (Customer customer : customers) {
            System.out.println("顾客：" + customer.getUserId() + "\t是否会员：" + customer.getMember() + "\t房间类型："  );
        }

    }
    
}
