import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
public class MYSQL {

    private static String driver;
    private static String url;
    private static String username;
    private static String password;
   static {
       InputStream is=MYSQL.class.getClassLoader().getResourceAsStream("sql.properties");
       Properties p=new Properties();
       try {
           p.load(is);
           driver=p.getProperty("driver");
           url=p.getProperty("jdbc.url");
           username=p.getProperty("username");
           password=p.getProperty("password");
           Class.forName(driver);
       } catch (Exception e) {
           System.out.print("驱动加载失败！");
           throw new RuntimeException(e);
       }
   }
   public static Connection getconnection(){
       try {
           return DriverManager.getConnection(url, username, password);
       } catch (SQLException e) {
           System.out.print("驱动连接失败！");
           e.printStackTrace();
       }
       return null;
   }
}
