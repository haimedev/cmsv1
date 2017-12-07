package com.home.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class HomeController
{
    @RequestMapping("/homePage")
    public ModelAndView homePage()
    {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("home.jsp");
        mv.addObject("name", "haime reyes");
        return mv;
    }
}
