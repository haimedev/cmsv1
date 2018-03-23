/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cmsv1.bean;

import com.cmsv1.bean.properties.RPTCustomerRecordsProp;
import com.cmsv1.bean.properties.properties;
import com.cmsv1.sqlconnection.MySQLConfiguration;
import com.cmsv1.sqlconnection.SQLiteConfiguration;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.apache.commons.lang3.text.WordUtils;

public class ReportsServiceBeanImpl implements ReportsServiceBean 
{
    properties prop = new properties();
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
            _MySQL.myStmt = _MySQL.myConn.prepareCall("{call read_default_page_report_element(?)}");
            _MySQL.myStmt.setString(1, pageId);
            rs = _MySQL.myStmt.executeQuery();
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
            returnHTML = "<label>" + label + ": </label><input id=\"" + labelId+"_"+ext + "\" "
                    + "name=\"" + labelId+"_"+ext + "\" class=\"calendar\" type=\"date\"/>";
        }
        
        else if(type.equals("textbox"))
        {
            returnHTML += "<label>" + label + ": </label><input id=\""+ labelId+"_"+ext +"\" "
                    + "name=\"" + labelId+"_"+ext + "\" type=\"text\"/>";
        }
        
        else if(type.equals("dropdownlist"))
        {
            returnHTML += "<input class=\"" + labelId + "_cls\" list=\"type_lst\" name=\"type_txt\" "
                    + "id=\"type_txt\" required style=\"width: 100%;\" autocomplete=\"off\">";
        }
        
        else if(type.equals("checkbox"))
        {
            returnHTML += "<input type=\"checkbox\" id=\"" + labelId+"_"+ext + "\" "
                    + "name=\"" + labelId+"_"+ext + "\" value=\"checked\">" + label;
        }
        
        
        return returnHTML;
    }
    
    public String readElementList()
    {
        return "";
    }
    
    
    //read what elements are there in specific report labels. Then pass those elements into MAP
    public List<String> readReportElement(String reportLabelId)
    {
        MySQLConfiguration _MySQL = new MySQLConfiguration();
        List<String> reportElement = new ArrayList<>();
        ResultSet rs = null;
        try
        {
            _MySQL.myStmt = _MySQL.myConn.prepareCall("{call read_report_element(?)}");
            _MySQL.myStmt.setString(1, reportLabelId);
            rs = _MySQL.myStmt.executeQuery();
            while(rs.next())
            {
                reportElement.add(rs.getString("element"));
            }
            
        rs.close();
        _MySQL.closeConnections();
        }
        catch (Exception e)
        {
            
        }
        return reportElement;
    }
    
    public List<RPTCustomerRecordsProp> readRPTCustomerRecords(Map<String, Object> map)
    {
        
        List<RPTCustomerRecordsProp> propList = new ArrayList<>();
        MySQLConfiguration _MySQL = new MySQLConfiguration();
        ResultSet rs = null;
        try
        {
            _MySQL.myStmt = _MySQL.myConn.prepareCall("{call read_rpt_0001(?,?)}");
            _MySQL.myStmt.setString(1, map.get("from").toString());
            _MySQL.myStmt.setString(2, map.get("to").toString());
            rs = _MySQL.myStmt.executeQuery();
            while(rs.next())
            {
                RPTCustomerRecordsProp prop = new RPTCustomerRecordsProp();
                prop.setFldTransacId(rs.getString("scft_id"));
                prop.setFldCustomerName(rs.getString("sc_fullname"));
                prop.setFldFreeTime(rs.getString("scft_free_time"));
                prop.setFldDate(rs.getString("scft_date"));
                prop.setFldActive(rs.getString("scft_active"));
                prop.setFldComment(rs.getString("scft_comment"));
                prop.setFldCreateBy(rs.getString("scft_create_by"));
                prop.setFldUpdateBy(rs.getString("scft_update_by"));
                propList.add(prop);
            }
            rs.close();
            _MySQL.closeConnections();
        }
        catch (Exception e)
        {
            
        }
        return propList;
    }
    
    public void generateRPT(List rptProp, Map<String, Object> map)
    {
        try
        {
            String myDate = "";
            String userHomeDirectory = System.getProperty("user.home");
            //SimpleDateFormat df1 = new SimpleDateFormat("yyyy-MM-dd - h:mma (EEEE)");//dd/MM/yyyy
            SimpleDateFormat df1 = new SimpleDateFormat("MM-dd-yyyy");//dd/MM/yyyy
            Date now = new Date();
            myDate = df1.format(now);
            /* Output file location */
            //String outputFile = prop.ReportOutput + File.separatorChar + "JasperTableExample.pdf";
            String outputFile = prop.ReportOutput + map.get("rptId").toString() + "\\" + map.get("rptId").toString() + "_" + myDate + ".pdf";
            System.out.println(outputFile);
            JRBeanCollectionDataSource itemsJRBean = new JRBeanCollectionDataSource(rptProp);
            /* Map to hold Jasper report Parameters */
    //        parameters.put("ds1", itemsJRBean);
    //        parameters.put("info", "haimeInfomoTo");
            Map<String, Object> parameters = new HashMap<>();
            /* Using compiled version(.jasper) of Jasper report to generate PDF */
            //JasperPrint jasperPrint = JasperFillManager.fillReport("resources/newReport.jasper", parameters, new JREmptyDataSource());
            System.out.println(prop.JRXMLPath + map.get("rptId").toString() + ".jasper");
            JasperPrint jasperPrint = JasperFillManager.fillReport(prop.JRXMLPath + map.get("rptId").toString() + ".jasper", map, itemsJRBean);

            /* outputStream to create PDF */
            OutputStream outputStream = new FileOutputStream(new File(outputFile));

            /* Write content to PDF file */
            JasperExportManager.exportReportToPdfStream(jasperPrint, outputStream);

            System.out.println("File Generated");
            }
            catch (Exception e)
            {
            }
        
    }
}
