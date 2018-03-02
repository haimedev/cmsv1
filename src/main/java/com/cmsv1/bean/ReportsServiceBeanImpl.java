/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cmsv1.bean;

import com.cmsv1.sqlconnection.SQLiteConfiguration;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang3.text.WordUtils;

public class ReportsServiceBeanImpl implements ReportsServiceBean 
{
    
    public List<ReportsProperties> readPageLabels(String reportId)
    {
        SQLiteConfiguration _sql = new SQLiteConfiguration();
        List<ReportsProperties> propList = new ArrayList<>();
        ResultSet rs = null;
        try
        {
            rs = _sql.myStmt.executeQuery("select pr_id, pr_report_label from page_reports where pr_page_id='" + reportId
               + "' and pr_active='1' order by pr_order asc;");

            while(rs.next())
            {
                ReportsProperties rp = new ReportsProperties();
                rp.setReportId(rs.getString("pr_id"));
                rp.setReportLabel(rs.getString("pr_report_label"));
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
    
    public String createReportElements(String reportLabelId)
    {
        SQLiteConfiguration _sql = new SQLiteConfiguration();
        String elementHTML = "";
        ResultSet rs = null;
        try
        {
            rs = _sql.myStmt.executeQuery("select pre_label, pre_element from page_report_elements where pre_report_label_id='"
                    + reportLabelId + "' and pre_active='1' order by pre_order asc;");
            
            while(rs.next())
            {
                elementHTML = elementHTML + createElement(rs.getString("pre_label"), rs.getString("pre_element"));
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
    
    private String createElement(String label, String type)
    {
        label = WordUtils.capitalizeFully(label);
        String returnHTML = "";
        if(!type.equals("calendar"))
        {
            returnHTML = "<br/><br/>";
        }
        
        if(type.equals("calendar"))
        {
            returnHTML = "<label>" + label + ": </label><input id=\"" + label + "_cal\" class=\"calendar\" type=\"date\"/>";
        }
        
        else if(type.equals("textbox"))
        {
            returnHTML += "<label>" + label + ": </label><input id=\""+ label + "_txt\" class=\"calendar\" type=\"text\"/>";
        }
        return returnHTML;
    }
}
