package com.cmsv1.bean.login;






import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;


public class config
{
    public static void main(String[] args)
    {
        Properties properties = null;

        try
          {
            
        properties = new Properties();
        InputStream resourceAsStream =  config.class.getClassLoader().getResourceAsStream("com/cmsv1/properties/config.properties");
        System.out.println("1");
        properties.load(resourceAsStream);
        System.out.println("2");
        System.out.println(properties.getProperty("name"));
        } 
        catch (IOException e) {
            e.printStackTrace();
        }
       
    }
}
