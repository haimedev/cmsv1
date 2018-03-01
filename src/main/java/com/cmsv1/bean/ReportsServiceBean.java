/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cmsv1.bean;
import java.sql.SQLException;
import java.util.List;


public interface ReportsServiceBean 
{
    public List<ReportsProperties> readPageLabels(String reportId) throws SQLException;
    public String createReportElements(String reportLabelId);
}
