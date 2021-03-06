package main.Tiezi.generalKanTie;



import main.domain.page.Page;
import main.domain.page.ReplyPage;
import main.domain.tiezi.ReplyTiezi;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Mayijun on 2016/9/12.
 */
public class GeneralKanTieDAO {
    //查询每个用户的权限等级
    private String authSql="select * from user where username=?";

    public int getAuthId(Connection connection,String username) throws SQLException {
        ResultSet resultSet=null;
        try(PreparedStatement preparedStatement=connection.prepareStatement(authSql)){
            preparedStatement.setString(1,username);
            resultSet=preparedStatement.executeQuery();
            if(resultSet.next()) {
                return resultSet.getInt("authid");
            }
            else{
                return 0;
            }
        }
        catch (Exception e){
            throw new RuntimeException("查看回复查询用户权限DAO失败!");
        }
        finally {
            if(resultSet!=null){
                resultSet.close();
            }
        }
    }
    //查看1楼
    private String findSql="select * from general_tiezi where tiezi_id=?";

    public String[] findTiezi(Connection connection,int tieziId) throws SQLException {
        ResultSet resultSet=null;
        try(PreparedStatement preparedStatement=connection.prepareStatement(findSql)){
            preparedStatement.setInt(1,tieziId);
            resultSet=preparedStatement.executeQuery();
            String[] tiezi=new String[5];
            int authId=0;
            if(resultSet.next()){
                if(resultSet.getString("username").length()<=6) {
                    authId = getAuthId(connection, resultSet.getString("username"));
                }
                else{
                    authId=0;
                }
                tiezi[0]=resultSet.getString("username");
                tiezi[1]=resultSet.getString("tiezi_title");
                tiezi[2]=resultSet.getString("tiezi_content");
                tiezi[3]=resultSet.getString("tiezi_time");
                tiezi[4]=authId+"";
            }
            return tiezi;
        }
        catch (Exception e){
            throw new RuntimeException("综合讨论区看帖DAO错误！");
        }
        finally {
            if(resultSet!=null){
                resultSet.close();
            }
        }
    }


   /* public Map<Integer,String[]> findReply(Connection connection, String tieziTime)throws SQLException{
        char[] timeArray=tieziTime.toCharArray();
        StringBuffer tablename=new StringBuffer();
        tablename.append("tiezi_");
        for(int i=0;i<timeArray.length;i++){
            if(timeArray[i]=='-'||timeArray[i]==' '||timeArray[i]==':'){
                continue;
            }
            else {
                tablename.append(timeArray[i]);
            }
        }
        String replySql="select * from "+tablename.toString();
        ResultSet resultSet=null;
        try(PreparedStatement preparedStatement=connection.prepareStatement(replySql)){
            resultSet=preparedStatement.executeQuery();
            Map<Integer,String[]> replyMap=new HashMap<>();
            while(resultSet.next()){
                replyMap.put(resultSet.getInt("tiezi_floor"),new String[]{resultSet.getString("reply_username"),
                resultSet.getString("tiezi_reply"),resultSet.getString("reply_time")});
            }
            return replyMap;
        }
        catch (Exception e){
            throw new RuntimeException("综合区看回复DAO出错！");
        }
        finally {
            if(resultSet!=null){
                resultSet.close();
            }
        }
    }*/
    //查找回复总数
    public int getTotalRecord(Connection connection,String tieziTime) throws SQLException {
        char[] timeArray=tieziTime.toCharArray();
        StringBuffer tablename=new StringBuffer();
        tablename.append("tiezi_");
        for(int i=0;i<timeArray.length;i++){
            if(timeArray[i]=='-'||timeArray[i]==' '||timeArray[i]==':'){
                continue;
            }
            else {
                tablename.append(timeArray[i]);
            }
        }
        String totalSql="select count(*) from "+tablename.toString();

        ResultSet resultSet=null;
        try(PreparedStatement preparedStatement=connection.prepareStatement(totalSql)){
            resultSet=preparedStatement.executeQuery();
            int totalRecord=0;
            if(resultSet.next()){
                totalRecord=resultSet.getInt("count(*)");
            }
            return totalRecord;

        }
        catch(Exception e){
            /*e.printStackTrace();
            return 0;*/
            throw new RuntimeException("查找回复总数DAO出错!");
        }
        finally {
            if(resultSet!=null){
                resultSet.close();
            }
        }
    }

    //查找回复内容
    public List<ReplyTiezi> getReply(Connection connection, ReplyPage replyPage,String tieziTime) throws SQLException {
        char[] timeArray=tieziTime.toCharArray();
        StringBuffer tablename=new StringBuffer();
        tablename.append("tiezi_");
        for(int i=0;i<timeArray.length;i++){
            if(timeArray[i]=='-'||timeArray[i]==' '||timeArray[i]==':'){
                continue;
            }
            else {
                tablename.append(timeArray[i]);
            }
        }
        String replySql="select * from "+tablename.toString()+" limit "+(replyPage.getCurrentPage()-1)*replyPage.getPageSize()
                +","+replyPage.getPageSize();

        ResultSet resultSet=null;
        List<ReplyTiezi> reply=new ArrayList<>();
        try(PreparedStatement preparedStatement=connection.prepareStatement(replySql)){
            resultSet=preparedStatement.executeQuery();
            int authId=0;
            while(resultSet.next()){
                if(resultSet.getString("reply_username").length()<=6) {
                    authId = getAuthId(connection, resultSet.getString("reply_username"));
                }
                else{
                    authId=0;
                }
                reply.add(new ReplyTiezi(resultSet.getString("reply_username"),resultSet.getInt("tiezi_id"),
                        resultSet.getString("tiezi_reply"),resultSet.getString("reply_time"),resultSet.getInt("tiezi_floor"),authId));
            }
            return reply;
        }
        catch (Exception e){
          /* e.printStackTrace();
            return null;*/
            throw new RuntimeException("获取每页回复内容DAO出错！");
        }
        finally {
            if(resultSet!=null){
                resultSet.close();
            }
        }
    }
}
