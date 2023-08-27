import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.Scanner;

public class user{
    public void Passwordmanagement(){
        int select;
        String name;
        int newpass;
        Scanner c = new Scanner(System.in);
        while (true) {
            System.out.println("**********************************");
            System.out.println("       欢迎使用超市购物管理系统");
            System.out.println("       1.修改密码");
            System.out.println("       2.退出");
            System.out.println("**********************************");
            System.out.print("请选择，输入数字：");
            while (true) {
                try {
                    select = Integer.parseInt(c.nextLine());
                    break;
                } catch (Exception ignored) {
                }
            }
            if (select == 1) {
                System.out.print("请输入用户名：");
                name=c.nextLine();
                System.out.print("请输入新密码：");
                newpass = c.nextInt();
                Connection coo = MYSQL.getconnection();
                Statement state = null;
                try {
                    assert coo != null;
                    state = coo.createStatement();
                    String sql;
                    sql = "update useraccount set password=? where account=?";
                    PreparedStatement pre=coo.prepareStatement(sql);
                    pre.setString(2,name);
                    pre.setInt(1,newpass);
                    pre.executeUpdate();
                    System.out.println("修改成功！");
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
            else if (select == 2) {
                break;
            }
        }
    }
    public void shop(){
        int select=0;
        Scanner c=new Scanner(System.in);
        shopping uc=new shopping();
        while (true) {
            System.out.println("**********************************");
            System.out.println("       欢迎使用超市购物管理系统");
            System.out.println("       1.将商品加入购物车");
            System.out.println("       2.从购物车中移除商品");
            System.out.println("       3.修改购物车中的商品");
            System.out.println("       4.模拟结账");
            System.out.println("       5.查看购物历史");
            System.out.println("       6.退出");
            System.out.println("**********************************");
            System.out.print("请选择，输入数字：");
            while (true) {
                try {
                    select = Integer.parseInt(c.nextLine());
                    break;
                } catch (Exception ignored) {
                }
            }
            if (select == 1) {
                uc.addcar();
            }
            if (select == 2) {
                uc.removecar();
            }
            if (select == 3) {
                uc.changecar();
            }
            if(select==4){
                uc.pay();
            }
            if(select==5){
                uc.shophistory();
            }
            if(select==6){
                break;
            }
        }
    }
}