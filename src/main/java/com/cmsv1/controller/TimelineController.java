
package com.cmsv1.controller;

import com.cmsv1.bean.AmountBalanceProp;
import com.cmsv1.bean.AmountBalanceServiceBeanImpl;
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
public class TimelineController
{
    @RequestMapping("TimelineController")
    public void timelineController(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        try
        {
            HttpSession session = request.getSession();
            String adminFullName = session.getAttribute("adminFullName").toString();
            String timeLineType = "unused";
            TimelineServiceBeanImpl _serviceBean = new TimelineServiceBeanImpl();
//            if(request.getParameterMap().containsKey("isFromHome") && request.getParameter("isFromHome").equals("1"))
//            {
//                List<TimeBalanceProp> propList = _serviceBean.getTimeBalance();
//                request.setAttribute("timeProp", propList);
//                RequestDispatcher rd = request.getRequestDispatcher("view/jsp/timeline/timeline.jsp");
//                rd.forward(request, response);
//            }

            if(request.getParameter("addBalance_btn") != null)
            {
                _serviceBean.createTimeBalance(adminFullName, request.getParameter("custName_txt"), 
                        request.getParameter("timeHour_txt"), request.getParameter("timeMinute_txt"), 
                        request.getParameter("comment_txt"));
//                List<TimeBalanceProp> propList = _serviceBean.getTimeBalance();
//                request.setAttribute("timeProp", propList);
//                RequestDispatcher rd = request.getRequestDispatcher("view/jsp/timeline/timeline.jsp");
//                rd.forward(request, response);
            }
            
            else if(request.getParameterMap().containsKey("del") && request.getParameter("del").equals("1"))
            {
                _serviceBean.deleteTimeBalance(adminFullName, request.getParameter("id"));
            }
            
            else if(request.getParameterMap().containsKey("timeLineType"))
            {
                timeLineType = request.getParameter("timeLineType");
            }
            List<TimeBalanceProp> propList = _serviceBean.getTimeBalance(timeLineType);
            request.setAttribute("timeProp", propList);
            request.setAttribute("timeLineType", timeLineType);
            RequestDispatcher rd = request.getRequestDispatcher("view/jsp/timeline/timeline.jsp");
            rd.forward(request, response);
        }
        catch (Exception e)
        {
            
        }
    }
}
