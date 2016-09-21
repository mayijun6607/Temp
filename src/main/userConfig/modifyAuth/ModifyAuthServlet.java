package main.userConfig.modifyAuth;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.mchange.v2.c3p0.ConnectionCustomizer;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;

/**
 * Created by Mayijun on 2016/9/21.
 */
public class ModifyAuthServlet  extends HttpServlet {
    private ComboPooledDataSource comboPooledDataSource;
    private ModifyAuthService modifyAuthService;

    public void setComboPooledDataSource(ComboPooledDataSource comboPooledDataSource) {
        this.comboPooledDataSource = comboPooledDataSource;
    }

    public void setModifyAuthService(ModifyAuthService modifyAuthService) {
        this.modifyAuthService = modifyAuthService;
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        WebApplicationContext webApplicationContext= WebApplicationContextUtils.getWebApplicationContext(this.getServletContext());
        comboPooledDataSource=webApplicationContext.getBean("dataSource",ComboPooledDataSource.class);
        modifyAuthService=webApplicationContext.getBean("modifyAuthService",ModifyAuthService.class);
        HttpSession session=request.getSession();

        String Eusername=request.getParameter("Eusername");
        String EauthId=request.getParameter("Eauth");
        //System.out.println(Eusername+"..."+EauthId);

        try(Connection connection=comboPooledDataSource.getConnection()){
            String modifyWarn=modifyAuthService.modifyAuth(connection,EauthId,Eusername);
            request.setAttribute("queryWarn",modifyWarn);
            request.getRequestDispatcher("/userConfig/setAuth.jsp").forward(request,response);
        }
        catch (Exception e){
            throw new RuntimeException("修改权限Servlet出错！");
        }
    }
}
