package dao;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by Mayijun on 2016/9/20.
 */
public interface Authority {
    public int getAuthority(Connection connection,String username) throws SQLException;
}
