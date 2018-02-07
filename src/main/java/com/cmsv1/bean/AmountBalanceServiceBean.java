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
public interface AmountBalanceServiceBean
{
    public List<AmountBalanceProp> getAmountBalance(String timeLineType);
    public void createAmountBalance(String adminFullName, String custName, String amount, String comments);
    public void updateAmountBalance(String adminFullName, String amountId);
}
