/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cmsv1.bean;
import com.cmsv1.bean.properties.SysCustomerProp;
import com.cmsv1.sqlconnection.SQLiteConfiguration;
import com.cmsv1.sqlconnection.MySQLConfiguration;
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
import org.springframework.util.StringUtils;
import org.apache.commons.lang3.text.WordUtils;

public class TimelineServiceBeanImpl implements TimelineServiceBean
{
    
    public List<TimeBalanceProp> readTimeBalance(String timeLineType)
    {
        MySQLConfiguration _MySQL = new MySQLConfiguration();
        List<TimeBalanceProp> propList = new ArrayList<>();
        String query = "";
        ResultSet rs = null;
        try
        {
            _MySQL.myStmt = _MySQL.myConn.prepareCall("{call read_sys_customer_free_time(?)}");
            _MySQL.myStmt.setString(1, timeLineType);
            rs = _MySQL.myStmt.executeQuery();
            while(rs.next())
            {
                TimeBalanceProp tb = new TimeBalanceProp();
                tb.setTb_id(rs.getString("scft_id"));
                tb.setTb_customername(rs.getString("sc_nickname"));
                tb.setTb_time(rs.getString("scft_free_time"));
                tb.setTb_date(ConverTime(rs.getString("scft_date"), "yyyy-MM-dd - h:mma (EEEE)", "MMMMM d, yyyy - h:mma (EEEE)"));
                System.out.println("haime::" + ConverTime(rs.getString("scft_date"), "yyyy-MM-dd - h:mma (EEEE)", "MMMMM d, yyyy - h:mma (EEEE)"));
                tb.setTb_type(rs.getString("scft_active"));
                tb.setTb_comments(rs.getString("scft_comment"));
                tb.setTb_createBy(rs.getString("scft_create_by"));
                tb.setTb_updateBy(rs.getString("scft_update_by"));
                propList.add(tb);
            }
            rs.close();
            _MySQL.closeConnections();
        }
        catch (Exception e)
        {
            
        }
        return propList;
    }
    
    public void createTimeBalance(String adminId, String customerId, String timeHour, String timeMinute, String comment)
    {
        MySQLConfiguration _MySQL = new MySQLConfiguration();
        try
        {
            //SimpleDateFormat dateNow = new SimpleDateFormat("MM/dd/yyyy HH:mm");
            String time = "";
            //SimpleDateFormat sdfDate = new SimpleDateFormat("MMMMM d, yyyy - h:mma (EEEE)");//dd/MM/yyyy
            SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd - h:mma (EEEE)");//dd/MM/yyyy
            Date now = new Date();
            String dateNow = sdfDate.format(now);
            System.out.println("time:: " + dateNow + "\n:: " + now);
            comment = (comment.equals("") || comment == null ? "No Comment" : comment);
            if(timeMinute.equals("0"))
            {
                time = timeHour + "hour";
                System.out.println("haime:: going true1");
            }
            
            else if(timeHour.equals("0"))
            {
                time = timeMinute + "minute";
                System.out.println("haime:: going true2");
            }
            
            else
            {
                System.out.println("haime:: going true3");
                time = timeHour + "hour " + timeMinute + "minute";
                System.out.println(time);
            }
            System.out.println(time);
            _MySQL.myStmt = _MySQL.myConn.prepareCall("{call create_sys_customer_free_time(?,?,?,?,?)}");
            _MySQL.myStmt.setString(1, customerId);
            _MySQL.myStmt.setString(2, time);
            _MySQL.myStmt.setString(3, dateNow);
            _MySQL.myStmt.setString(4, comment);
            _MySQL.myStmt.setString(5, adminId);
            _MySQL.myStmt.execute();
            //_sql.myStmt.executeQuery("insert into time_balance(tb_customername, tb_time, tb_date, tb_comments, tb_createby) values ('" + custName + "','" + time + "','" + dateNow + "','" + (comments.equals("") || comments == null ? "No Comment" : comments) + "','" + adminFullName + "');");
            //_sql.closeConnections();
            _MySQL.closeConnections();
        }
        catch (Exception e)
        {
            e.getStackTrace();
        }
    }
    
    public void updateFreeTime(String adminId, String transacId)
    {
        MySQLConfiguration _MySQL = new MySQLConfiguration();
        try
        {
            _MySQL.myStmt = _MySQL.myConn.prepareCall("{call update_sys_customer_free_time(?,?)}");
            _MySQL.myStmt.setString(1, adminId);
            _MySQL.myStmt.setString(2, transacId);
            _MySQL.myStmt.execute();
            _MySQL.closeConnections();
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
            _MySQL.closeConnections();
        }
        catch (Exception e)
        {
        }
        return customer;
    }
    
    public String ConverTime(String setDate, String setFormat, String getFormat)
    {
        //String date1 = "2018-01-22 - 2:30PM (Thursday)"; //yyyy-MM-dd - h:mma (EEEE)
        DateFormat df1 = new SimpleDateFormat(setFormat);
        DateFormat df2 = new SimpleDateFormat(getFormat);
        String getDate = "";
        try
        {
            Date tempDate = df1.parse(setDate);
            getDate = df2.format(tempDate);
        }
        catch (Exception e)
        {
            
        }
        System.out.println(getDate);
        return getDate;
    }
}
