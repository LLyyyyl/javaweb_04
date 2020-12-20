package controller;

import dao.DownloadListDao;
import dao.UserDao;
import vo.User;
import vo.download;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.ArrayList;

import static dao.DownloadListDao.*;

@WebServlet(name = "LoginServlet",urlPatterns = "/LoginServlet")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");

        User user=new User();
        UserDao userDao = new UserDao();
        DownloadListDao downloadListDao = new DownloadListDao();
        HttpSession session = request.getSession();

        String verityCode = session.getAttribute("verityCode").toString();
        System.out.println(verityCode);

        String userName = request.getParameter("userName");
        String password = request.getParameter("password");
        String vcode = request.getParameter("vcode");

        System.out.println(userName+","+password+","+vcode);


		boolean result = vcode.equalsIgnoreCase(verityCode);//判断验证码是否正确
		//System.out.println(result);

		if(result){
			//System.out.println("验证码正确");

			try {
				if(UserDao.select(userName, password)!=null) {//登录成功
					user=UserDao.select(userName, password);
					String chrName = user.getChrName();
					String role = user.getRole();
                    System.out.println("role"+role);

					request.getSession().setAttribute("user","user");
					request.getSession().setAttribute("role",role);

//                    String role1 = session.getAttribute("role").toString();
//                    System.out.println("role1"+role1);

                    //自动登录
                    String[] autoLogin = request.getParameterValues("autoLogin");
                    System.out.println(autoLogin);
                    if (autoLogin!=null) {//七天免登录被选中
                        Cookie PasswordCookie=null;
                        Cookie UsernameCookie=null;

                        PasswordCookie = new Cookie("password", password);
                        UsernameCookie=new Cookie("userName",userName);
                        PasswordCookie.setMaxAge(7 * 24 * 60 * 60);
                        UsernameCookie.setMaxAge(7*24*60*60);
                        PasswordCookie.setPath(request.getContextPath());
                        UsernameCookie.setPath(request.getContextPath());
                        response.addCookie(PasswordCookie);
                        response.addCookie(UsernameCookie);
                    }

                    Cookie[] cookies=request.getCookies();
                    System.out.println(cookies);
                    String cookieusername="";
                    String cookiepassword="";
                    User cookieuser=null;
                    if (cookies!=null){
                        for(Cookie cookie1:cookies){
                            String cookie1name=cookie1.getName();
                            String cookie1Value=cookie1.getValue();
                            if (cookie1name.equals("username")){
                                cookieusername=cookie1Value;
                            }else if (cookie1name.equals("password"))
                                cookiepassword=cookie1Value;
                        }
                        try {
                            cookieuser=userDao.select(cookieusername,cookiepassword);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        if (cookieuser!=null&&cookiepassword.equals(cookieuser.getPassword()))
                            request.getRequestDispatcher("/main.jsp").forward(request,response);
                    }else
                        request.getRequestDispatcher("/login.html").forward(request,response);


//                    session.setAttribute("User", user);
//                    session.setAttribute("userName", userName);

                    ArrayList<download> loadlist=new ArrayList<download>();

                    try {
                        loadlist=DownloadListDao.selectfile();
                    }catch(Exception e) {
                        e.printStackTrace();
                    }
                    request.getSession().setAttribute("list", loadlist);

                    request.setAttribute("chrName",chrName);
                    request.getRequestDispatcher("/main.jsp").forward(request, response);//登录成功跳转主页面
				} else{//数据库中不催在当前用户或密码错误
                    String errors="用户名或密码错误!";
                    request.setAttribute("errors", errors);
                    request.getRequestDispatcher("/error.jsp").forward(request, response);
				}

			}catch(Exception e) {
				e.printStackTrace();
			}

		} else{//验证码错误
            String errors="抱歉，您输入的验证码不正确";
            request.setAttribute("errors", errors);
            request.getRequestDispatcher("/error.jsp").forward(request, response);
        }
    }
}
