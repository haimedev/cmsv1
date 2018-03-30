/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cmsv1.bean;

import com.cmsv1.bean.properties.RPT0001Prop;
import com.cmsv1.bean.properties.ReportElementProp;
import com.cmsv1.bean.properties.SysCustomerProp;
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
import java.util.Arrays;
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
    
//    public String createDefaultReportElements(String pageId)
//    {
//        SQLiteConfiguration _sql = new SQLiteConfiguration();
//        MySQLConfiguration _MySQL = new MySQLConfiguration();
//        String elementHTML = "";
//        String reportLabelId = "";
//        ResultSet rs = null;
//        try
//        {
//            _MySQL.myStmt = _MySQL.myConn.prepareCall("{call read_default_page_report_element(?)}");
//            _MySQL.myStmt.setString(1, pageId);
//            rs = _MySQL.myStmt.executeQuery();
//            while(rs.next())
//            {
//                elementHTML = elementHTML + createElement(rs.getString("pre_id"), rs.getString("pre_label"), 
//                        rs.getString("pre_element"), rs.getString("pre_ext"));
//            }
//            System.out.println(elementHTML);
//            rs.close();
//            _sql.closeConnections();
//        } 
//        catch (Exception e)
//        {
//            e.printStackTrace();
//        }
//        return elementHTML;
//    }
    
    public String createReportElements(String reportLabelId)
    {
        if(reportLabelId.equals("default"))
        {
            
        }
        SQLiteConfiguration _sql = new SQLiteConfiguration();
        MySQLConfiguration _MySQL = new MySQLConfiguration();
        String elementHTML = "";
        ResultSet rs = null;
        List<ReportElementProp> propList = new ArrayList<>();
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
                ReportElementProp prop = new ReportElementProp();
                prop.setPreId(rs.getString("pre_id"));
                prop.setPreLabel(rs.getString("pre_label"));
                prop.setPreElement(rs.getString("pre_element"));
                prop.setPreExt(rs.getString("pre_ext"));
                prop.setPreData(rs.getString("pred_data"));
                propList.add(prop);
//                elementHTML = elementHTML + createElement(rs.getString("pre_id"), rs.getString("pre_label"), 
//                        rs.getString("pre_element"), rs.getString("pre_ext"));
            }
            elementHTML = elementHTML + createElement(propList);
            rs.close();
            _sql.closeConnections();
        } 
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return elementHTML;
    }
    
    public String createElement(List<ReportElementProp> propList)
    {
        String tempId[] = new String[100];
        Integer tempIdIndex = 0;
        String returnHTML = "";
        for(ReportElementProp list : propList)
        {
            //If list ID is not already processed
            if(!Arrays.asList(tempId).contains(list.getPreId()))
            {
                String labelId = list.getPreLabel();
                String label = WordUtils.capitalizeFully(list.getPreLabel());
                String type = list.getPreElement();
                String ext = list.getPreExt();
                if(!type.equals("calendar"))
                {
                    returnHTML += "<br/><br/>";
                }

                if(type.equals("calendar"))
                {
                    returnHTML += "<label>" + label + ": </label><input id=\"" + labelId+"_"+ext + "\" "
                            + "name=\"" + labelId+"_"+ext + "\" class=\"calendar\" type=\"date\"/>";
                }

                else if(type.equals("textbox"))
                {
                    returnHTML += "<label>" + label + ": </label><input id=\""+ labelId+"_"+ext +"\" "
                            + "name=\"" + labelId+"_"+ext + "\" type=\"text\"/>";
                }

                else if(type.equals("dropdownlist"))
                { 
                    returnHTML += "<label>" + label + ": </label><input class=\"drownDown_cls\" list=\"" + labelId + "_lst\" id=\""+ labelId+"_"+ext +"\" "
                            + "name=\"" + labelId+"_"+ext + "\" required>";
                    returnHTML += "<datalist id=\"" + labelId + "_lst\">";
                    for(ReportElementProp lst : propList)
                    {
                        if(lst.getPreId().equals(list.getPreId()))
                        {
                            returnHTML += "<option value=\"" + lst.getPreData() + "\">";
                        }
                    }
                    returnHTML += "</datalist>";
                    tempId[tempIdIndex] = list.getPreId();
                    tempIdIndex++;
                    
                }

                else if(type.equals("checkbox"))
                {
                    returnHTML += "<input type=\"checkbox\" id=\"" + labelId+"_"+ext + "\" "
                            + "name=\"" + labelId+"_"+ext + "\" value=\"checked\">" + label;
                }
                
                else if(type.equals("syscustomernames"))
                {
                    List<SysCustomerProp> prop = new ArrayList<>();
                    prop = readCustomers();
                    returnHTML += "<label class=\"customerName_cls\">" + label + ": </label>"
                            + "<input class=\"drownDown_cls customerName_cls\" list=\"" + labelId + "_lst\" id=\""+ labelId+"_"+ext +"\" "
                            + "name=\"" + labelId+"_"+ext + "\">";
                    returnHTML += "<datalist id=\"" + labelId + "_lst\">";
                    for(SysCustomerProp lst : prop)
                    {
                        returnHTML += "<option value=\"" + lst.getCustomerId() + ": " + lst.getCustomerName()+ "\">";
                        
                    }
                    returnHTML += "</datalist>";
                }
            }
            
            else
            {
                
            }
        }
        return returnHTML;
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
    
    public List<RPT0001Prop> readRPT0001(Map<String, Object> map)
    {
        
        List<RPT0001Prop> propList = new ArrayList<>();
        MySQLConfiguration _MySQL = new MySQLConfiguration();
        ResultSet rs = null;
        try
        {
            _MySQL.myStmt = _MySQL.myConn.prepareCall("{call read_rpt_0001(?,?,?,?)}");
            _MySQL.myStmt.setString(1, map.get("from").toString());
            _MySQL.myStmt.setString(2, map.get("to").toString());
            _MySQL.myStmt.setString(3, map.get("type").toString());
            _MySQL.myStmt.setString(4, map.get("customerId").toString());
            System.out.println("map:: " + map.get("type").toString());
            System.out.println("map:: " + map.get("customerId").toString());
            rs = _MySQL.myStmt.executeQuery();
            while(rs.next())
            {
                RPT0001Prop prop = new RPT0001Prop();
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
    
    public Map<String, Object> generateRPT(List rptProp, Map<String, Object> map)
    {
        Map<String, Object> resultMap = new HashMap<>();
        try
        {
            String myDate = "";
            SimpleDateFormat df1 = new SimpleDateFormat("MM-dd-yyyy h-mma");
            Date now = new Date();
            myDate = df1.format(now);
            String outputFile = prop.ReportOutput + map.get("rptId").toString() + "\\" + map.get("rptId").toString() + "_" + myDate + ".pdf";
            JRBeanCollectionDataSource itemsJRBean = new JRBeanCollectionDataSource(rptProp);
            JasperPrint jasperPrint = JasperFillManager.fillReport(prop.JRXMLPath + map.get("rptId").toString() + ".jasper", map, itemsJRBean);
            OutputStream outputStream = new FileOutputStream(new File(outputFile));
            JasperExportManager.exportReportToPdfStream(jasperPrint, outputStream);
            System.out.println("File Generated");
            resultMap.put("pdfFile", outputFile);
            resultMap.put("pdfName", map.get("rptId").toString() + "_" + myDate + ".pdf");
            resultMap.put("jasperPrint", jasperPrint);
            }
            catch (Exception e)
            {
            }
        return resultMap;
    }
}
