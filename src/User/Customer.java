package User;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Customer extends User {
    private boolean isMember = false;
    private List<Customer> customers;//存储用户信息的列表

    Scanner sc = new Scanner(System.in);

    // 无参构造方法
    public Customer(List<Customer> customers) {
        this.customers = customers;
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
        Customer newCustomer = new Customer(userId,password);
        customers.add(newCustomer);
        System.out.println("账户创建成功！");
    }

    //登录(涉及到判断用户类型)
    public void login() {
        System.out.println("请输入用户id：");
        String userId = sc.nextLine();
        System.out.println("请输入密码:");
        String password = sc.nextLine();

        Admin admin = new Admin(customers);
        //判断是否为管理员
        if (userId.equals("admin") && password.equals("admin123")) {
            System.out.println("正在进入管理员界面...");
            admin.adminMenu("admin");//调用管理员界面
        }else{
            for (Customer customer : customers) {
                if (customer.getUserId().equals(userId)) {
                    if (customer.getPassword().equals(password)) {
                        System.out.println("正在进入顾客界面...");
                        //进入到顾客界面。。。
                        customerMenu(customer.getUserId());
                    } else {
                        System.out.println("密码错误请重新输入。");
                        return;
                    }
                }else{
                    System.out.println("用户ID不存在，请重新输入。");
                }
            }
        }
    }

    //顾客界面
    public void customerMenu(String userId) {
        while (true) {
            System.out.println("=====顾客界面=====");
            System.out.println("欢迎顾客：" + userId + "\t" + "当前余额：" + "\t" + "享受折扣：");
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
                    System.out.println("查看空余房间功能待实现");

                    break;
                case 2:
                    System.out.println("开房功能待实现");
                    break;
                case 3:
                    System.out.println("退房功能待实现");
                    break;
                case 4:
                    System.out.println("查看房卡功能待实现");
                    break;
                case 5:
                    System.out.println("充值功能待实现");
                    break;
                case 0:
                    System.out.println("返回主界面");
                    return;
                default:
                    System.out.println("无效操作");
            }
        }
    }


    //打印用户信息
    @Override
    public String toString() {
        return "customerId:" + getUserId() + "/password:" + getPassword() + "/isMember:" + getMember();
    }

}
