package main.userConfig.modifyAuth;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by Mayijun on 2016/9/21.
 */
public class ModifyAuthService {
    private ModifyAuthDAO modifyAuthDAO;

    public void setModifyAuthDAO(ModifyAuthDAO modifyAuthDAO) {
        this.modifyAuthDAO = modifyAuthDAO;
    }

    public String modifyAuth(Connection connection,String EmodifyAuth,String Eusername) throws SQLException {
        int modifyWarn=modifyAuthDAO.modifyAuth(connection,EmodifyAuth,Eusername);
        if(modifyWarn==1){
            return "修改成功！";
        }
        else if(modifyWarn==-1){
            return "人数已达到上限！";
        }
        else{
            return "未知错误！";
        }
    }
}
