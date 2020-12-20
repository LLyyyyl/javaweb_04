package controller;

import com.google.gson.Gson;
import dao.ProvinceCityDao;
import vo.City;
import vo.Province;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

@WebServlet(name = "SelectProvinceCityServlet",urlPatterns = "/SelectProvinceCityServlet")
public class SelectProvinceCityServlet extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String provinceCode = request.getParameter("provinceCode");
        String jsonStr = "";
        ProvinceCityDao dao = new ProvinceCityDao();
        if (provinceCode ==null) {  //没有请求参数provinceCode，表示查询所有省份列表
            ArrayList<Province> list = null;
            try {
                list = dao.queryProvince();
            } catch (Exception e) {
                e.printStackTrace();
            }
            jsonStr = new Gson().toJson(list);
        } else { //按provinceCode查询对应的城市的列表
            ArrayList<City> list = null;
            try {
                list = dao.queryCity(provinceCode);
            } catch (Exception e) {
                e.printStackTrace();
            }
            jsonStr = new Gson().toJson(list);
        }
        // 字符流输出字符串
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
        System.out.println(jsonStr);
        out.print(jsonStr);
        out.flush();
        out.close();
    }
}
