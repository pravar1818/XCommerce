package com.crio.xcommerce.contract.insights;

import java.io.File;
import java.io.IOException;
import com.crio.xcommerce.contract.exceptions.AnalyticsException;
import com.crio.xcommerce.contract.resolver.DataProvider;
import com.crio.xcommerce.contract.analytics.*;

public class SaleInsightsImpl implements SaleInsights {


    @Override
    public SaleAggregate getSaleInsights(DataProvider dataProvider, int year) throws IOException, AnalyticsException {
        // TODO Auto-generated method stub

        File file = dataProvider.resolveFile();
        String vendorName = dataProvider.getProvider();

        SaleAggregate saleAggregate = SalesAnalyticFactoryImpl.getSalesAnaltyics(file,vendorName,year);
        return saleAggregate;

    }
    
}