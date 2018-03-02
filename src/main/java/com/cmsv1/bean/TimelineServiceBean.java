/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cmsv1.bean;

import com.cmsv1.bean.TimeBalanceProp;
import java.util.List;

/**
 *
 * @author UpuanNgHARI
 */
public interface TimelineServiceBean
{
    public List<TimeBalanceProp> getTimeBalance(String timeLineType);
    public void createTimeBalance(String adminFullName, String custName, String timeHour, String timeMinute, String comments);
    public void deleteTimeBalance(String adminFullName, String timeId);
    public List<String> readCustomers();
}
