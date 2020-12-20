package tools;

import com.mysql.jdbc.Connection;

import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DataBaseConnection {

    private Connection con;
    private static String className;
    private static String url;
    private static String user;
    private static String password;


    //静态代码
    static {
        try {
            Properties pro = new Properties();
            InputStream is =DataBaseConnection.class.getResourceAsStream("/resources/jdbc.properties");
            pro.load(is);
            className = pro.getProperty("className");
            url = pro.getProperty("url");
            user = pro.getProperty("user");
            password = pro.getProperty("password");
            Class.forName(className);
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }

    public DataBaseConnection() {

    }

    public Connection getConnection() {
        try {
            con = (Connection) DriverManager.getConnection(url,user,password);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return con;
    }

    public void close() {
        try {
            if(con!=null)
                con.close();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
