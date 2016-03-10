package com.jpmc.examples.stockmarket;

import java.util.Map;

/**
 * This class defines the functionality of GBCE
 * 
 * @author santoshini.jami
 *
 */
public class GBCE {

	/**
	 * Method to calculate AllShareIndex for the input stocks
	 * 
	 * @param stocks
	 *            - List of Stocks
	 * @return calculated AllShareIndex
	 */
	public static Double getAllShareIndex(Map<String, Stock> stocks) {
		Double resultedStock = 0.0;
		for (Stock stock : stocks.values()) {
			resultedStock += stock.getTradePrice();
		}
		return Math.pow(resultedStock, 1.0 / stocks.size());
	}
}
