/**
 * 
 */
package com.jpmc.examples.stockmarket;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import com.jpmc.examples.stockmarket.enums.StockTypeEnum;

/**
 * @author santoshini.jami
 *
 */
public class StockTest {
	
	@Test
	public void shouldReturnValidDividendYieldForStockTypeAsCommon() {
		Stock stockData = new Stock("POP", StockTypeEnum.COMMON, 8.0, 0.0, 100); 
		assertEquals(0.08, stockData.getDividendYield(100.0), 0.0);
	}
	
	@Test
	public void shouldReturnValidDividendYieldForStockTypeAsPreferred() {
		Stock stockData = new Stock("GIN", StockTypeEnum.PREFERRED, 8.0, 2.0, 100); 
		assertEquals(20.0, stockData.getDividendYield(10.0), 0.0);
	}
	
	@Test
	public void shouldReturnDefaultDividendYieldForInvalidPrice() {
		Stock stockData = new Stock("GIN", StockTypeEnum.PREFERRED, 8.0, 2.0, 100); 
		assertEquals(0.0, stockData.getDividendYield(0.0), 0.0);
	}
	
	@Test
	public void shouldReturnValidPERatioForStockTypeAsCommon() {
		Stock stockData = new Stock("POP", StockTypeEnum.COMMON, 8.0, 0.0, 100); 
		assertEquals(1250.0, stockData.getPERatio(100.0), 0.0);
	}
	
	@Test
	public void shouldReturnValidPERatioForStockTypeAsPreferred() {
		Stock stockData = new Stock("GIN", StockTypeEnum.PREFERRED, 8.0, 2.0, 100); 
		assertEquals(0.5, stockData.getPERatio(10.0), 0.0);
	}
	
	@Test
	public void shouldReturnDefaultPERatioForInvalidPrice() {
		Stock stockData = new Stock("GIN", StockTypeEnum.PREFERRED, 8.0, 2.0, 100); 
		assertEquals(0.0, stockData.getPERatio(0.0), 0.0);
	}
	
	@Test
	public void shouldReturnTradesWithBuyIndicator() {
		Stock stockData = new Stock("GIN", StockTypeEnum.PREFERRED, 8.0, 2.0, 100); 
		stockData.buy(20.0, 5);
		assertEquals(1, stockData.getTrades().size());
		assertEquals("BUY", ((Trade)stockData.getTrades().firstEntry().getValue()).getType().name());
	}
	
	@Test
	public void shouldReturnTradesWithSellIndicator() {
		Stock stockData = new Stock("GIN", StockTypeEnum.PREFERRED, 8.0, 2.0, 100); 
		stockData.sell(20.0, 5);
		assertEquals(1, stockData.getTrades().size());
		assertEquals("SELL", ((Trade)stockData.getTrades().firstEntry().getValue()).getType().name());
	}
	
	@Test
	public void shouldReturnValidVolWeightedStockPrice() throws InterruptedException {
		Stock stockData = new Stock("TEA", StockTypeEnum.PREFERRED, 0.0, 2.0, 100);
		for (int count = 1; count < 4; count++) {
			stockData.sell(count + 20.0, count);
			Thread.sleep(10);
			stockData.buy(count + 10.0, count);
		}
		assertNotNull(stockData.getVolumeWeightedStockPrice());
	}
	
	@Test
	public void shouldReturnValidVolWeightedStockPriceForSingleTrade() throws InterruptedException {
		Stock stockData = new Stock("TEA", StockTypeEnum.PREFERRED, 0.0, 2.0, 100);
		stockData.sell(20.0, 5);
		assertEquals(20.0, stockData.getVolumeWeightedStockPrice(), 0.0);
	}
	
	@Test
	public void shouldReturnDefaultVolWeightedStockPriceForInvalidInput() throws InterruptedException {
		Stock stockData = new Stock("TEA", StockTypeEnum.PREFERRED, 0.0, 0.0, 0);
		stockData.sell(0.0, 2);
		assertEquals(0.0, stockData.getVolumeWeightedStockPrice(), 0.0);
	}
	
}
