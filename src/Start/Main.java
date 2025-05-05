package Start;


import User.Admin;
import User.Customer;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        //调用系统界面
        systemMenu();
    }

    //主界面
    public static void systemMenu() {
        List<Customer> customerList = new ArrayList<>();

        Customer customer = new Customer(customerList);
        Admin admin = new Admin(customerList);

        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("=====酒店管理系统=====");
            System.out.println("1.注册");
            System.out.println("2.登录");
            System.out.println("0.退出系统");
            System.out.println("================");
            System.out.print("请输入选项 >");

            int choice = sc.nextInt();
            sc.nextLine();
            switch (choice) {
                case 1:
                    customer.signUp();
                    break;
                case 2:
                    customer.login();
                    break;
                case 0:
                    System.out.println("系统已退出 ");
                    System.exit(0);
                default:
                    System.out.println("无效操作");
            }
        }

    }
}