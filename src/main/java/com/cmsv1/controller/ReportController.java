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
            HttpSession session = request.getSession();
            String page = session.getAttribute("page").toString();
            String adminFullName = session.getAttribute("adminFullName").toString();
            ReportsServiceBeanImpl _serviceBean = new ReportsServiceBeanImpl();
            
            List<ReportsProperties> propList = _serviceBean.readPageLabels(page);
            session.setAttribute("reportsProp", propList);
            
            //if report label is clicked
            if(request.getParameterMap().containsKey("reportLabelId") && request.getParameter("reportLabelId") != null)
            {
                String reportElements = _serviceBean.createReportElements(request.getParameter("reportLabelId"));
                request.setAttribute("reportElements", reportElements);
                System.out.println(reportElements);
            }
            
            else
            {
                String reportElements = _serviceBean.createDefaultReportElements(page);
                request.setAttribute("reportElements", reportElements);
                System.out.println("here123");
            }
            
            RequestDispatcher rd = request.getRequestDispatcher("view/jsp/reports.jsp");
            rd.forward(request, response);
        } 
        catch (Exception e)
        {
            
        }
        
        
        
    }
}
