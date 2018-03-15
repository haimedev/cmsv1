
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
            String adminId = session.getAttribute("adminId").toString();
            String timeLineType = "unpaid";
            AmountBalanceServiceBeanImpl _serviceBean = new AmountBalanceServiceBeanImpl();
            if(request.getParameter("addBalance_btn") != null)
            {
                String customerId = request.getParameter("custId_txt");
                _serviceBean.createMoneyBalance(adminId, customerId, request.getParameter("amount_txt"), request.getParameter("comment_txt"));
            }
             
            else if(request.getParameterMap().containsKey("del") && request.getParameter("del").equals("1"))
            {
                System.out.println("here123");
                _serviceBean.updateMoneyBalance(adminId, request.getParameter("transacId"));
            }
            
            else if(request.getParameterMap().containsKey("timeLineType"))
            {
                timeLineType = request.getParameter("timeLineType");
            }
            List<AmountBalanceProp> propList = _serviceBean.readMoneyBalance(timeLineType);
            request.setAttribute("balanceProp", propList);
            request.setAttribute("timeLineType", timeLineType);
            request.setAttribute("customers", _serviceBean.readCustomers());
            RequestDispatcher rd = request.getRequestDispatcher("view/jsp/AmountBalance.jsp");
            rd.forward(request, response);  
            
        }
        catch (Exception e)
        {
            
        }
    }
}