import java.sql.*;
import java.util.Scanner;

public class goods {
    public static void goodslist() {
        Scanner c = new Scanner(System.in);
        Connection coo = MYSQL.getconnection();
        Statement state = null;
        try {
            assert coo != null;
            state = coo.createStatement();
            ResultSet res = state.executeQuery("select * from goods");
            System.out.print("商品编号   商品名   价格   库存量     生产日期       保质期(年)\n");
            while (res.next()) {
                System.out.print(res.getInt("id") + "        ");
                System.out.print(res.getString("productname") + "    ");
                System.out.print(res.getInt("price") + "    ");
                System.out.print(res.getInt("stock") + "     ");
                System.out.print(res.getString("producedate")+"        ");
                System.out.print(res.getInt("shelflife"));
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
    public static void addgoods(){
        Scanner s=new Scanner(System.in);
        String productname;
        String producedate;
        int id;
        int price;
        int stock;
        int shelflife;
        System.out.print("请输入商品名：");
        productname= s.nextLine();
        System.out.print("请输入生产日期：");
        producedate = s.nextLine();
        System.out.print("请输入商品id：");
        id = s.nextInt();
        System.out.print("请输入商品价格：");
        price= s.nextInt();
        System.out.print("请输入库存量：");
        stock= s.nextInt();
        System.out.print("请输入保质期（年）：");
        shelflife= s.nextInt();
        Connection coo = MYSQL.getconnection();
        Statement state = null;
        try {
            assert coo != null;
            state = coo.createStatement();
            String sql = "insert into goods(id,productname,price,stock,producedate,shelflife) values(?,?,?,?,?,?)";
            PreparedStatement pre=coo.prepareStatement(sql);
            pre.setInt(1,id);
            pre.setString(2,productname);
            pre.setInt(3,price);
            pre.setInt(4,stock);
            pre.setString(5,producedate);
            pre.setInt(6,shelflife);
            pre.executeUpdate();
            System.out.println("商品添加成功！");
            System.out.print("当前商品信息表为：\n");
            ResultSet rss = state.executeQuery("select * from goods");
            System.out.print("商品编号   商品名   价格   库存量     生产日期       保质期(年)\n");
            while (rss.next()) {
                System.out.print(rss.getInt("id") + "        ");
                System.out.print(rss.getString("productname") + "    ");
                System.out.print(rss.getInt("price") + "    ");
                System.out.print(rss.getInt("stock") + "     ");
                System.out.print(rss.getString("producedate")+"        ");
                System.out.print(rss.getInt("shelflife"));
                System.out.print("\n");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        System.out.print("按0键返回上个页面");
        int k = s.nextInt();
        if (k != 0) {
            k = s.nextInt();
        }
    }
    public static void changegoods(){
        String productname;
        String producedate;
        int id;
        int price;
        int stock;
        int shelflife;
        Scanner c=new Scanner(System.in);
        System.out.print("请输入要修改的商品id：");
        id = c.nextInt();
        c.nextLine();
        System.out.print("请输入修改后的商品名：");
        productname= c.nextLine();
        System.out.print("请输入修改后的生产日期：");
        producedate = c.nextLine();
        System.out.print("请输入修改后的商品价格：");
        price= c.nextInt();
        System.out.print("请输入修改后的库存量：");
        stock= c.nextInt();
        System.out.print("请输入修改后的保质期（年）：");
        shelflife= c.nextInt();
        Connection coo = MYSQL.getconnection();
        Statement state = null;
        try {
            assert coo != null;
            state = coo.createStatement();
            String sql;
            sql = "update goods set productname=?,price=?,stock=?,producedate=?,shelflife=? where id=?";
            PreparedStatement pre=coo.prepareStatement(sql);
            pre.setInt(6,id);
            pre.setString(1,productname);
            pre.setInt(2,price);
            pre.setInt(3,stock);
            pre.setString(4,producedate);
            pre.setInt(5,shelflife);
            pre.executeUpdate();
            System.out.println("修改成功！");
            System.out.print("当前商品信息表为：\n");
            ResultSet rss = state.executeQuery("select * from goods");
            System.out.print("商品编号   商品名   价格   库存量     生产日期       保质期(年)\n");
            while (rss.next()) {
                System.out.print(rss.getInt("id") + "        ");
                System.out.print(rss.getString("productname") + "    ");
                System.out.print(rss.getInt("price") + "    ");
                System.out.print(rss.getInt("stock") + "     ");
                System.out.print(rss.getString("producedate")+"        ");
                System.out.print(rss.getInt("shelflife"));
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
    public static void deletegoods(){
        int id;
        Scanner c=new Scanner(System.in);
        Connection coo = MYSQL.getconnection();
        Statement state = null;
        try {
            assert coo != null;
            state = coo.createStatement();
            System.out.print("请输入要删除的商品id");
            id = c.nextInt();
            String sql3 = "delete from goods where id=?";
            PreparedStatement pst = coo.prepareStatement(sql3);
            pst.setInt(1, id);
            pst.executeUpdate();
            System.out.print("当前商品信息表为：\n");
            ResultSet rss = state.executeQuery("select * from goods");
            System.out.print("商品编号   商品名   价格   库存量     生产日期       保质期(年)\n");
            while (rss.next()) {
                System.out.print(rss.getInt("id") + "        ");
                System.out.print(rss.getString("productname") + "    ");
                System.out.print(rss.getInt("price") + "    ");
                System.out.print(rss.getInt("stock") + "     ");
                System.out.print(rss.getString("producedate")+"        ");
                System.out.print(rss.getInt("shelflife"));
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
    public static void serchgoods(){
        Scanner c=new Scanner(System.in);
        int id;
        Connection coo = MYSQL.getconnection();
        Statement state = null;
        try {
            assert coo != null;
            state = coo.createStatement();
            ResultSet rss = state.executeQuery("select * from goods");
            System.out.print("商品编号   商品名   价格   库存量     生产日期       保质期(年)\n");
            while (rss.next()) {
                System.out.print(rss.getInt("id") + "        ");
                System.out.print(rss.getString("productname") + "    ");
                System.out.print(rss.getInt("price") + "    ");
                System.out.print(rss.getInt("stock") + "     ");
                System.out.print(rss.getString("producedate")+"        ");
                System.out.print(rss.getInt("shelflife"));
                System.out.print("\n");
            }
            System.out.print("请输入要查询的商品id");
            id = c.nextInt();
            String sql = "select id,productname,price,stock,producedate,shelflife from goods where id=?";
            PreparedStatement rs = coo.prepareStatement(sql);
            rs.setInt(1, id);
            ResultSet ps = rs.executeQuery();
            System.out.print("商品编号   商品名   价格   库存量     生产日期       保质期(年)\n");
            while (ps.next()) {
                System.out.print(ps.getInt("id") + "        ");
                System.out.print(ps.getString("productname") + "    ");
                System.out.print(ps.getInt("price") + "    ");
                System.out.print(ps.getInt("stock") + "     ");
                System.out.print(ps.getString("producedate")+"        ");
                System.out.print(ps.getInt("shelflife"));
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
}
