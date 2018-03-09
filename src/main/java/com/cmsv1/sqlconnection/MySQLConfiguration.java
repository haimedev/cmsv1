/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cmsv1.sqlconnection;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author UpuanNgHARI
 */
public class MySQLConfiguration
{
    public Connection myConn;
    public CallableStatement myStmt;
    public MySQLConfiguration()
    {
        try
          {
            Class.forName("com.mysql.jdbc.Driver");
            myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/cms-v1", "root", "Haimemysql1");
            //myStmt = myConn.prepareCall("{call "+sql+"}");
          }
        catch (Exception e)
          {
          }
        
    }
    
    public void closeConnections()
    {
        try
          {
            myConn.close();
            myStmt.close();
          }
        catch (SQLException ex)
          {
            Logger.getLogger(MySQLConfiguration.class.getName()).log(Level.SEVERE, null, ex);
          }
    }
}
