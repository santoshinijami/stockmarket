package com.jpmc.examples.stockmarket;

import java.util.HashMap;
import java.util.Map;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.jpmc.examples.stockmarket.enums.StockTypeEnum;

/**
 * @author santoshini.jami
 *
 */
public class GBCETest {
	
	Map<String, Stock> stockMap;
	
	@Before
	public void setUp() {
		stockMap = new HashMap<String, Stock>();
		stockMap.put("TEA", new Stock("TEA", StockTypeEnum.COMMON, 0.0, 0.0, 100));
		stockMap.put("POP", new Stock("POP", StockTypeEnum.COMMON, 8.0, 0.0, 100));
	}
	
	@Test
	public void shouldReturnValidAllShareIndexForStocks() {
		for (Stock stock : stockMap.values()) {
			stock.buy(10.0, 10);
			stock.sell(2.0, 20);
		}
		Assert.assertNotNull(GBCE.getAllShareIndex(stockMap));
	}
	
	@Test
	public void shouldReturnDefaultAllShareIndexForZeroPricesAndQuantity() {
		for (Stock stock : stockMap.values()) {
			stock.buy(0.0, 0);
			stock.sell(0.0, 0);
		}
		Assert.assertNotNull(GBCE.getAllShareIndex(stockMap));
	}
	
	@After
	public void tearDown() {
		stockMap = null;
	}
}
