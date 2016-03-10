package com.jpmc.examples.stockmarket;

import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.jpmc.examples.stockmarket.util.SampleDataUtil;

/**
 * This is main class to test the functionality with sample stock market data
 * 
 * @author santoshini.jami
 *
 */
public class Application {

	private static Log logger = LogFactory.getLog(Application.class);

	/**
	 * Main method to calculate the DividendYield, PERatio, AllShareIndex for
	 * the stocks
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			@SuppressWarnings("resource")
			ApplicationContext context = new ClassPathXmlApplicationContext(new String[] { "spring-context.xml" });
			SampleDataUtil sampleData = (SampleDataUtil) context.getBean("sampleStocksBean");
			Map<String, Stock> sampleStocks = sampleData.getStockSampleData();
			for (Stock stock : sampleStocks.values()) {
				logger.info("--------------Stock Starts-----------------");
				logger.info("Stock Details: " + stock.toString());
				logger.info("Stock DividendYield: " + stock.getDividendYield(10.0));
				logger.info("Stock PERatio: " + stock.getPERatio(10.0));

				stock.buy(1.0, 10);
				stock.sell(12.0, 20);
				logger.info("Stock TradePrice: " + stock.getTradePrice());

				for (Trade trade : stock.getTrades().values()) {
					logger.info("Trade Details: " + trade.toString());
				}
				logger.info("--------------Stock Ends-----------------");
			}

			Double shareIndex = GBCE.getAllShareIndex(sampleStocks);
			logger.info("Resulted AllShareIndex: " + shareIndex);
		} catch (BeansException e) {
			logger.error("Exception occurred while executing StockMarket functionality: " + e.getMessage(), e);
		}
	}
}
