package com.cmsv1.controller;

import com.cmsv1.bean.AmountBalanceProp;
import com.cmsv1.bean.AmountBalanceServiceBeanImpl;
import com.cmsv1.bean.ReportsProperties;
import com.cmsv1.bean.ReportsServiceBeanImpl;
import com.cmsv1.bean.TimeBalanceProp;
import com.cmsv1.bean.TimelineServiceBeanImpl;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
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
            System.out.println(request.getParameter("reportTitle_lbl"));
            System.out.println(request.getParameter("reportId_lbl"));
            HttpSession session = request.getSession();
            String pageId = session.getAttribute("pageId").toString();
            String adminFullName = session.getAttribute("adminFullName").toString();
            ReportsServiceBeanImpl _serviceBean = new ReportsServiceBeanImpl();
            
            List<ReportsProperties> propList = _serviceBean.readPageLabels(pageId);
            session.setAttribute("reportsProp", propList);
            
            //if report label is clicked
            if(request.getParameterMap().containsKey("reportId_lbl") && request.getParameter("reportId_lbl") != null)
            {
                String reportElements = _serviceBean.createReportElements(request.getParameter("reportId_lbl"));
                request.setAttribute("reportElements", reportElements);
            }
            
            else
            {
                String reportElements = _serviceBean.createDefaultReportElements(pageId);
                request.setAttribute("reportElements", reportElements);
            }
            
            RequestDispatcher rd = request.getRequestDispatcher("view/jsp/reports.jsp");
            rd.forward(request, response);
        } 
        catch (Exception e)
        {
            
        }
        
        
        
    }
}
