package com.cmsv1.controller;

import com.cmsv1.bean.AmountBalanceProp;
import com.cmsv1.bean.AmountBalanceServiceBeanImpl;
import com.cmsv1.bean.properties.RPT0001Prop;
import com.cmsv1.bean.ReportsProperties;
import com.cmsv1.bean.ReportsServiceBeanImpl;
import com.cmsv1.bean.TimeBalanceProp;
import com.cmsv1.bean.TimelineServiceBeanImpl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import haimedevframework.StringUtil;
import java.awt.Desktop;
import java.io.FileInputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.PrintWriter;
import net.sf.jasperreports.engine.JasperExportManager;

@Controller
public class ReportController 
{
    @RequestMapping("ReportController")
    public void reportController(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    { 
        StringUtil _stringUtil = new StringUtil();
        String tempDate = _stringUtil.DateConverter("2018-03-09","yyyy-MM-dd","MMMMM d, yyyy");
        System.out.println("haime date::" + tempDate);
        try
        {
            HttpSession session = request.getSession();
            String pageId = session.getAttribute("pageId").toString();
            String adminFullName = session.getAttribute("adminFullName").toString();
            String reportElements = "";
            ReportsServiceBeanImpl _serviceBean = new ReportsServiceBeanImpl();
            List<ReportsProperties> propList = _serviceBean.readPageLabels(pageId);
            request.setAttribute("reportsProp", propList);
            
            //if report label is clicked
            if(request.getParameterMap().containsKey("reportId_lbl") && request.getParameter("reportId_lbl") != null)
            {
                //session.setAttribute("reportLabelId", request.getParameter("reportId_lbl"));
                session.setAttribute("reportLabelId", request.getParameter("reportId_lbl"));
                session.setAttribute("reportTitle", request.getParameter("reportTitle_lbl"));
                reportElements = _serviceBean.createReportElements(request.getParameter("reportId_lbl"));
            }
            
            else if(request.getParameterMap().containsKey("generateReport_btn") && request.getParameter("generateReport_btn") != null)
            {
                //Map<String, Object> map = new HashMap<>();
                Map<String, Object> paramMap = new HashMap<>();//Map that will pass to iReport
                Map<String, Object> resultMap = new HashMap<>();//Map that will receive the data of report
                String reportLabelId = session.getAttribute("reportLabelId").toString();
                String reportTitle = session.getAttribute("reportTitle").toString();
                List<String> reportElement = _serviceBean.readReportElement(reportLabelId);
                if(reportLabelId.equals("RPT_0001"))
                {
                    Map<String, String[]> parameters = request.getParameterMap();
                    for(String parameter : parameters.keySet()) {
                        System.out.println("param::" + parameter + ":: " + request.getParameter(parameter));
                    }
                    String customerId = request.getParameter("customer_scn");
                    int endChar = customerId.indexOf(":");
                    Map<String, Object> map = new HashMap<String, Object>();//Map that will pass to function
                    map.put("from", request.getParameter("from_cal"));
                    map.put("to", request.getParameter("to_cal"));
                    map.put("type", request.getParameter("category_ddl"));
                    String tempString = "2018-03-19";
                    if(!customerId.equals(""))
                    {
                        map.put("customerId", customerId.substring(0, endChar));
                    }
                    else
                    {
                        map.put("customerId", "");
                    }
                    
                    //map.put("all", request.getParameter("all_chk"));
                    map.put("rptId", reportLabelId);
                    map.put("reportTitle", reportTitle);
                    List<RPT0001Prop> rptProp = new ArrayList<>();
                    rptProp = _serviceBean.readRPT0001(map);
                    map.put("from", _stringUtil.DateConverter(request.getParameter("from_cal"),"yyyy-MM-dd","MMMMM d, yyyy"));
                    map.put("to", _stringUtil.DateConverter(request.getParameter("to_cal"),"yyyy-MM-dd","MMMMM d, yyyy"));
                    resultMap = _serviceBean.generateRPT(rptProp, map);
                    System.out.println("rpt0001 2");
                }
                
                //open pdf in browser
//                String fileName = resultMap.get("pdfName").toString();
//                String filePath = resultMap.get("pdfFile").toString().replaceAll("\\\\","/");
//                PrintWriter out = response.getWriter();
//                response.setContentType("application/pdf");
//
//                response.setHeader("Content-Disposition", "attachment; filename=" + fileName + ";");
//                FileOutputStream fileOut = new FileOutputStream(filePath);
//                String fileName = resultMap.get("pdfName").toString();
//                String filePath = resultMap.get("pdfFile").toString().replaceAll("\\\\","/");
                String filePath = resultMap.get("pdfFile").toString();
//                PrintWriter out = response.getWriter();
//                response.setContentType("application/pdf");
//                response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
//                FileInputStream fi = new FileInputStream(filePath);
//
//                fi.close();
//                out.close();
                System.out.println("111");
                Runtime.getRuntime().exec("rundll32 url.dll, FileProtocolHandler " + filePath);
                System.out.println("222");
//                response.setContentType("application/x-download");
//                response.addHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");             
//                //response.addHeader("Content-disposition", "attachment; filename=StatisticsrReport1.pdf");
//                OutputStream out = response.getOutputStream();
//                JasperExportManager.exportReportToPdfStream((InputStream)resultMap.get("jasperPrint"),out);
                reportElements = _serviceBean.createReportElements(reportLabelId);
                
            }
            else
            {
                session.setAttribute("reportLabelId", propList.get(0).getReportId());
                session.setAttribute("reportTitle", propList.get(0).getReportLabel());
                reportElements = _serviceBean.createReportElements(propList.get(0).getReportId());
            }
            
            request.setAttribute("reportElements", reportElements);
            //String reportLabelId = session.getAttribute("reportLabelId").toString();
            
            
            RequestDispatcher rd = request.getRequestDispatcher("view/jsp/reports.jsp");
            rd.forward(request, response);
        } 
        catch (Exception e)
        {
            
        }
        
        
        
    }
}
