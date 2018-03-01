/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cmsv1.bean;

/**
 *
 * @author UPUANNGHARI
 */
public class ReportsProperties 
{
    String reportId;
    String reportLabel;
    String elementLabel;
    String elementType;

    public String getElementLabel()
    {
        return elementLabel;
    }

    public void setElementLabel(String elementLabel)
    {
        this.elementLabel = elementLabel;
    }

    public String getElementType()
    {
        return elementType;
    }

    public void setElementType(String elementType)
    {
        this.elementType = elementType;
    }
    
    public String getReportId()
    {
        return reportId;
    }

    public void setReportId(String reportId)
    {
        this.reportId = reportId;
    }

    public String getReportLabel()
    {
        return reportLabel;
    }

    public void setReportLabel(String reportLabel)
    {
        this.reportLabel = reportLabel;
    }
}
