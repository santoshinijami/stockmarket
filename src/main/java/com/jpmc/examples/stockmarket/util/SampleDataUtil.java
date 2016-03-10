package com.jpmc.examples.stockmarket.util;

import java.util.HashMap;
import java.util.Map;

import com.jpmc.examples.stockmarket.Stock;
import com.jpmc.examples.stockmarket.enums.StockTypeEnum;

/**
 * This class holds the test data for the stocks
 * 
 * @author santoshini.jami
 *
 */
public class SampleDataUtil {

	/**
	 * This method holds the sampleData required by the Application
	 * 
	 * @return Map<String, Stock>
	 */
	public Map<String, Stock> getStockSampleData() {
		Map<String, Stock> stockMap = new HashMap<String, Stock>();
		stockMap.put("TEA", new Stock("TEA", StockTypeEnum.COMMON, 0.0, 0.0, 100));
		stockMap.put("POP", new Stock("POP", StockTypeEnum.COMMON, 8.0, 0.0, 100));
		stockMap.put("ALE", new Stock("ALE", StockTypeEnum.COMMON, 23.0, 0.0, 60));
		stockMap.put("GIN", new Stock("GIN", StockTypeEnum.PREFERRED, 8.0, 2.0, 100));
		stockMap.put("JOE", new Stock("JOE", StockTypeEnum.COMMON, 13.0, 0.0, 250));
		return stockMap;
	}

}
