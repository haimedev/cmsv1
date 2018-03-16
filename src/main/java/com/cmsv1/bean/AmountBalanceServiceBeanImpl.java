/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cmsv1.bean;
import com.cmsv1.bean.properties.SysCustomerProp;
import com.cmsv1.sqlconnection.MySQLConfiguration;
import com.cmsv1.sqlconnection.SQLiteConfiguration;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
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
    
    public List<AmountBalanceProp> readMoneyBalance(String type)
    {
        List<AmountBalanceProp> propList = new ArrayList<>();
        MySQLConfiguration _mySQL = new MySQLConfiguration();
        String query = "";
        ResultSet rs = null;
        try
        {
            _mySQL.myStmt = _mySQL.myConn.prepareCall("{call read_sys_customer_money_balance(?)}");
            _mySQL.myStmt.setString(1, type);
            rs = _mySQL.myStmt.executeQuery();
            //rs = _sql.myStmt.executeQuery(query);
            while(rs.next())
            {
                AmountBalanceProp ab = new AmountBalanceProp();
                ab.setAb_id(rs.getString("scmb_id"));
                ab.setAb_customername(rs.getString("sc_nickname"));
                ab.setAb_amount(rs.getString("scmb_amount"));
                ab.setAb_date(rs.getString("scmb_date"));
                ab.setAb_type(rs.getString("scmb_active"));
                ab.setAb_comments(rs.getString("scmb_comment"));
                ab.setAb_createBy(rs.getString("scmb_create_by"));
                ab.setAb_updateBy(rs.getString("scmb_update_by"));
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
    
    public void createMoneyBalance(String adminId, String customerId, String amount, String comments)
    {
        try
        {
            MySQLConfiguration _mySQL = new MySQLConfiguration();
            SimpleDateFormat sdfDate = new SimpleDateFormat("MMMMM d, yyyy - h:mma (EEEE)");//dd/MM/yyyy
            Date now = new Date();
            customerId = WordUtils.capitalizeFully(customerId);
            String dateNow = sdfDate.format(now);
            comments = (comments.equals("") || comments == null ? "No Comment" : comments);
            _mySQL.myStmt = _mySQL.myConn.prepareCall("{call create_sys_customer_money_time(?,?,?,?,?)}");
            _mySQL.myStmt.setString(1, customerId);
            _mySQL.myStmt.setString(2, amount);
            _mySQL.myStmt.setString(3, dateNow);
            _mySQL.myStmt.setString(4, comments);
            _mySQL.myStmt.setString(5, adminId);
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
    
    public void updateMoneyBalance(String adminId, String transacId)
    {
        try
        {
            MySQLConfiguration _mySQL = new MySQLConfiguration();
            _mySQL.myStmt = _mySQL.myConn.prepareCall("{call update_sys_customer_money_balance(?,?)}");
            _mySQL.myStmt.setString(1, adminId);
            _mySQL.myStmt.setString(2, transacId);
            _mySQL.myStmt.execute();
//            if(!id.equals(""))
//            {
//                _sql.myStmt.executeQuery("update amount_balance set ab_active='0', ab_updateby='" + adminFullName + "' where ab_id='" + id + "';");
//                _sql.closeConnections();
//            }
        }
        catch (Exception e)
        {
            e.getStackTrace();
        }
    }
    
    public List<SysCustomerProp> readCustomers()
    {
        List<SysCustomerProp> customer = new ArrayList<>();
        try
        {
            MySQLConfiguration _MySQL = new MySQLConfiguration();
            ResultSet rs = null;
            _MySQL.myStmt = _MySQL.myConn.prepareCall("{call read_sys_customer()}");
            rs = _MySQL.myStmt.executeQuery();
            while(rs.next())
            {
                SysCustomerProp prop = new SysCustomerProp();
                prop.setCustomerId(rs.getString("sc_id"));
                prop.setCustomerName(rs.getString("sc_nickname"));
                customer.add(prop);
            }
            rs.close();
            _sql.closeConnections();
        }
        catch (Exception e)
        {
        }
        return customer;
    }
}
