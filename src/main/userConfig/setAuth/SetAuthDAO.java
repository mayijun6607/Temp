package main.userConfig.setAuth;

import main.domain.user.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mayijun on 2016/9/20.
 */
public class SetAuthDAO {
    //模糊查询
    //private String blurSql="select * from user where username like ? limit 0,10";

    public List<User> findBlurUser(Connection connection, String queryName,int userAuth) throws SQLException {
        ResultSet resultSet=null;
        List<User> userList=new ArrayList<>();
        //System.out.println(userList);
        String blurSql="select * from user where username like "+"'%"+queryName+"%'"+" limit 0,10";
        try(PreparedStatement preparedStatement=connection.prepareStatement(blurSql)){
            /*String username="'%"+queryName+"%'";
            preparedStatement.setString(1,username);*/
            resultSet=preparedStatement.executeQuery();
            while(resultSet.next()){
                if(resultSet.getInt("authid")<userAuth){
                    userList.add(new User(resultSet.getString("username"),resultSet.getInt("authid")));
                }
            }
            //System.out.println(userList.get(0));
            return userList;
        }
        catch (Exception e){
           /* e.printStackTrace();
            return null;*/
            throw new RuntimeException("设置权限模糊查询DAO出错！");
        }
        finally {
            if(resultSet!=null){
                resultSet.close();
            }
        }

    }
    //精确查询
    private String findSql="select * from user where username=?";

    public int findExactUser(Connection connection,String queryName,int userAuth) throws SQLException {
        ResultSet resultSet = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(findSql)) {
            preparedStatement.setString(1, queryName);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                if (resultSet.getInt("authid") < userAuth) {
                   return resultSet.getInt("authid");
                }
            }
            return 0;
        }
        catch (Exception e) {
            throw new RuntimeException("设置权限精确查询DAO出错！");
        }
        finally {
            if (resultSet != null) {
                resultSet.close();
            }
        }
    }
}

