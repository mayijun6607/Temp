package main.myLogin.login;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import main.domain.user.User;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import java.net.HttpCookie;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by Mayijun on 2016/9/8.
 */
public class LoginServlet extends HttpServlet {
    private LoginService loginService;
    private ComboPooledDataSource comboPooledDataSource;
    private User user;

    public void setLoginService(LoginService loginService) {
        this.loginService = loginService;
    }

    public void setComboPooledDataSource(ComboPooledDataSource comboPooledDataSource) {
        this.comboPooledDataSource = comboPooledDataSource;
    }

    public void setUser(User user) {
        this.user = user;
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        WebApplicationContext webApplicationContext= WebApplicationContextUtils.getWebApplicationContext(this.getServletContext());
        comboPooledDataSource=webApplicationContext.getBean("dataSource",ComboPooledDataSource.class);
        loginService=webApplicationContext.getBean("loginService", main.myLogin.login.LoginService.class);
        user=webApplicationContext.getBean("user",User.class);

        HttpSession session=request.getSession();

        String username=request.getParameter("username");
        String password=request.getParameter("password");

        user.setUsername(username);
        user.setPassword(password);

        //错误时间 30秒
        int errorTime=30;

        //错误次数Cookie的创建
        Cookie[] all=request.getCookies();
        int isExist=0;
        for(Cookie temp:all){
            if("errorCount".equals(temp.getName())){
                isExist++;
            }
        }
        if(isExist==0){
            Cookie errorCount=new Cookie("errorCount","0");
            errorCount.setMaxAge(errorTime);
            response.addCookie(errorCount);
        }

        //session里loginUsername用来放密码错误时正确的用户名，username用来放登录成功的用户名
        try(Connection connection=comboPooledDataSource.getConnection()){
            int status=loginService.login(connection,user);
            if(status==-1){
                request.setAttribute("loginUsernameWarn","用户名错误!");
                session.setAttribute("loginUsername","");

                //用户输入错误次数
                Cookie[] all2=request.getCookies();
                for(Cookie count:all){
                    if("errorCount".equals(count.getName())){
                        int eCount=Integer.parseInt(count.getValue());
                        if(eCount>=3){
                            request.setAttribute("countWarn","输入错误次数过多，请稍后再试！");
                        }
                        else{
                            Cookie errorCount1=new Cookie("errorCount",eCount+1+"");
                            errorCount1.setMaxAge(errorTime);
                            response.addCookie(errorCount1);
                        }
                    }
                }

                request.getRequestDispatcher("title/login/login.jsp").forward(request,response);
            }
            else if(status==-2){
                request.setAttribute("loginPasswordWarn","密码错误!");
                session.setAttribute("loginUsername",user.getUsername());

                //用户输入错误次数
                Cookie[] all2=request.getCookies();
                for(Cookie count:all){
                    if("errorCount".equals(count.getName())){
                        int eCount=Integer.parseInt(count.getValue());
                        if(eCount>=3){
                            request.setAttribute("countWarn","输入错误次数过多，请稍后再试！");
                        }
                        else{
                            Cookie errorCount1=new Cookie("errorCount",eCount+1+"");
                            errorCount1.setMaxAge(errorTime);
                            response.addCookie(errorCount1);
                        }
                    }
                }

                request.getRequestDispatcher("title/login/login.jsp").forward(request,response);
            }
            else{
                //拿取lastURL
                String lastURL=null;
                if((String)session.getAttribute("lastURL")!=null) {
                    lastURL=(String) session.getAttribute("lastURL");
                }
                else{
                    lastURL="/index.jsp";
                }

                //创建Cookie
                Cookie cookie=new Cookie("username",user.getUsername());
                cookie.setMaxAge(604800);
                response.addCookie(cookie);
                //用户输入错误次数
                Cookie[] all2=request.getCookies();
                int isExist2=0;
                for(Cookie count:all){
                    if("errorCount".equals(count.getName())){
                        int eCount=Integer.parseInt(count.getValue());
                        isExist2++;
                        if(eCount>=3){
                            request.setAttribute("countWarn","输入错误次数过多，请稍后再试！");
                            request.getRequestDispatcher("title/login/login.jsp").forward(request,response);
                        }
                        else{
                            int userAuth=loginService.getAuth(connection,username);
                            session.setAttribute("username",user.getUsername());
                            session.setAttribute("userAuth",userAuth);
                            response.sendRedirect(lastURL);
                        }
                    }

                }
                if(isExist2==0){
                    int userAuth=loginService.getAuth(connection,username);
                    session.setAttribute("username",user.getUsername());
                    session.setAttribute("userAuth",userAuth);
                    response.sendRedirect(lastURL);
                }

            }
        }
        catch (SQLException e){
            throw new RuntimeException("登陆时连接池给予失败!");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
