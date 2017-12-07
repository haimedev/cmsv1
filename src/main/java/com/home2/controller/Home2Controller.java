package com.home2.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class Home2Controller
{
    @RequestMapping("/homePage2")
    public String homePage()
    {
        return "home.jsp";
    }
}
