/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cmsv1.bean;

import java.sql.SQLException;
import java.util.Map;

/**
 *
 * @author UpuanNgHARI
 */
public interface LoginServiceBean
{
   public Map<String, Object> isUserValid(String userName, String passWord) throws SQLException;
   public String retrieveUserFullName(String userName, String passWord) throws SQLException;
}
