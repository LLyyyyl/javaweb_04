package filter;

import vo.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(filterName = "PermissionFilter")
public class PermissionFilter implements Filter {
    private String notCheckPath;//不需要过滤的请求地址

    public void destroy() {
    }

    //拦截请求
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        //将ServletRequest类型参数转换成HttpServletRequest类型
        HttpServletRequest httpServletRequest = (HttpServletRequest) req;

        //获取请求的地址
        String path = httpServletRequest.getServletPath();
        System.out.println("请求地址"+path);
        //System.out.println("地址："+notCheckPath);

        HttpSession session = httpServletRequest.getSession();
        Object user = session.getAttribute("user");

        if(!notCheckPath.contains(path)){//请求地址不在不需要过滤的列表范围内

            if(user == null){
                System.out.println("未登录");
                String errors="您尚未登录，无权访问界面！";
                req.setAttribute("errors", errors);
                req.getRequestDispatcher("/error.jsp").forward(req,resp);
            }else {//已经登录，直接放行
                chain.doFilter(req,resp);
            }
        }else{//该请求地址无需过滤，直接放行
            chain.doFilter(req,resp);
        }
    }

    public void init(FilterConfig config) throws ServletException {
        //初始化
        notCheckPath = config.getInitParameter("notCheckPath");
    }

}
