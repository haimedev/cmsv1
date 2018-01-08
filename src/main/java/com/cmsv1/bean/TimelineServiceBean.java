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
    public List<TimeBalanceProp> getTimeBalance();
    public void createTimeBalance(String custName, String timeHour, String timeMinute);
    public void deleteTimeBalance(String timeId);
}
