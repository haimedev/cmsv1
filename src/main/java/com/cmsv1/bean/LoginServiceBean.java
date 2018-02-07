/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cmsv1.bean;

import java.sql.SQLException;

/**
 *
 * @author UpuanNgHARI
 */
public interface LoginServiceBean
{
   public boolean isUserValid(String userName, String passWord) throws SQLException;
   public String retrieveUserFullName(String userName, String passWord) throws SQLException;
}
