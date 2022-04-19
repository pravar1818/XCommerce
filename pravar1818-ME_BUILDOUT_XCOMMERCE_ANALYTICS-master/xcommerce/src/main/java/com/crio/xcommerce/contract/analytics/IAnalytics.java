package com.crio.xcommerce.contract.analytics;

import java.io.File;
import java.io.IOException;

import com.crio.xcommerce.contract.exceptions.AnalyticsException;
import com.crio.xcommerce.contract.insights.SaleAggregate;

public interface IAnalytics {
    public SaleAggregate analysis(int year, File file) throws IOException, AnalyticsException;
        
}