package main.myLogin.login;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by Mayijun on 2016/9/9.
 */
public class LoginOutServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session=request.getSession();
        session.setAttribute("username",null);
        session.setAttribute("userAuth",0);
        //拿取lastURL
        String lastURL=null;
        if((String)session.getAttribute("lastURL")!=null) {
            lastURL=(String) session.getAttribute("lastURL");
        }
        else{
            lastURL="/index.jsp";
        }
        response.sendRedirect(lastURL);
        //System.out.println((String)session.getAttribute("username"));
    }
}
