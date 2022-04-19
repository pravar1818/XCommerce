package com.crio.xcommerce.contract.analytics;

import java.io.File;
import java.io.IOException;
import com.crio.xcommerce.contract.exceptions.AnalyticsException;
import com.crio.xcommerce.contract.insights.SaleAggregate;

public class SalesAnalyticFactoryImpl {

    public static SaleAggregate getSalesAnaltyics(File file, String vendorName, int year)
            throws IOException, AnalyticsException {

        SaleAggregate saleAggregate = null;

        if(vendorName.equals("flipkart")){
            IAnalytics flipkartAnalytics = new FlipkartAnalyticsImpl();
            saleAggregate = flipkartAnalytics.analysis(year, file);
        }

        else if(vendorName.equals("amazon")){
            IAnalytics amazonAnalytics = new AmazonAnalyticsImpl();
            saleAggregate = amazonAnalytics.analysis(year, file);
        }

        else if(vendorName.equals("ebay")){
            IAnalytics ebayAnalytics = new EBayAnalyticsImpl();
            saleAggregate = ebayAnalytics.analysis(year, file);
        }
        
        return saleAggregate;
    }
}