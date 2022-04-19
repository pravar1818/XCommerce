package com.crio.xcommerce.contract.analytics;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import com.crio.xcommerce.contract.exceptions.AnalyticsException;
import com.crio.xcommerce.contract.insights.SaleAggregateByMonth;
import com.crio.xcommerce.contract.insights.SaleAggregate;

public class FlipkartAnalyticsImpl implements IAnalytics {

    public static SaleAggregate getRequiredSaleAggregateOfFlipkart(
            List<SaleAggregateByMonth> listOfSalesAggregateByMonth) {

        SaleAggregate saleAggregate = new SaleAggregate();
        List<SaleAggregateByMonth> aggregateByMonthsList = new ArrayList<>();
        Double totalSale = 0.0;

        for (int month = 1; month <= 12; month++) {
            Double totalMonthSales = 0.0;

            for (SaleAggregateByMonth saleAggregateByMonth : listOfSalesAggregateByMonth) {
                if (saleAggregateByMonth.getMonth() == month) {
                    totalMonthSales = totalMonthSales + saleAggregateByMonth.getSales();
                    DecimalFormat df = new DecimalFormat("###.##");
                    totalSale = totalSale + saleAggregateByMonth.getSales();
                    totalSale = Double.parseDouble(df.format(totalSale));
                }
            }

            SaleAggregateByMonth saleAggregateByMonthObject = new SaleAggregateByMonth();

            saleAggregateByMonthObject.setMonth(month);
            saleAggregateByMonthObject.setSales(totalMonthSales);

            aggregateByMonthsList.add(saleAggregateByMonthObject);
        }

        saleAggregate.setTotalSales(totalSale);
        saleAggregate.setAggregateByMonths(aggregateByMonthsList);

        return saleAggregate;

    }

    @Override
    public SaleAggregate analysis(int year, File file) throws IOException, AnalyticsException {
        // TODO Auto-generated method stub
        List<SaleAggregateByMonth> listOfSalesAggregateByMonth = new ArrayList<>();
        
        String line = ""; 
        boolean flag = false;
    
        BufferedReader br = new BufferedReader(new FileReader(file));  
        while ((line = br.readLine()) != null){  
            if(flag==false){
                flag=true;
            }
            else{
                String[] tokens = line.split(",");    
                if(tokens[3] == "" || tokens[5] == ""){
                    throw new AnalyticsException("Invalid Data");
                }
                if((LocalDate.parse(tokens[3]).getYear() == year) && (tokens[4].equals("complete") || tokens[4].equals("paid") || tokens[4].equals("shipped"))){
                    SaleAggregateByMonth saleAggregateByMonth = new SaleAggregateByMonth();
                    saleAggregateByMonth.setMonth(LocalDate.parse(tokens[3]).getMonthValue());
                    saleAggregateByMonth.setSales(Double.parseDouble(tokens[5]));
                    listOfSalesAggregateByMonth.add(saleAggregateByMonth);
                }
            }
            
        }  

        SaleAggregate saleAggregate = getRequiredSaleAggregateOfFlipkart(listOfSalesAggregateByMonth);
      
        return saleAggregate;
 
    }
}