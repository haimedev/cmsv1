package com.cmsv1.bean;

import com.cmsv1.sqlconnection.SQLiteConfiguration;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LoginServiceBeanImpl implements LoginServiceBean
{
    public boolean isUserValid(String userName, String passWord) throws SQLException
    {
        SQLiteConfiguration _sqliteConnection = new SQLiteConfiguration();
        //List<AdminData> lst = new ArrayList<AdminData>();
        Boolean isValid = false;
        ResultSet rs = null;
        try
          {
            rs = _sqliteConnection.myStmt.executeQuery("select *from admins where ad_user='"+ userName +"' and ad_pass='"+ passWord +"';");
            
            while(rs.next())
              {
                isValid = true;
              }
          }
        catch (Exception ex)
          {
            Logger.getLogger(LoginServiceBeanImpl.class.getName()).log(Level.SEVERE, null, ex);
          }
        rs.close();
        _sqliteConnection.closeConnections();
        return isValid;
    }
    
    public String retrieveUserFullName(String userName, String passWord) throws SQLException
    {
        SQLiteConfiguration _sqliteConnection = new SQLiteConfiguration();
        String userFullName = null;
        ResultSet rs = null;
        try
          {
            rs = _sqliteConnection.myStmt.executeQuery("select ad_name from admins where ad_user='"+ userName +"' and ad_pass='"+ passWord +"';");
            
            while(rs.next())
              {
                userFullName = rs.getString("ad_name");
              }
          }
        catch (Exception ex)
          {
            Logger.getLogger(LoginServiceBeanImpl.class.getName()).log(Level.SEVERE, null, ex);
          }
        rs.close();
        _sqliteConnection.closeConnections();
        return userFullName;
    }
}
