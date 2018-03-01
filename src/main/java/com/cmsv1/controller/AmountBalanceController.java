
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
public class AmountBalanceController
{   
    @RequestMapping("AmountBalanceController")
    public void amountBalanceController(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        try
        {
            HttpSession session = request.getSession();
            String adminFullName = session.getAttribute("adminFullName").toString();
            String timeLineType = "unpaid";
            AmountBalanceServiceBeanImpl _serviceBean = new AmountBalanceServiceBeanImpl();
            if(request.getParameter("addBalance_btn") != null)
            {
                _serviceBean.createAmountBalance(adminFullName, request.getParameter("custName_txt"), request.getParameter("amount_txt"), request.getParameter("comment_txt"));
            }
             
            else if(request.getParameterMap().containsKey("del") && request.getParameter("del").equals("1"))
            {
                _serviceBean.updateAmountBalance(adminFullName, request.getParameter("id"));
            }
            
            else if(request.getParameterMap().containsKey("timeLineType"))
            {
                timeLineType = request.getParameter("timeLineType");
            }
            List<AmountBalanceProp> propList = _serviceBean.getAmountBalance(timeLineType);
            request.setAttribute("balanceProp", propList);
            request.setAttribute("timeLineType", timeLineType);
            RequestDispatcher rd = request.getRequestDispatcher("view/jsp/timeline/AmountBalance.jsp");
            rd.forward(request, response);  
            
        }
        catch (Exception e)
        {
            
        }
    }
}