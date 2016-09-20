package main.myLogin.login;

import dao.Authority;
import main.domain.user.User;

import java.sql.*;

/**
 * Created by Mayijun on 2016/9/8.
 */
public class LoginDAO implements Authority{
    private String checkSql="select password from user where username=?";

    //返回值 1是成功，-1是用户名错误，-2是密码错误
    public int check(Connection connection, User user) throws SQLException {
        ResultSet resultSet=null;
        try(PreparedStatement preparedStatement=connection.prepareStatement(checkSql)){
            preparedStatement.setString(1,user.getUsername());
            resultSet=preparedStatement.executeQuery();
            if(resultSet.next()){
                if(resultSet.getString("password").equals(user.getPassword())){
                    return 1;
                }
                else {
                    return -2;
                }
            }
            return -1;
        }
        catch (Exception e){
            throw new RuntimeException("登录查询失败!");
        }
        finally {

            if(resultSet!=null){
                resultSet.close();
            }

        }
    }
    //获得权限等级 0游客，1会员，2斑竹，3管理员，4站长
    private String AuthSql="select * from user where username=?";
    @Override
    public int getAuthority(Connection connection, String username) throws SQLException {
        ResultSet resultSet=null;
        try(PreparedStatement preparedStatement=connection.prepareStatement(AuthSql)){
            preparedStatement.setString(1,username);
            resultSet=preparedStatement.executeQuery();
            if(resultSet.next()) {
                return resultSet.getInt("authid");
            }
            else{
                return -1;
            }
        }
        catch (Exception e){
           /* e.printStackTrace();
            return 0;*/
            throw new RuntimeException("登录查询权限DAO出错！");
        }
        finally {
            if(resultSet!=null){
                resultSet.close();
            }
        }

    }

}
