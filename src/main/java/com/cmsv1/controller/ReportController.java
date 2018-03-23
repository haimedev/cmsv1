package com.cmsv1.controller;

import com.cmsv1.bean.AmountBalanceProp;
import com.cmsv1.bean.AmountBalanceServiceBeanImpl;
import com.cmsv1.bean.properties.RPTCustomerRecordsProp;
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
@Controller
public class ReportController 
{
    @RequestMapping("ReportController")
    public void reportController(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
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
                reportElements = _serviceBean.createReportElements(request.getParameter("reportId_lbl"));
            }
            
            else if(request.getParameterMap().containsKey("generateReport_btn") && request.getParameter("generateReport_btn") != null)
            {
                //Map<String, Object> map = new HashMap<>();
                Map<String, Object> paramMap = new HashMap<String, Object>();//Map that will pass to iReport
                String reportLabelId = session.getAttribute("reportLabelId").toString();
                List<String> reportElement = _serviceBean.readReportElement(reportLabelId);
                if(reportLabelId.equals("RPT_0001"))
                {
                    System.out.println("rpt0001 11111");
                    Map<String, Object> map = new HashMap<String, Object>();//Map that will pass to function
                    map.put("from", request.getParameter("from_cal"));
                    map.put("to", request.getParameter("to_cal"));
                    map.put("all", request.getParameter("all_chk"));
                    map.put("rptId", reportLabelId);
                    List<RPTCustomerRecordsProp> rptProp = new ArrayList<>();
                    rptProp = _serviceBean.readRPTCustomerRecords(map);
                    _serviceBean.generateRPT(rptProp, map);
                    System.out.println("rpt0001 2");
                }
                System.out.println("a1");
                reportElements = _serviceBean.createReportElements(reportLabelId);
                System.out.println("a2");
            }
            else
            {
                session.setAttribute("reportLabelId", propList.get(0).getReportId());
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
