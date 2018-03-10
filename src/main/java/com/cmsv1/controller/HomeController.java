/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cmsv1.controller;

import java.io.IOException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author UpuanNgHARI
 */
@Controller
public class HomeController
{
    @RequestMapping("/HomeController")
    public void homeController(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        HttpSession session = request.getSession();
        boolean adminLogged = ((session.getAttribute("adminFullName")== "" || session.getAttribute("adminFullName") == null) ? false:true);
        if(adminLogged)
        {
            RequestDispatcher rd = request.getRequestDispatcher("view/jsp/home.jsp");
            rd.forward(request, response);
        }
        
        else
        {
            response.sendRedirect("LoginPage");
        }
    }
    
    @RequestMapping("/HomePage")
    public String getHomePage()
    {
        return "view/jsp/home.jsp";
    }
}
