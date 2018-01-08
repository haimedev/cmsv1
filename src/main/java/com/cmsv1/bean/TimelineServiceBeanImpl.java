/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cmsv1.bean;
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

public class TimelineServiceBeanImpl implements TimelineServiceBean
{
    SQLiteConfiguration _sql = new SQLiteConfiguration();
    public List<TimeBalanceProp> getTimeBalance()
    {
        List<TimeBalanceProp> propList = new ArrayList<TimeBalanceProp>();
        ResultSet rs = null;
        try
        {
            rs = _sql.myStmt.executeQuery("select * from time_balance where tb_active='1';");
            while(rs.next())
            {
                TimeBalanceProp tb = new TimeBalanceProp();
                tb.setTb_id(rs.getString("tb_id"));
                tb.setTb_time(rs.getString("tb_time"));
                tb.setTb_date(rs.getString("tb_date"));
                tb.setTb_customername(rs.getString("tb_customername"));
                propList.add(tb);
            }
            rs.close();
            _sql.closeConnections();
        }
        catch (Exception e)
        {
            
        }
        return propList;
    }
    
    public void createTimeBalance(String custName, String timeHour, String timeMinute)
    {
        try
        {
            //SimpleDateFormat dateNow = new SimpleDateFormat("MM/dd/yyyy HH:mm");
            String time = "";
            SimpleDateFormat sdfDate = new SimpleDateFormat("MMMMM d, yyyy - h:mma (EEEE)");//dd/MM/yyyy
            Date now = new Date();
            String dateNow = sdfDate.format(now);
            if(timeMinute.equals("0"))
            {
                time = timeHour + "h";
            }
            
            else if(timeHour.equals("0"))
            {
                time = timeMinute + "m";
            }
            
            else
            {
                time = timeHour + "h " + timeMinute + "m";
            }
            
            
            _sql.myStmt.executeQuery("insert into time_balance(tb_customername, tb_time, tb_date) values ('" + custName + "','" + time + "','" + dateNow + "');");
            _sql.closeConnections();
        }
        catch (Exception e)
        {
            e.getStackTrace();
        }
    }
    
    public void deleteTimeBalance(String id)
    {
        try
        {
            if(!id.equals(""))
            {
                _sql.myStmt.executeQuery("update time_balance set tb_active='0' where tb_id='" + id + "';");
                _sql.closeConnections();
            }
        }
        catch (Exception e)
        {
            e.getStackTrace();
        }
    }
}
