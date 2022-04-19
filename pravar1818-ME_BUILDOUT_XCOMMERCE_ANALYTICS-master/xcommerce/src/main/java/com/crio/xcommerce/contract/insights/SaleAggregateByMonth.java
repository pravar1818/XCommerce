
package com.crio.xcommerce.contract.insights;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class SaleAggregateByMonth {
  private Integer month;
  private Double sales;

}

