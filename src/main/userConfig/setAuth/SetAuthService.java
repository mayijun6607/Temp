package main.userConfig.setAuth;

import main.domain.user.User;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by Mayijun on 2016/9/20.
 */
public class SetAuthService {
    private SetAuthDAO setAuthDAO;

    public void setSetAuthDAO(SetAuthDAO setAuthDAO) {
        this.setAuthDAO = setAuthDAO;
    }

    //模糊
    public List<User> getBlurUser(Connection connection,String queryName,int userAuth) throws SQLException {
        return setAuthDAO.findBlurUser(connection,queryName,userAuth);
    }
    //精确
    public int getExactUser(Connection connection,String queryName,int userAuth) throws SQLException{
        return setAuthDAO.findExactUser(connection,queryName,userAuth);
    }
    //拿到模糊的用户名
    public String[] getBlurUsername(List<User> userList){
        String[] Busername=new String[10];
        int i=0;
        for(User userTemp:userList){
            if(i<=10) {
                Busername[i]=userTemp.getUsername();
                //System.out.println(Busername[i]);
                i++;
            }
            else{
                return Busername;
            }
        }
        return Busername;
    }
    //拿到模糊的权限
    public int[] getBlurUserAuth(List<User> userList){
        int[] BuserAuth=new int[10];
        int i=0;
        for(User userTemp:userList){
            if(i<=10) {
                BuserAuth[i]=userTemp.getAuthId();
                //System.out.println(BuserAuth[i]+"...");
                i++;
            }
            else{
                return BuserAuth;
            }
        }
        return BuserAuth;
    }
}
