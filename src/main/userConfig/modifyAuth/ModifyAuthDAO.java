package main.userConfig.modifyAuth;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Mayijun on 2016/9/21.
 */
public class ModifyAuthDAO {
    //查询权限数量
    private String findSql="select count(*) from user where authid=?";
    public int authCount(Connection connection,int authId) throws SQLException {
        ResultSet resultSet=null;
        try(PreparedStatement preparedStatement=connection.prepareStatement(findSql)){
            preparedStatement.setInt(1,authId);
            resultSet=preparedStatement.executeQuery();
            if(resultSet.next()){
                return resultSet.getInt("count(*)");
            }
            //先放着，没查到就不让修改会员以上权限
            return 999;
        }
        catch (Exception e){
            throw new RuntimeException("修改权限查询数量DAO出错！");
        }
        finally {
            if(resultSet!=null){
                resultSet.close();
            }
        }
    }
    //修改权限
    private String modifySql="update user set authid=? where username=?";

    //1成功，-1人数超出,-2未知错误
    public int modifyAuth(Connection connection,String EmodifyAuth,String Eusername) throws SQLException {
        int authId=Integer.parseInt(EmodifyAuth);
        int authCount=authCount(connection,authId);
        try(PreparedStatement preparedStatement=connection.prepareStatement(modifySql)){
            preparedStatement.setInt(1,authId);
            preparedStatement.setString(2,Eusername);
            if(authId==1){
                preparedStatement.execute();
                return 1;
            }
            else if(authId==2){
                if(authCount>=2){
                    return -1;
                }
                else {
                    preparedStatement.execute();
                    return 1;
                }
            }
            else if(authId==3){
                if(authCount>=1){
                    return -1;
                }
                else {
                    preparedStatement.execute();
                    return 1;
                }
            }
            return -2;
        }
        catch (Exception e){
            throw new RuntimeException("修改权限DAO出错！");
        }
    }
}
