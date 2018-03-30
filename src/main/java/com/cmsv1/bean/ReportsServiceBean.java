/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cmsv1.bean;
import com.cmsv1.bean.properties.RPT0001Prop;
import com.cmsv1.bean.properties.ReportElementProp;
import com.cmsv1.bean.properties.SysCustomerProp;
import java.sql.Array;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public interface ReportsServiceBean 
{
    public List<ReportsProperties> readPageLabels(String pageId) throws SQLException;
    //public String createDefaultReportElements(String pageId);
    public String createReportElements(String reportLabelId);
    public String createElement(List<ReportElementProp> propList);
    public List<String> readReportElement(String reportLabelId);
    public List<RPT0001Prop> readRPT0001(Map<String, Object> map);
    public Map<String, Object> generateRPT(List rptProp, Map<String, Object> map);
    public List<SysCustomerProp> readCustomers();
}
