/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cmsv1.bean;

import com.cmsv1.bean.TimeBalanceProp;
import com.cmsv1.bean.properties.SysCustomerProp;
import java.util.List;

/**
 *
 * @author UpuanNgHARI
 */
public interface TimelineServiceBean
{
    public List<TimeBalanceProp> readTimeBalance(String timeLineType);
    public void createTimeBalance(String adminId, String customerId, String timeHour, String timeMinute, String comment);
    public void updateFreeTime(String adminId, String transacId);
    public List<SysCustomerProp> readCustomers();
}
