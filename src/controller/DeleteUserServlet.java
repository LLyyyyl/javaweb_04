package controller;

import com.google.gson.Gson;
import dao.UserDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "DeleteUserServlet",urlPatterns = "/DeleteUserServlet")
public class DeleteUserServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String ids = request.getParameter("ids");

        UserDao dao = new UserDao();
        boolean success = false;
        try {
            success = dao.delete(ids);
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 存放返回信息的Map
        Map<String, Object> map = new HashMap<String, Object>();
        if (success) {
            map.put("code", 0);
            map.put("info", "删除成功!");
        } else {
            map.put("code", 1);
            map.put("info", "删除失败!");
        }

        String jsonStr = new Gson().toJson(map);
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
        out.print(jsonStr);
        out.flush();
        out.close();
    }
}
