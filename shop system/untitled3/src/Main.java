import java.sql.*;
import java.util.Objects;
import java.util.Random;
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        Boolean flag = true;
        while (flag) {
            System.out.println("**********************************");
            System.out.println("           超市购物管理系统");
            System.out.println("           1、管理员入口");
            System.out.println("           2、用户入口");
            System.out.println("           3、退出系统");
            System.out.println("**********************************");
            int select = 0;
            while (true) {
                try {
                    select = Integer.parseInt(s.nextLine());
                    break;
                } catch (Exception ignored) {
                }
            }
            switch (select) {
                case 1:
                    Administratorinterface();
                    break;
                case 2:
                    Userinterface();
                    break;
                case 3:
                    flag = false;
                    System.out.println("已退出系统！");
                    break;
                default:
                    System.out.println("请输入正确的数字！");
            }
        }
    }

    public static void Administratorinterface() {
        int select;
        boolean check1=false;
        boolean check2=false;
        String name;
        int pass;
        Scanner s = new Scanner(System.in);
        while (true) {
            System.out.println("**********************************");
            System.out.println("       欢迎使用超市购物管理系统");
            System.out.println("       1.登录系统");
            System.out.println("       2.重置管理员密码");
            System.out.println("       3.退出");
            System.out.println("**********************************");
            System.out.print("请选择，输入数字：");
            while (true) {
                try {
                    select = Integer.parseInt(s.nextLine());
                    break;
                } catch (Exception ignored) {
                }
            }
            if (select == 1) {
                for(int i = 2; i >= 0; i--){
                    System.out.print("请输入用户名：");
                    name = s.nextLine();
                    System.out.print("请输入密码：");
                    pass = s.nextInt();
                    s.nextLine();
                Connection coo=MYSQL.getconnection();
                Statement state= null;
                try {
                    assert coo != null;
                    state = coo.createStatement();
                    ResultSet res=state.executeQuery("select * from adaccount");
                    while(res.next()) {
                        if (Objects.equals(name, res.getString("account")) && pass == res.getInt("password")) {
                            System.out.println("登录成功");
                            check1=true;
                            break;
                        }
                    }
                    if(check1) {
                        check1=false;
                        break;
                    }
                    else if(!check1){
                        System.out.println("用户名和密码不匹配！");
                        if (i != 0)
                            System.out.println("您还有" + i + "次登录机会，请重新输入：");
                        if (i == 0) {
                            System.out.println("密码错误次数超过3次，已退出！");
                            check2=true;
                        }
                    }
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
                }
            }
            if (select == 2) {
                int check;
                int backvalue;
                String qqaccount;
                System.out.print("请输入qq邮箱账号：");
                qqaccount = s.nextLine();
                Random r = new Random();
                emailcheck sendmss=new emailcheck();
                check = (int)(r.nextFloat()*100000);
                sendmss.send(check,qqaccount);
                System.out.print("请输入验证码：");
                backvalue=s.nextInt();
                s.nextLine();
                if(check==backvalue) {
                    System.out.println("验证成功！");
                    System.out.println("请输入用户名：");
                    name = s.nextLine();
                    Connection coo = MYSQL.getconnection();
                    Statement state = null;
                    try {
                        assert coo != null;
                        state = coo.createStatement();
                        String sql1;
                        String sql2;
                        sql1 = "DELETE FROM adaccount WHERE account=?";
                        PreparedStatement ps = coo.prepareStatement(sql1);
                        ps.setString(1, name);
                        ps.executeUpdate();
                        sql2 = "insert into adaccount(account,password) values(?,?)";
                        PreparedStatement pre = coo.prepareStatement(sql2);
                        pre.setString(1, name);
                        pre.setInt(2, 111111);
                        pre.executeUpdate();
                        System.out.println("密码已重置为：111111");
                        check2=true;
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }
                else{
                    System.out.println("验证失败！");
                    break;
                }
            }
            if (check2) {
                check2=false;
                break;
            }
            if (select == 3) {
                break;
            }
            if (select!=1&&select!=2) {
                System.out.println("请输入正确的数字！");
                break;
            }
            administrator operate = new administrator();
            while (true) {
                System.out.println("**********************************");
                System.out.println("          1.密码管理");
                System.out.println("          2.客户管理");
                System.out.println("          3.商品管理");
                System.out.println("          4.退出登录");
                System.out.println("**********************************");
                System.out.println("请选择，输入数字：");
                while (true) {
                    try {
                        select = Integer.parseInt(s.nextLine());
                        break;
                    } catch (Exception ignored) {
                    }
                }
                if (select == 1) {
                    operate.Passwordmanagement();
                } else if (select == 2) {
                    operate.Usermanagement();
                } else if (select == 3) {
                    operate.Goodsmanagement();
                } else if (select == 4) {
                    break;
                } else {
                    System.out.println("请输入正确的数字！");
                }
                System.out.println("已退出<管理员界面>，返回上一级菜单。");
            }
        }
    }

    public static void Userinterface() {
        int select;
        boolean check1=false;
        boolean check2=false;
        boolean check3=false;
        Scanner s = new Scanner(System.in);
        while (true) {
            System.out.println("**********************************");
            System.out.println("       欢迎使用超市购物管理系统");
            System.out.println("       1.注册");
            System.out.println("       2.登录");
            System.out.println("       3.重置密码");
            System.out.println("       4.退出");
            System.out.println("**********************************");
            System.out.print("请选择，输入数字：");
            while (true) {
                try {
                    select = Integer.parseInt(s.nextLine());
                    break;
                } catch (Exception ignored) {
                }
            }
            if(select==1) {
                String newname;
                int newpass;
                String sex;
                int age;
                int id=0;
                System.out.print("请输入用户名：");
                newname = s.nextLine();
                System.out.print("请输入性别：");
                sex = s.nextLine();
                System.out.print("请输入密码：");
                newpass = s.nextInt();
                System.out.print("请输入年龄：");
                age = s.nextInt();
                Connection coo = MYSQL.getconnection();
                Statement state = null;
                    try {
                        assert coo != null;
                        state = coo.createStatement();
                        String sql = "insert into useraccount(account,password,age,sex,id,balance) values(?,?,?,?,?,?)";
                        PreparedStatement pre=coo.prepareStatement(sql);
                        pre.setString(1,newname);
                        pre.setInt(2,newpass);
                        pre.setInt(3,age);
                        pre.setString(4,sex);
                        pre.setInt(6,0);
                        ResultSet res=state.executeQuery("select * from useraccount");
                        while(res.next()) {
                            if (id<=res.getInt("id")) {
                                id= res.getInt("id")+1;
                            }
                        }
                        pre.setInt(5,(id));
                        pre.executeUpdate();
                        String sql1="create table shopcar?(id int, paoductname varchar(10),price int,amount int)";
                        PreparedStatement prr=coo.prepareStatement(sql1);
                        prr.setInt(1,(id));
                        prr.executeUpdate();
                        String sql2="create table history?(id int, paoductname varchar(10),price int,amount int,buydate date)";
                        PreparedStatement prc=coo.prepareStatement(sql2);
                        prc.setInt(1,(id));
                        prc.executeUpdate();
                        System.out.printf("注册成功！您的序列号为：%d,请重新登录\n",id);
                        check3=true;
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }
            if(check3){
                check3=false;
                break;
            }
            if (select == 2) {
                String name;
                int pass;
                for(int i = 2; i >= 0; i--){
                    System.out.print("请输入用户名：");
                    name = s.nextLine();
                    System.out.print("请输入密码：");
                    pass = s.nextInt();
                    s.nextLine();
                    Connection coo=MYSQL.getconnection();
                    Statement state= null;
                    try {
                        assert coo != null;
                        state = coo.createStatement();
                        ResultSet res=state.executeQuery("select * from useraccount");
                        while(res.next()) {
                            if (Objects.equals(name, res.getString("account")) && pass == res.getInt("password")) {
                                System.out.println("登录成功");
                                check1=true;
                                break;
                            }
                        }
                        if(check1) {
                            check1=false;
                            break;
                        }
                        else if(!check1){
                            System.out.println("用户名和密码不匹配！");
                            if (i != 0)
                                System.out.println("您还有" + i + "次登录机会，请重新输入：");
                            if (i == 0) {
                                System.out.println("密码错误次数超过3次，已退出！");
                                check2=true;
                            }
                        }
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }
            }
            if(select==3) {
                int check;
                int backvalue;
                String name;
                String qqaccount;
                System.out.print("请输入qq邮箱账号：");
                qqaccount = s.nextLine();
                Random r = new Random();
                emailcheck sendmss = new emailcheck();
                check = (int) (r.nextFloat() * 100000);
                sendmss.send(check, qqaccount);
                System.out.print("请输入验证码：");
                backvalue = s.nextInt();
                s.nextLine();
                if (check == backvalue) {
                    System.out.println("验证成功！");
                    System.out.println("请输入用户名：");
                    name = s.nextLine();
                    Connection coo = MYSQL.getconnection();
                    Statement state = null;
                    try {
                        assert coo != null;
                        state = coo.createStatement();
                        String sql;
                        sql = "update useraccount set password=111111 where account=?";
                        PreparedStatement pre = coo.prepareStatement(sql);
                        pre.setString(1, name);
                        pre.executeUpdate();
                        System.out.println("密码已重置为：111111");
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }
            }
            if(check2) {
                check2=false;
                break;
            }
            else if (select == 4) {
                break;
            }
            if (select!=2) {
                System.out.println("请输入正确的数字！");
                break;
            }
            user md = new user();
            while (true) {
                System.out.println("**********************************");
                System.out.println("       欢迎使用超市购物管理系统");
                System.out.println("       1.密码管理");
                System.out.println("       2.购物");
                System.out.println("       3.退出登录");
                System.out.println("**********************************");
                System.out.print("请选择，输入数字：");
                while (true) {
                    try {
                        select = Integer.parseInt(s.nextLine());
                        break;
                    } catch (Exception ignored) {
                    }
                }
              if (select == 1) {
                    md.Passwordmanagement();
                } else if (select == 2) {
                    md.shop();
                } else if (select == 3) {
                    break;
                } else {
                    System.out.println("请输入正确的数字！");
                }
                System.out.println("已退出<用户界面>，返回上一级菜单。");
            }
        }
    }
}