package com.cmsv1.bean;

import com.cmsv1.sqlconnection.SQLiteConfiguration;
import com.cmsv1.sqlconnection.MySQLConfiguration;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LoginServiceBeanImpl implements LoginServiceBean
{
    public boolean isUserValid(String userName, String passWord) throws SQLException
    {
        //SQLiteConfiguration _sqliteConnection = new SQLiteConfiguration();
        MySQLConfiguration _mysql = new MySQLConfiguration();
        //List<AdminData> lst = new ArrayList<AdminData>();
        Boolean isValid = false;
        ResultSet rs = null;
        try
          {
            //rs = _sqliteConnection.myStmt.executeQuery("select *from admins where ad_user='"+ userName +"' and ad_pass='"+ passWord +"';");
            _mysql.myStmt = _mysql.myConn.prepareCall("{call read_admin(?,?)}");
            _mysql.myStmt.setString(1, userName);
            _mysql.myStmt.setString(2, passWord);
            rs = _mysql.myStmt.executeQuery();
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
        _mysql.closeConnections();
        return isValid;
    }
    
    public String retrieveUserFullName(String userName, String passWord) throws SQLException
    {
        SQLiteConfiguration _sqliteConnection = new SQLiteConfiguration();
        MySQLConfiguration _mysql = new MySQLConfiguration();
        String userFullName = null;
        ResultSet rs = null;
        try
        { 
            _mysql.myStmt = _mysql.myConn.prepareCall("{call read_admin_fullname(?,?)}");
            _mysql.myStmt.setString(1, userName);
            _mysql.myStmt.setString(2, passWord);
            rs = _mysql.myStmt.executeQuery();
                //rs = _sqliteConnection.myStmt.executeQuery("select ad_name from admins where ad_user='"+ userName +"' and ad_pass='"+ passWord +"';");
            
            while(rs.next())
            {
                userFullName = rs.getString("adm_fullname");
            }
        }
        catch (Exception ex)
          {
            Logger.getLogger(LoginServiceBeanImpl.class.getName()).log(Level.SEVERE, null, ex);
          }
        rs.close();
        _mysql.closeConnections();
        //_sqliteConnection.closeConnections();
        return userFullName;
    }
}
