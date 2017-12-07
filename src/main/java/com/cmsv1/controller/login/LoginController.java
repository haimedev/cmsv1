package com.cmsv1.controller.login;

import com.cmsv1.bean.login.*;
import java.sql.SQLException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class LoginController
{
    LoginServiceBeanImpl _loginServiceBeanImpl = new LoginServiceBeanImpl();
    @RequestMapping("/LoginController")
    public ModelAndView UserLogin(HttpServletRequest request, HttpServletResponse response) throws SQLException
    {
        ModelAndView mv = new ModelAndView();
        
        if(request.getParameter("login_btn")!= null)
          {
            boolean isValid = false;
              isValid = _loginServiceBeanImpl.isUserValid(request.getParameter("userName"), request.getParameter("passWord"));
              if(isValid)
                {
//                  HttpSession session = request.getSession();
//                  session.setAttribute("imagesPath", _systemDirectoryPath);
//                  session.setAttribute("userName", request.getParameter("userName"));
//                  request.getRequestDispatcher("jsp/home/home.jsp").forward(request, response);
//                    System.out.println("true");
                    mv.setViewName("home.jsp");
                    mv.addObject("name", "spring mvc in the house");
                }
              
              else
                {
                    mv.setViewName("home.jsp");
                    mv.addObject("name", "spring mvc faileds");
                    System.out.println("spring mvc failed");
                }
          }
        return mv;
    }
}
