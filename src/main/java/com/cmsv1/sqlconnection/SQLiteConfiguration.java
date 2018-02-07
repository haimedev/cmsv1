/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cmsv1.sqlconnection;

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
public class SQLiteConfiguration
{
    public Connection myConn;
    public Statement myStmt;
    
    public SQLiteConfiguration()
    {
        try
          {
            Class.forName("org.sqlite.JDBC");
            myConn = DriverManager.getConnection("jdbc:sqlite:E:\\Java Web Projects\\CMSv1\\CMSv1\\target\\CMSv1-1.0-SNAPSHOT\\WEB-INF\\lib\\cmsv1.sqlite");
            myStmt = myConn.createStatement();
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
            Logger.getLogger(SQLiteConfiguration.class.getName()).log(Level.SEVERE, null, ex);
          }
    }
}
