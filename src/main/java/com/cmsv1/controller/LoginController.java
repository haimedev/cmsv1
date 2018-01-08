package com.cmsv1.controller;

import com.cmsv1.bean.LoginServiceBeanImpl;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.Properties;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class LoginController
{
    @RequestMapping("/LoginController")
    public void UserLogin(HttpServletRequest request, HttpServletResponse response) throws SQLException
    {
        LoginServiceBeanImpl _loginServiceBeanImpl = new LoginServiceBeanImpl();
        ModelAndView mv = new ModelAndView();
        try
        {
            Properties prop = new Properties();
            InputStream iS = LoginController.class.getClassLoader().getResourceAsStream("com/cmsv1/properties/config.properties");
            prop.load(iS);
            System.out.println(request.getParameter("login_btn"));
            if(request.getParameter("login_btn")!= null)
            {
                System.out.println(request.getParameter("login_btn"));
                RequestDispatcher rd = null;
                boolean isValid = false;
                isValid = _loginServiceBeanImpl.isUserValid(request.getParameter("userName"), request.getParameter("passWord"));
                if(isValid)
                {
//                    mv.setViewName("view/jsp/home/home.jsp");
//                    mv.addObject("name", prop.getProperty("name"));
//                    rd = request.getRequestDispatcher("/view/jsp/home/home.jsp");
//                    rd.forward(request, response);
                    response.sendRedirect("HomePage");
                }
                else
                {
//                    HttpSession session = request.getSession();
//                    mv.setViewName("view/jsp/login/login.jsp");
//                    mv.addObject("isUserValid", "false");
//                    session.setAttribute("wrongName", request.getAttribute("hahaha"));
//                    System.out.println("spring mvc failed");
//                    rd = request.getRequestDispatcher("HomeController");
//                    rd.forward(request, response);
                    response.sendRedirect("HomeController");
                }
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
//        return mv;
    }
    
    @RequestMapping("/LoginPage")
    public String getLoginPage()
    {
        return "view/jsp/login/login.jsp";
    }
}
