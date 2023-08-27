import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class shopping {
    public static void addcar(){
        Scanner c = new Scanner(System.in);
        Connection coo = MYSQL.getconnection();
        Statement state = null;
        int choose;
        int amount;
        int id;
        String product=null;
        int price=0;
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
            System.out.print("输入你的用户序号");
            id=c.nextInt();
            System.out.print("输入你想要的物品id");
            choose=c.nextInt();
            System.out.print("输入你想要的物品数量");
            amount=c.nextInt();
            String sql = "select id,productname,price,stock from goods where id=?";
            PreparedStatement rs = coo.prepareStatement(sql);
            rs.setInt(1, choose);
            ResultSet ps = rs.executeQuery();
            while(ps.next()) {
                product=ps.getString("productname");
                price=ps.getInt("price") * amount;
            }
            String sql1 = "insert into shopcar?(id,paoductname,price,amount) values(?,?,?,?)";
            PreparedStatement pre = coo.prepareStatement(sql1);
            pre.setInt(1, id);
            pre.setInt(2, choose);
            pre.setString(3, product);
            pre.setInt(4, price);
            pre.setInt(5, amount);
            pre.executeUpdate();


            String sql3 = "select * from shopcar?";
            PreparedStatement pr = coo.prepareStatement(sql3);
            pr.setInt(1, id);
            ResultSet rr=pr.executeQuery();
            System.out.print("商品编号   商品名   价格   数量\n");
            while (rr.next()) {
                System.out.print(rr.getInt("id") + "        ");
                System.out.print(rr.getString("paoductname") + "    ");
                System.out.print(rr.getInt("price") + "    ");
                System.out.print(rr.getInt("amount") + "     ");
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
    public static void removecar(){
        Scanner c = new Scanner(System.in);
        Connection coo = MYSQL.getconnection();
        Statement state = null;
        int delete;
        int id;
        try {
            assert coo != null;
            state = coo.createStatement();
            System.out.print("输入你的用户序号");
            id=c.nextInt();
            System.out.print("您的购物车如下：\n");
            String sql3 = "select * from shopcar?";
            PreparedStatement pr = coo.prepareStatement(sql3);
            pr.setInt(1, id);
            ResultSet rr=pr.executeQuery();
            System.out.print("商品编号   商品名   价格   数量\n");
            while (rr.next()) {
                System.out.print(rr.getInt("id") + "        ");
                System.out.print(rr.getString("paoductname") + "    ");
                System.out.print(rr.getInt("price") + "    ");
                System.out.print(rr.getInt("amount") + "     ");
                System.out.print("\n");
            }
            System.out.print("输入你想要删除的物品id");
            delete=c.nextInt();
            String sql2 = "delete from shopcar? where id=?";
            PreparedStatement pst = coo.prepareStatement(sql2);
            pst.setInt(1, id);
            pst.setInt(2, delete);
            pst.executeUpdate();
            System.out.print("当前购物车信息表为：\n");
            String sql1 = "select * from shopcar?";
            PreparedStatement cr = coo.prepareStatement(sql1);
            cr.setInt(1, id);
            ResultSet rs=cr.executeQuery();
            System.out.print("商品编号   商品名   价格   数量\n");
            while (rs.next()) {
                System.out.print(rs.getInt("id") + "        ");
                System.out.print(rs.getString("paoductname") + "    ");
                System.out.print(rs.getInt("price") + "    ");
                System.out.print(rs.getInt("amount") + "     ");
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
    public static void changecar(){
        Scanner c = new Scanner(System.in);
        Connection coo = MYSQL.getconnection();
        Statement state = null;
        int change;
        int amount;
        int id;
        int price=0;
        int oldamount=1;
        try {
            assert coo != null;
            state = coo.createStatement();
            System.out.print("输入你的用户序号");
            id=c.nextInt();
            System.out.print("您的购物车如下：\n");
            String sql3 = "select * from shopcar?";
            PreparedStatement pr = coo.prepareStatement(sql3);
            pr.setInt(1, id);
            ResultSet rr=pr.executeQuery();
            System.out.print("商品编号   商品名   价格   数量\n");
            while (rr.next()) {
                System.out.print(rr.getInt("id") + "        ");
                System.out.print(rr.getString("paoductname") + "    ");
                System.out.print(rr.getInt("price") + "    ");
                System.out.print(rr.getInt("amount") + "     ");
                System.out.print("\n");
            }
            System.out.print("输入你想要修改的物品id");
            change=c.nextInt();
            System.out.print("输入你想要修改的数量");
            amount=c.nextInt();

            String sql = "select price,amount from shopcar? where id=?";
            PreparedStatement ss = coo.prepareStatement(sql);
            ss.setInt(1, id);
            ss.setInt(2, change);
            ResultSet ps = ss.executeQuery();
            while(ps.next())
            {
                price=ps.getInt("price");
                oldamount=ps.getInt("amount");
            }

            String sql2;
            sql2 = "update shopcar? set amount=?,price=? where id=?";
            PreparedStatement pre=coo.prepareStatement(sql2);
            pre.setInt(2,amount);
            pre.setInt(1,id);
            pre.setInt(3,(price/oldamount*amount));
            pre.setInt(4,change);
            pre.executeUpdate();
            System.out.println("修改成功！");

            System.out.print("当前购物车信息表为：\n");
            String sql1 = "select * from shopcar?";
            PreparedStatement cr = coo.prepareStatement(sql1);
            cr.setInt(1, id);
            ResultSet rs=cr.executeQuery();
            System.out.print("商品编号   商品名   价格   数量\n");
            while (rs.next()) {
                System.out.print(rs.getInt("id") + "        ");
                System.out.print(rs.getString("paoductname") + "    ");
                System.out.print(rs.getInt("price") + "    ");
                System.out.print(rs.getInt("amount") + "     ");
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
    public static void pay(){
        Scanner c = new Scanner(System.in);
        Connection coo = MYSQL.getconnection();
        Statement state = null;
        int id;
        int value;
        String name=null;
        int price=0;
        int amount=0;
        int balance=0;
        int newbalance=0;
        int carid=0;
        try {
            assert coo != null;
            state = coo.createStatement();
            System.out.print("输入你的用户序号");
            id=c.nextInt();
            System.out.print("您的购物车如下：\n");
            String sql3 = "select * from shopcar?";
            PreparedStatement pr = coo.prepareStatement(sql3);
            pr.setInt(1, id);
            ResultSet rr=pr.executeQuery();
            System.out.print("商品编号   商品名   价格   数量\n");
            while (rr.next()) {
                System.out.print(rr.getInt("id") + "        ");
                System.out.print(rr.getString("paoductname") + "    ");
                System.out.print(rr.getInt("price") + "    ");
                System.out.print(rr.getInt("amount") + "     ");
                System.out.print("\n");
            }
            System.out.print("输入你的要结账的物品id");
            value=c.nextInt();
            String sql = "select paoductname,amount,price from shopcar? where id=?";
            PreparedStatement rs = coo.prepareStatement(sql);
            rs.setInt(1, id);
            rs.setInt(2, value);
            ResultSet ps = rs.executeQuery();
            while (ps.next()) {
                name=ps.getString("paoductname");
                price=ps.getInt("price");
                amount=ps.getInt("amount");
            }
            String sql1 = "select balance from useraccount where id=?";
            PreparedStatement rss = coo.prepareStatement(sql1);
            rss.setInt(1, id);
            ResultSet pss = rss.executeQuery();
            while (pss.next()) {
                balance=pss.getInt("balance");
            }
            System.out.printf("你的余额为%d\n",balance);
            while(true) {
                if (balance < price) {
                    System.out.printf("余额不足，请充值：");
                    balance += c.nextInt();
                } else {
                    newbalance = balance - price;
                    break;
                }
            }
            String sql5 = "select * from history?";
            PreparedStatement prd = coo.prepareStatement(sql5);
            prd.setInt(1, id);
            ResultSet rd=prd.executeQuery();
            while (rd.next()) {
                if(carid<rd.getInt("id"))
                    carid=rd.getInt("id")+1;
            }
            String sql2 = "insert into history?(id,paoductname,price,amount,buydate) values(?,?,?,?,?)";
            PreparedStatement pre=coo.prepareStatement(sql2);
            pre.setInt(1,id);
            pre.setString(3,name);
            pre.setInt(4,price);
            pre.setInt(5,amount);
            pre.setString(6,"2023-2-13");
            pre.setInt(2,carid);
            pre.executeUpdate();
            System.out.println("历史记录更新成功！");
            String sql4;
            sql4 = "update useraccount set balance=? where id=?";
            PreparedStatement prc=coo.prepareStatement(sql4);
            prc.setInt(1,newbalance);
            prc.setInt(2,id);
            prc.executeUpdate();
            System.out.printf("你的余额为%d\n",newbalance);

            String sql6= "delete from shopcar? where id=?";
            PreparedStatement pst = coo.prepareStatement(sql6);
            pst.setInt(1, id);
            pst.setInt(2, value);
            pst.executeUpdate();
            String sql7= "select * from shopcar?";
            PreparedStatement cr = coo.prepareStatement(sql7);
            cr.setInt(1, id);
            ResultSet rh=cr.executeQuery();
            System.out.print("你的购物车信息为：\n");
            System.out.print("商品编号   商品名   价格   数量\n");
            while (rh.next()) {
                System.out.print(rh.getInt("id") + "        ");
                System.out.print(rh.getString("paoductname") + "    ");
                System.out.print(rh.getInt("price") + "    ");
                System.out.print(rh.getInt("amount") + "     ");
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
    public static void shophistory(){
        Scanner c = new Scanner(System.in);
        Connection coo = MYSQL.getconnection();
        Statement state = null;
        int id;
        try {
            assert coo != null;
            state = coo.createStatement();
            System.out.print("输入你的用户序号");
            id=c.nextInt();
            System.out.print("您的购物历史如下：\n");
            String sql3 = "select * from history?";
            PreparedStatement pr = coo.prepareStatement(sql3);
            pr.setInt(1, id);
            ResultSet rr=pr.executeQuery();
            System.out.print("购买编号   商品名   价格   数量   购买日期\n");
            while (rr.next()) {
                System.out.print(rr.getInt("id") + "        ");
                System.out.print(rr.getString("paoductname") + "    ");
                System.out.print(rr.getInt("price") + "    ");
                System.out.print(rr.getInt("amount") + "     ");
                System.out.print(rr.getString("buydate") + "     ");
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
}
