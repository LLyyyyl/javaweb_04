package dao;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import vo.Page;
import vo.User;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class UserDao {
    public static User select(String userName, String password) throws Exception{//查询用户信息
        Class.forName("com.mysql.jdbc.Driver");
        Connection con=(Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/excise?useUnicode=true&characterEncoding=utf-8",
                "root","Lyl99999");

        String sql1="select * from t_user";
        PreparedStatement ps=(PreparedStatement) con.prepareStatement(sql1);
        ResultSet rs=(ResultSet) ps.executeQuery();

        while(rs.next()) {
            String name=rs.getString("userName");
            String word=rs.getString("password");

            if(name.equals(userName) && word.equals(password)) {
                String chrName=rs.getString("chrName");
                String role=rs.getString("role");
                User user=new User(name,word,chrName,role);
                return user;
            }
        }

        ps.close();
        rs.close();
        con.close();

        return null;
    }

    public User get(String userName) throws SQLException, ClassNotFoundException {
        User user = null;

        Class.forName("com.mysql.jdbc.Driver");
        Connection con=(Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/excise?useUnicode=true&characterEncoding=utf-8",
                "root","Lyl99999");

        String sql = "select * from t_user where username=?";
        PreparedStatement ps = (PreparedStatement) con.prepareStatement(sql);

        ps.setString(1, userName);
        ResultSet rs = ps.executeQuery();

        while(rs.next()) {
            String name=rs.getString("userName");
            String chrName=rs.getString("chrName");
            String word=rs.getString("password");
            String role=rs.getString("role");
            user = new User(name,chrName,word,role);
        }

        ps.close();
        rs.close();
        con.close();

        return user;
    }

    public boolean delete(String ids) throws Exception {
        boolean success = false;

        Class.forName("com.mysql.jdbc.Driver");
        Connection con=(Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/excise?useUnicode=true&characterEncoding=utf-8",
                "root","Lyl99999");

        String[] array = ids.split(",");
        try {

            // 创建语句
            String sql = "delete from t_user where userName in(";
            StringBuffer sb = new StringBuffer("?");
            for (int i = 0; i < array.length - 1; i++) {
                sb.append(",?");
            }
            sql = sql + sb.toString() + ")";
            System.out.println(sql);
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            for (int i = 0; i < array.length; i++) {
                pst.setString(i + 1, array[i]);
            }

            int i = pst.executeUpdate();
            if (i > 0) {
                success = true;
            }

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            // 6.关闭连接
            con.close();
        }

        return success;

    }

    public int count(User user, Page page) throws Exception {
        int total = 0;

        Class.forName("com.mysql.jdbc.Driver");
        try (Connection con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/excise?useUnicode=true&characterEncoding=utf-8",
                "root", "Lyl99999")) {

            StringBuffer condition = new StringBuffer();// 查询条件
            if (user.getUserName() != null && !"".equals(user.getUserName())) {
                condition.append(" and userName like '%")
                        .append(user.getUserName()).append("%'");
            }
            if (user.getChrName() != null && !"".equals(user.getChrName())) {
                condition.append(" and chrName like '%").append(user.getChrName())
                        .append("%'");
            }

            String sql = "select count(*) from t_user A left join t_province B";
            sql = sql
                    + " on A.provinceCode = B.provinceCode left join t_city C on A.cityCode = C.cityCode ";
            sql = sql + " where  1=1 ";
            sql = sql + condition;
            System.out.println(sql);

            try {
                Statement stm = con.createStatement();
                ResultSet rs = stm.executeQuery(sql);
                if (rs.next()) {
                    total = rs.getInt(1);
                }
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } finally {
                // 6.关闭连接
                con.close();
            }
        }

        return total;
    }
    public ArrayList<User> query(User user, Page page) {
        ArrayList<User> list = new ArrayList<>();
        return list;
    }

}
