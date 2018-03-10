/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cmsv1.bean;
import com.cmsv1.sqlconnection.MySQLConfiguration;
import com.cmsv1.sqlconnection.SQLiteConfiguration;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.commons.lang3.text.WordUtils;

public class AmountBalanceServiceBeanImpl implements AmountBalanceServiceBean
{
    SQLiteConfiguration _sql = new SQLiteConfiguration();
    
    public List<AmountBalanceProp> getAmountBalance(String type)
    {
        List<AmountBalanceProp> propList = new ArrayList<>();
        MySQLConfiguration _mySQL = new MySQLConfiguration();
        String query = "";
        ResultSet rs = null;
        try
        {
//            if(timeLineType.equals("all"))
//            {
//                query = "select ab_id, ab_customername, ab_amount, ab_date, replace(replace(ab_active,'0','Paid'),'1','Unpaid') as ab_active, ab_comments, ab_createby, ab_updateby from amount_balance order by ab_id desc;";
//            }
//            
//            else if(timeLineType.equals("unpaid"))
//            {
//                query = "select ab_id, ab_customername, ab_amount, ab_date, replace(ab_active,'1','Unpaid') as ab_active, ab_comments, ab_createby, ab_updateby from amount_balance where ab_active='1' order by ab_id desc;";
//            }
//            
//            else if(timeLineType.equals("paid"))
//            {
//                query = "select ab_id, ab_customername, ab_amount, ab_date, replace(ab_active,'0','Paid') as ab_active, ab_comments, ab_createby, ab_updateby from amount_balance where ab_active='0' order by ab_id desc;";
//            }
            _mySQL.myStmt = _mySQL.myConn.prepareCall("{call r_amount_balance(?)}");
            _mySQL.myStmt.setString(1, type);
            rs = _mySQL.myStmt.executeQuery();
            //rs = _sql.myStmt.executeQuery(query);
            while(rs.next())
            {
                AmountBalanceProp ab = new AmountBalanceProp();
                ab.setAb_id(rs.getString("ab_id"));
                ab.setAb_customername(rs.getString("ab_customername"));
                ab.setAb_amount(rs.getString("ab_amount"));
                ab.setAb_date(rs.getString("ab_date"));
                ab.setAb_type(rs.getString("ab_active"));
                ab.setAb_comments(rs.getString("ab_comment"));
                ab.setAb_createBy(rs.getString("ab_createby"));
                ab.setAb_updateBy(rs.getString("ab_updateby"));
                propList.add(ab);
            }
            rs.close();
            _mySQL.closeConnections();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return propList;
    }
    
    public void createAmountBalance(String adminFullName, String custName, String amount, String comments)
    {
        try
        {
            MySQLConfiguration _mySQL = new MySQLConfiguration();
            SimpleDateFormat sdfDate = new SimpleDateFormat("MMMMM d, yyyy - h:mma (EEEE)");//dd/MM/yyyy
            Date now = new Date();
            custName = WordUtils.capitalizeFully(custName);
            String dateNow = sdfDate.format(now);
            comments = (comments.equals("") || comments == null ? "No Comment" : comments);
            _mySQL.myStmt = _mySQL.myConn.prepareCall("{call c_amount_balance(?,?,?,?,?)}");
            _mySQL.myStmt.setString(1, custName);
            _mySQL.myStmt.setString(2, amount);
            _mySQL.myStmt.setString(3, comments);
            _mySQL.myStmt.setString(4, dateNow);
            _mySQL.myStmt.setString(5, adminFullName);
            _mySQL.myStmt.execute();
            
            _mySQL.closeConnections();
            //_sql.myStmt.executeQuery("insert into amount_balance(ab_customername, ab_amount, ab_comments, ab_date, ab_createby) values ('" + custName + "','" + amount + "','" + (comments.equals("") || comments == null ? "No Comment" : comments) + "','" + dateNow + "','" + adminFullName + "');");
            //_sql.closeConnections();
        }
        catch (Exception e)
        {
            e.getStackTrace();
        }
    }
    
    public void updateAmountBalance(String adminFullName, String id)
    {
        try
        {
            MySQLConfiguration _mySQL = new MySQLConfiguration();
            _mySQL.myStmt = _mySQL.myConn.prepareCall("{call u_amount_balance(?,?)}");
            _mySQL.myStmt.setString(1, adminFullName);
            _mySQL.myStmt.setString(2, id);
            _mySQL.myStmt.execute();
            if(!id.equals(""))
            {
                _sql.myStmt.executeQuery("update amount_balance set ab_active='0', ab_updateby='" + adminFullName + "' where ab_id='" + id + "';");
                _sql.closeConnections();
            }
        }
        catch (Exception e)
        {
            e.getStackTrace();
        }
    }
    
    public List<String> readCustomers()
    {
        SQLiteConfiguration _sql = new SQLiteConfiguration();
        List<String> customerNames = new ArrayList<>();
        ResultSet rs = null;
        try
        {
            rs = _sql.myStmt.executeQuery("select ctm_nickname from customers where ctm_active='1' order by ctm_nickname asc;");
            
            while(rs.next())
            {
                customerNames.add(rs.getString("ctm_nickname"));
            }
            rs.close();
            _sql.closeConnections();
        }
        catch (Exception e)
        {
        }
        return customerNames;
    }
}
