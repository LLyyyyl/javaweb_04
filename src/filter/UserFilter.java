package filter;

import vo.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(filterName = "UserFilter")
public class UserFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        //将ServletRequest类型参数转换成HttpServletRequest类型
        HttpServletRequest httpServletRequest = (HttpServletRequest) req;

        HttpSession session = httpServletRequest.getSession();
        //Object role = session.getAttribute("role");
        String role = session.getAttribute("role").toString();
        System.out.println(role);
//        String role = "管理员";


        if(!(role.equals("管理员"))){//身份为普通用户
            System.out.println("未登录");
            String errors="该资源您无权访问";
            req.setAttribute("errors", errors);
            req.getRequestDispatcher("/error.jsp").forward(req,resp);
        }else{
            chain.doFilter(req,resp);//放行
        }
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
