package com.cmsv1.controller.login;

import com.cmsv1.bean.login.LoginServiceBeanImpl;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.Properties;
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
    public ModelAndView UserLogin(HttpServletRequest request, HttpServletResponse response) throws SQLException
    {
        LoginServiceBeanImpl _loginServiceBeanImpl = new LoginServiceBeanImpl();
        ModelAndView mv = new ModelAndView();
        try
        {
            Properties prop = new Properties();
            InputStream iS = LoginController.class.getClassLoader().getResourceAsStream("com/cmsv1/properties/config.properties");
            prop.load(iS);
            if(request.getParameter("login_btn")!= null)
            {
                boolean isValid = false;
                isValid = _loginServiceBeanImpl.isUserValid(request.getParameter("userName"), request.getParameter("passWord"));
                if(isValid)
                {
////                  HttpSession session = request.getSession();
////                  session.setAttribute("imagesPath", _systemDirectoryPath);
////                  session.setAttribute("userName", request.getParameter("userName"));
////                  request.getRequestDispatcher("jsp/home/home.jsp").forward(request, response);
////                    System.out.println("true");
                    mv.setViewName("view/jsp/home/home.jsp");
                    mv.addObject("name", prop.getProperty("name"));
                    //mv.addObject("name", _config.prop.getProperty("imgPath"));
                }
                else
                {
                    mv.setViewName("home.jsp");
                    mv.addObject("name", prop.getProperty("name"));
                    System.out.println("spring mvc failed");
                }
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return mv;
    }
}
