import java.sql.*;
import java.util.Scanner;
public class administrator{
   public void Passwordmanagement() {
      int select=0;
      String name;
      int newpass;
      Scanner c = new Scanner(System.in);
      while (true) {
         System.out.println("**********************************");
         System.out.println("       欢迎使用超市购物管理系统");
         System.out.println("       1.修改密码");
         System.out.println("       2.重置用户密码");
         System.out.println("       3.退出");
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
            name = c.nextLine();
            System.out.print("请输入新密码：");
            newpass = c.nextInt();
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
               pre.setInt(2, newpass);
               pre.executeUpdate();
               System.out.println("修改成功！");
            } catch (Exception e) {
               throw new RuntimeException(e);
            }
         }
         if (select == 2) {
            System.out.print("请输入用户名：");
            name=c.nextLine();
            Connection coo = MYSQL.getconnection();
            Statement state = null;
            try {
               assert coo != null;
               state = coo.createStatement();
               String sql;
               sql = "update useraccount set password=? where account=?";
               PreparedStatement pre=coo.prepareStatement(sql);
               pre.setString(2,name);
               pre.setInt(1,111111);
               pre.executeUpdate();
               System.out.println("修改成功！");
            } catch (Exception e) {
               throw new RuntimeException(e);
            }
         }
         if (select == 3) {
            break;
         }
         if (select != 1 && select != 2) {
            System.out.println("请输入正确的数字！");
         }
      }
   }
   public void Usermanagement(){
      int select=0;
      int id;
      Scanner c = new Scanner(System.in);
      while (true) {
         System.out.println("**********************************");
         System.out.println("       欢迎使用超市购物管理系统");
         System.out.println("       1.客户信息表");
         System.out.println("       2.删除客户信息");
         System.out.println("       3.查询客户信息");
         System.out.println("       4.退出");
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
               Connection coo = MYSQL.getconnection();
               Statement state = null;
               try {
                  assert coo != null;
                  state = coo.createStatement();
                  ResultSet res = state.executeQuery("select * from useraccount");
                  System.out.print("用户名称   密码   年龄   性别   序号\n");
                  while (res.next()) {
                     System.out.print(res.getString("account") + "  ");
                     System.out.print(res.getInt("password") + "  ");
                     System.out.print(res.getInt("age") + "    ");
                     System.out.print(res.getString("sex") + "       ");
                     System.out.print(res.getInt("id"));
                     System.out.print("\n");
                  }
               } catch (Exception e) {
                  throw new RuntimeException(e);
               }
               System.out.print("按0键返回上个页面");
               int k = c.nextInt();
               if (k != 0) {
                  k = c.nextInt();
               }
            }
            if (select == 2) {
               Connection coo = MYSQL.getconnection();
               Statement state = null;
               try {
                  assert coo != null;
                  state = coo.createStatement();
                  ResultSet res = state.executeQuery("select * from useraccount");
                  System.out.print("用户名称   密码   年龄   性别   序号\n");
                  while (res.next()) {
                     System.out.print(res.getString("account") + "  ");
                     System.out.print(res.getInt("password") + "  ");
                     System.out.print(res.getInt("age") + "    ");
                     System.out.print(res.getString("sex") + "       ");
                     System.out.print(res.getInt("id"));
                     System.out.print("\n");
                  }
                  System.out.print("请输入要删除的客户序号");
                  id = c.nextInt();
                  String sql3 = "delete from useraccount where id=?";
                  PreparedStatement pst = coo.prepareStatement(sql3);
                  pst.setInt(1, id);
                  pst.executeUpdate();
                  String sql1="drop table shopcar?";
                  PreparedStatement prr=coo.prepareStatement(sql1);
                  prr.setInt(1,(id));
                  prr.executeUpdate();
                  String sql2="drop table history?";
                  PreparedStatement prc=coo.prepareStatement(sql2);
                  prc.setInt(1,(id));
                  prc.executeUpdate();
                  System.out.print("当前客户信息表为：\n");
                  ResultSet rss = state.executeQuery("select * from useraccount");
                  System.out.print("用户名称   密码   年龄   性别   序号\n");
                  while (rss.next()) {
                     System.out.print(rss.getString("account") + "  ");
                     System.out.print(rss.getInt("password") + "  ");
                     System.out.print(rss.getInt("age") + "    ");
                     System.out.print(rss.getString("sex") + "       ");
                     System.out.print(rss.getInt("id"));
                     System.out.print("\n");
                  }
               } catch (Exception e) {
                  throw new RuntimeException(e);
               }
               System.out.print("按0键返回上个页面");
               int m = c.nextInt();
               if (m != 0) {
                  m = c.nextInt();
               }
            }
            if (select == 3) {
               Connection coo = MYSQL.getconnection();
               Statement state = null;
               try {
                  assert coo != null;
                  state = coo.createStatement();
                  ResultSet res = state.executeQuery("select * from useraccount");
                  System.out.print("用户名称   密码   年龄   性别   序号\n");
                  while (res.next()) {
                     System.out.print(res.getString("account") + "  ");
                     System.out.print(res.getInt("password") + "  ");
                     System.out.print(res.getInt("age") + "    ");
                     System.out.print(res.getString("sex") + "       ");
                     System.out.print(res.getInt("id"));
                     System.out.print("\n");
                  }
                  System.out.print("请输入要查询的客户id");
                  id= c.nextInt();
                  String sql = "select account,password,age,sex,id from useraccount where id=?";
                  PreparedStatement rs = coo.prepareStatement(sql);
                  rs.setInt(1, id);
                  ResultSet ps = rs.executeQuery();
                  System.out.print("用户名称   密码   年龄   性别   序号\n");
                  while (ps.next()) {
                     System.out.print(ps.getString("account") + "  ");
                     System.out.print(ps.getInt("password") + "  ");
                     System.out.print(ps.getInt("age") + "    ");
                     System.out.print(ps.getString("sex") + "       ");
                     System.out.print(ps.getInt("id"));
                     System.out.print("\n");
                  }
               } catch (SQLException e) {
                  throw new RuntimeException(e);
               }
               System.out.print("按0键返回上个页面");
               int l = c.nextInt();
               if (l != 0) {
                  l = c.nextInt();
               }
            }
            if(select==4)
               break;
      }
   }
   public void Goodsmanagement(){
      int select=0;
      Scanner c=new Scanner(System.in);
      goods mc=new goods();
      while (true) {
         System.out.println("**********************************");
         System.out.println("       欢迎使用超市购物管理系统");
         System.out.println("       1.商品信息表");
         System.out.println("       2.添加商品信息");
         System.out.println("       3.修改商品信息");
         System.out.println("       4.删除商品信息");
         System.out.println("       5.查询商品信息");
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
            mc.goodslist();
         }
         if (select == 2) {
            mc.addgoods();
         }
         if (select == 3) {
            mc.changegoods();
         }
         if(select==4){
            mc.deletegoods();
         }
         if(select==5){
            mc.serchgoods();
         }
         if(select==6){
            break;
         }
      }
   }
}