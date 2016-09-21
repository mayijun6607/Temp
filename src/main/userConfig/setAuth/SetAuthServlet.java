package main.userConfig.setAuth;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import main.domain.user.User;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.util.List;

/**
 * Created by Mayijun on 2016/9/20.
 */
public class SetAuthServlet extends HttpServlet{
    private ComboPooledDataSource comboPooledDataSource;
    private SetAuthService setAuthService;

    public void setComboPooledDataSource(ComboPooledDataSource comboPooledDataSource) {
        this.comboPooledDataSource = comboPooledDataSource;
    }

    public void setSetAuthService(SetAuthService setAuthService) {
        this.setAuthService = setAuthService;
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        WebApplicationContext webApplicationContext= WebApplicationContextUtils.getWebApplicationContext(this.getServletContext());
        comboPooledDataSource=webApplicationContext.getBean("dataSource",ComboPooledDataSource.class);
        setAuthService=webApplicationContext.getBean("setAuthService",SetAuthService.class);
        HttpSession session=request.getSession();
        //获得用户权限等级
        int userAuth=0;
        if(session.getAttribute("username")==null){
            userAuth=0;
        }
        else {
            userAuth = (Integer) session.getAttribute("userAuth");
        }

        String queryName=request.getParameter("queryName");
        String isBlur=request.getParameter("isBlur");
        //System.out.println(queryName+"..."+isBlur+"..."+userAuth);
        //System.out.println(isBlur);

        try(Connection connection=comboPooledDataSource.getConnection()){
            if("yes".equals(isBlur)) {
                List<User> userList = setAuthService.getBlurUser(connection, queryName, userAuth);
                if(userList.toString()=="[]"){
                    request.setAttribute("queryWarn","目标用户不存在或您权限不够!");
                    request.getRequestDispatcher("/userConfig/setAuth.jsp").forward(request,response);
                }
                else{
                    request.setAttribute("userList",userList);
                    request.setAttribute("BuserName",setAuthService.getBlurUsername(userList));
                    request.setAttribute("BuserAuth",setAuthService.getBlurUserAuth(userList));
                    request.getRequestDispatcher("/userConfig/setAuth.jsp").forward(request,response);
                }
            }
            else{
                int EauthId=setAuthService.getExactUser(connection,queryName,userAuth);
                if(EauthId==0){
                    request.setAttribute("queryWarn","目标用户不存在或您权限不够!");
                    request.getRequestDispatcher("/userConfig/setAuth.jsp").forward(request,response);
                }
                else{
                    request.setAttribute("EuserName",queryName);
                    request.setAttribute("EauthId",EauthId);
                    request.getRequestDispatcher("/userConfig/setAuth.jsp").forward(request,response);
                }
            }
        }
        catch (Exception e){
            //e.printStackTrace();
            throw new RuntimeException("设置权限Servlet出错！");
        }

    }
}
