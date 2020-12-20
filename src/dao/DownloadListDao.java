package dao;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import tools.DataBaseConnection;
import vo.download;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.ArrayList;

public class DownloadListDao {
//    private DataBaseConnection dbc;
//    Connection con = new DataBaseConnection().getConnection();

    public static ArrayList<download> selectfile() throws Exception{

//        this.dbc = new DataBaseConnection(); // 连接数据库
        //?useUnicode=true&characterEncoding=utf-8
        ArrayList<download> downlist=new ArrayList<download>();
        Class.forName("com.mysql.jdbc.Driver");
        Connection con=(Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/excise?useUnicode=true&characterEncoding=utf-8",
                "root","Lyl99999");

        String sql2="select * from t_downloadList";
        PreparedStatement ps2= (PreparedStatement) con.prepareStatement(sql2);
        ResultSet rs=ps2.executeQuery();

        while(rs.next()) {
            int id=rs.getInt("id");

            String name=rs.getString("name");
            String path=rs.getString("path");
            String description=rs.getString("description");
            long size=rs.getLong("size");
            int star=rs.getInt("star");
            String image=rs.getString("image");
            String time=rs.getString("time");
            download dload=new download(id,name,path,description,size,star,image,time);
            downlist.add(dload);
        }
        ps2.close();
        rs.close();
        con.close();
        return downlist;
    }
}
