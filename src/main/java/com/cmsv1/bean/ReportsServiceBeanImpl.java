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
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang3.text.WordUtils;

public class ReportsServiceBeanImpl implements ReportsServiceBean 
{
    
    public List<ReportsProperties> readPageLabels(String pageId)
    {
        SQLiteConfiguration _sql = new SQLiteConfiguration();
        MySQLConfiguration _MySQL = new MySQLConfiguration();
        List<ReportsProperties> propList = new ArrayList<>();
        ResultSet rs = null;
        try
        {
            _MySQL.myStmt = _MySQL.myConn.prepareCall("{call read_page_report_label(?)}");
            _MySQL.myStmt.setString(1, pageId);
            rs = _MySQL.myStmt.executeQuery();
//            rs = _sql.myStmt.executeQuery("select pr_id, pr_report_label from page_reports where pr_page_id='" + pageId
//               + "' and pr_active='1' order by pr_order asc;");

            while(rs.next())
            {
                ReportsProperties rp = new ReportsProperties();
                rp.setReportId(rs.getString("prl_id"));
                rp.setReportLabel(rs.getString("prl_label"));
                propList.add(rp);
            }

            rs.close();
            _sql.closeConnections();
        } 
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return propList;
    }
    
    public String createDefaultReportElements(String pageId)
    {
        SQLiteConfiguration _sql = new SQLiteConfiguration();
        MySQLConfiguration _MySQL = new MySQLConfiguration();
        String elementHTML = "";
        String reportLabelId = "";
        ResultSet rs = null;
        try
        {
            //get the default first report label
//            rs = _sql.myStmt.executeQuery("select pr_id from page_reports where pr_page_id='" + pageId
//               + "' and pr_order='1' and pr_active='1';");
//            while(rs.next())
//            {
//                reportLabelId = rs.getString("pr_id");
//            }
            _MySQL.myStmt = _MySQL.myConn.prepareCall("{call read_default_page_report_element(?)}");
            _MySQL.myStmt.setString(1, pageId);
            rs = _MySQL.myStmt.executeQuery();
//            rs = _sql.myStmt.executeQuery("select pre_id, pre_label, pre_element, pre_ext "
//                    + "from page_report_elements as a join page_reports as b on a.pre_report_label_id=b.pr_id "
//                    + "where pr_page_id='" + pageId + "' and pr_order='1' and pr_active='1' " 
//                    + "and pre_active='1' order by pre_order asc;");
            while(rs.next())
            {
                elementHTML = elementHTML + createElement(rs.getString("pre_id"), rs.getString("pre_label"), 
                        rs.getString("pre_element"), rs.getString("pre_ext"));
            }
            System.out.println(elementHTML);
            rs.close();
            _sql.closeConnections();
        } 
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return elementHTML;
    }
    
    public String createReportElements(String reportLabelId)
    {
        if(reportLabelId.equals("default"))
        {
            
        }
        SQLiteConfiguration _sql = new SQLiteConfiguration();
        MySQLConfiguration _MySQL = new MySQLConfiguration();
        String elementHTML = "";
        ResultSet rs = null;
        try
        {
            _MySQL.myStmt = _MySQL.myConn.prepareCall("{call read_page_report_element(?)}");
            _MySQL.myStmt.setString(1, reportLabelId);
            rs = _MySQL.myStmt.executeQuery();
//            rs = _sql.myStmt.executeQuery("select pre_id, pre_label, pre_element, pre_ext "
//                    + "from page_report_elements where pre_report_label_id='"
//                    + reportLabelId + "' and pre_active='1' order by pre_order asc;");
//            
            while(rs.next())
            {
                elementHTML = elementHTML + createElement(rs.getString("pre_id"), rs.getString("pre_label"), 
                        rs.getString("pre_element"), rs.getString("pre_ext"));
            }
            System.out.println(elementHTML);
            rs.close();
            _sql.closeConnections();
        } 
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return elementHTML;
    }
    
    private String createElement(String elementId, String label, String type, String ext)
    {
        String labelId = label;
        label = WordUtils.capitalizeFully(label);
        String returnHTML = "";
        if(!type.equals("calendar"))
        {
            returnHTML = "<br/><br/>";
        }
        
        if(type.equals("calendar"))
        {
            returnHTML = "<label>" + label + ": </label><input id=\"" + labelId+ext + "\" "
                    + "name=\"" + labelId+ext + "\" class=\"calendar\" type=\"date\"/>";
        }
        
        else if(type.equals("textbox"))
        {
            returnHTML += "<label>" + label + ": </label><input id=\""+ labelId+ext +"\" "
                    + "name=\"" + labelId+ext + "\" type=\"text\"/>";
        }
        
        else if(type.equals("dropdownlist"))
        {
            returnHTML += "<input class=\"" + labelId + "_cls\" list=\"type_lst\" name=\"type_txt\" "
                    + "id=\"type_txt\" required style=\"width: 100%;\" autocomplete=\"off\">";
        }
        
        else if(type.equals("checkbox"))
        {
            returnHTML += "<input type=\"checkbox\" id=\"" + labelId+ext + "\" "
                    + "name=\"" + labelId+ext + "\" value=\"checked\">" + label;
        }
        
        
        return returnHTML;
    }
    
    public String readElementList()
    {
        return "";
    }
}
