package dao;

import com.mysql.jdbc.Connection;
import vo.City;
import vo.Province;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ProvinceCityDao {
    public ArrayList<Province> queryProvince() throws Exception {
        ArrayList<Province> list=new ArrayList<Province>();

        Class.forName("com.mysql.jdbc.Driver");
        Connection con=(Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/excise?useUnicode=true&characterEncoding=utf-8",
                "root","Lyl99999");
        try {

            // 3.创建语句
            String sql = "select * from t_province ";
            PreparedStatement ps = con.prepareStatement(sql);
            // 4.执行语句
            ResultSet rs = ps.executeQuery();
            // 5.结果处理
            while (rs.next()) {
                Province province = new Province();
                province.setProvinceCode(rs.getString("provinceCode"));
                province.setProvinceName(rs.getString("provinceName"));

                list.add(province); // 将对象存放于集合中
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 6.关闭连接
            con.close();
        }

        return list;
    }

    public ArrayList<City> queryCity(String provinceCode) throws Exception {
        ArrayList<City> list=new ArrayList<City>();
        Class.forName("com.mysql.jdbc.Driver");
        Connection con=(Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/excise?useUnicode=true&characterEncoding=utf-8",
                "root","Lyl99999");
        try {

            // 3.创建语句
            String sql = "select * from t_city where provinceCode=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, provinceCode);
            // 4.执行语句
            ResultSet rs = ps.executeQuery();
            // 5.结果处理
            while (rs.next()) {
                City city = new City();
                city.setCityCode(rs.getString("cityCode"));
                city.setProvinceCode(rs.getString("provinceCode"));
                city.setCityName(rs.getString("cityName"));

                list.add(city); // 将对象存放于集合中
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 6.关闭连接
            con.close();
        }

        return list;
    }
}
