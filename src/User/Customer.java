package User;

import Room.Room;
import Room.RoomManagement;
import Room.RoomType;

//import java.util.ArrayList;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/*
    1.没有设置初始会员，是否后期考虑加入充值一定数额后非会员转变为会员的功能
 */

public class Customer extends User {
    private boolean isMember = false;
    private double balance = 0.0;
    private Room currentRoom;
    private List<Customer> customers;//存储用户信息的列表
    private RoomManagement rm;

    Scanner sc = new Scanner(System.in);

    // 构造方法
    public Customer(List<Customer> customers, RoomManagement rm) {
        this.customers = customers;
        this.rm = rm;
    }

    public Customer(String userId, String password) {
        super(userId,password);
        this.customers = new ArrayList<>();
    }

    //setters和getters
    public boolean getMember() {
        return isMember;
    }
    public void setMember(boolean isMember) {
        this.isMember = isMember;
    }
    public List<Customer> getCustomers() {
        return customers;
    }
    public Room getCurrentRoom() {
        return currentRoom;
    }
    public void setCurrentRoom(Room room) {
        this.currentRoom = room;
    }
    public double getBalance() {
        return balance;
    }
    public void setBalance(double balance) {
        this.balance = balance;
    }

    //注册
    public void signUp() {
        //提示用户输入账号密码
        System.out.println("请输入用户id：");
        String userId = sc.nextLine();
        System.out.println("请输入密码：");
        String password = sc.nextLine();
        
        //检查用户的账号是否存在
        for (Customer customer : customers) {
            if (customer.getUserId().equals(userId)){
                System.out.println("该用户ID已存在，请重新输入用户ID：");
                return;
            }
        }
        //把账号密码对应人保存到动态数组里边。。。
        //如果没有重复就创建成功。。。
        Customer newCustomer = new Customer(customers,rm);
        newCustomer.setUserId(userId);
        newCustomer.setPassword(password);
        customers.add(newCustomer);
        System.out.println("账户创建成功！");
    }

    //登录(涉及到判断用户类型)
    public void login() {
        System.out.println("请输入用户id：");
        String userId = sc.nextLine();
        System.out.println("请输入密码:");
        String password = sc.nextLine();

        Admin admin = new Admin(customers,rm);
        //判断是否为管理员
        if (userId.equals("admin") && password.equals("admin123")) {
            System.out.println("正在进入管理员界面...");
            admin.adminMenu("admin"); //调用管理员界面
            return;
        }
        
        Customer targetCustomer = null;
        for (Customer customer : customers) {
            if (customer.getUserId().equals(userId)) {
                targetCustomer = customer; //先找到目标账号，即是先验证账号
                break;
            }
        }
        
        if (targetCustomer == null) {
            System.out.println("用户ID不存在，请重新输入。");
            return;
        }
        //验证密码
        if (targetCustomer.getPassword().equals(password)) {
            System.out.println("正在进入顾客界面...");
            customerMenu(targetCustomer.getUserId());
        } else {
            System.out.println("密码错误请重新输入。");
        }
    }

    //顾客界面
    public void customerMenu(String userId) {
        
        while (true) {
            System.out.println("=====顾客界面=====");
            System.out.println("欢迎顾客：" + userId + "\t" + "当前余额：" + balance + "\t" + "享受折扣：" + (isMember ? "会员78折" : "非会员无折扣"));
            System.out.println("1.查看空闲房间");
            System.out.println("2.开房");
            System.out.println("3.退房");
            System.out.println("4.查看我的房卡");
            System.out.println("5.充值");
            System.out.println("0.退出登录");
            System.out.println("================");
            System.out.print("请输入选项 >");

            int choice = sc.nextInt();
            sc.nextLine();
            switch (choice) {
                case 1:
                    rm.displayAvailableRooms(); //应该是可以看到所有的房型,然后状态false的不显示

                    break;
                case 2: //开房判断是否会员(其实就是看是否享受折扣)，会员在原价基础进行折扣
                    System.out.println("请输入想要的房间类型(SINGLE,DOUBLE)：");
                    String input = sc.next();
                    try {
                        RoomType type = RoomType.valueOf(input);
                        Room bookedRoom = rm.bookRoom(type);
                        if (bookedRoom != null) {
                            double originPrice = bookedRoom.getPrice();
                            double finalPrice = originPrice * this.discount();
                            //成功扣除相应的金额,失败则充值后再来开房
                            if (getBalance() >= finalPrice) {
                                setBalance(getBalance() - finalPrice);
                                this.setCurrentRoom(bookedRoom);
                                System.out.println("开房成功！原价: "+ originPrice + "折后价：" + finalPrice);
                            } else {
                                System.out.println("余额不足，请充值。");
                            }
                            
                        }else{
                            System.out.println("该类型房间已满，请选择其他类型。");
                        }
                    } catch (IllegalArgumentException e) {
                        System.out.println("输入的房间类型无效！");
                    }
                    break;
                case 3:
                    if (this.getCurrentRoom() != null) {
                        if (rm.checkOutRoom(this.getCurrentRoom())) {
                            System.out.println("退房成功！");
                            this.setCurrentRoom(null);
                        } else {
                            System.out.println("退房失败");
                        }
                    } else {
                        System.out.println("您未入住任何房间。");
                    }
                    break;
                case 4:
                    viewRoomCard();
                    break;
                case 5:
                    recharge();
                    break;
                case 0:
                    System.out.println("返回主界面");
                    return;
                default:
                    System.out.println("无效操作");
            }
        }
    }
    
    //查看房卡
    public void viewRoomCard() {
        if (this.getCurrentRoom() != null) {
            System.out.println("您的房卡信息：" + this.getCurrentRoom());
        } else {
            System.out.println("您当前并未入住任何房间。");
        }
    }
    
    //充值功能 ---> 1.开房时要有扣除对应房间金额 2.满足 充值一定金额可以非会员转会员（这里设置10000的门槛）
    public void recharge() {
        System.out.println("请输入充值的数额：");
        double amount = sc.nextDouble();
        sc.nextLine();
        if (amount > 0) {
            if (amount >= 10000) {
                this.setMember(true);
                System.out.println("恭喜您成为本店 至高无上 的永久会员!");
            }
            this.setBalance(this.getBalance() + amount);
            System.out.println("充值成功！当前余额：" + this.getBalance());
        } else {
            System.out.println("输入的金额无效！");
        }
    }
    
    //判断是否享受折扣
    public double discount() {
        return this.isMember ? 0.78 : 1.0 ; //会员78折，非会员原价；
    }
    
    //打印用户信息
    @Override
    public String toString() {
        return "customerId:" + getUserId() + "/password:" + getPassword() + "/isMember:" + getMember();
    }

}
