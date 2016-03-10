package com.jpmc.examples.stockmarket;

import java.time.LocalDateTime;
import java.util.SortedMap;
import java.util.TreeMap;

import com.jpmc.examples.stockmarket.enums.StockTypeEnum;
import com.jpmc.examples.stockmarket.enums.TradeTypeEnum;
import com.jpmc.examples.stockmarket.util.AppConstants;

/**
 * Class to define Stock parameters and functionality to be done on each Stock
 * 
 * @author santoshini.jami
 * 
 */
public class Stock {

	private String stockSymbol;
	private StockTypeEnum type;
	private Double lastDividend;
	private Double fixedDividend;
	private int parValue;
	private TreeMap<LocalDateTime, Trade> trades;

	public Stock(String stockSymbol, StockTypeEnum type, Double lastDividend, Double fixedDividend, int parValue) {
		this.setStockSymbol(stockSymbol);
		this.setType(type);
		this.setLastDividend(lastDividend);
		this.setFixedDividend(fixedDividend);
		this.setParValue(parValue);
		this.trades = new TreeMap<LocalDateTime, Trade>();
	}

	/**
	 * Method returns the last trade price for the stock
	 * 
	 * @return Trade Price value
	 */
	public Double getTradePrice() {
		if (this.trades.size() > 0) {
			return this.trades.lastEntry().getValue().getPrice();
		} else {
			return 0.0;
		}
	}

	/**
	 * Method to calculate DividendYield for given price for each stock
	 * 
	 * @param price
	 *            - Input price
	 * @return dividendYield - Returns 0.0 if input price is less than 0
	 */
	public Double getDividendYield(Double price) {
		if (price <= 0) {
			return 0.0;
		}

		switch (this.getType()) {
		case COMMON:
			return this.getLastDividend() / price;
		case PREFERRED:
			return (this.getFixedDividend() * this.getParValue()) / price;
		default:
			return 0.0;
		}
	}

	/**
	 * Method to calculate PERatio for given price for each stock
	 * 
	 * @param price
	 *            - Input price
	 * @return PERatio - Returns 0.0 if input price is less than 0
	 */
	public Double getPERatio(Double price) {
		if (price <= 0) {
			return 0.0;
		} else {
			return price / this.getDividendYield(price);
		}

	}

	/**
	 * Method to add trades with BUY indicator
	 * 
	 * @param price
	 *            - Input price for the trade
	 * @param quantityOfShares
	 *            - Input quantityOfShares for the trade
	 */
	public void buy(Double price, int quantityOfShares) {
		Trade tradeBought = new Trade(TradeTypeEnum.BUY, quantityOfShares, price);
		this.getTrades().put(LocalDateTime.now(), tradeBought);

	}

	/**
	 * Method to add trades with SELL indicator
	 * 
	 * @param price
	 *            - Input price for the trade
	 * @param quantityOfShares
	 *            - Input quantityOfShares for the trade
	 */
	public void sell(Double price, int quantityOfShares) {
		Trade tradeSold = new Trade(TradeTypeEnum.SELL, quantityOfShares, price);
		this.getTrades().put(LocalDateTime.now(), tradeSold);
	}

	/**
	 * Method to calculate the VolumeWeightedStockPrice for the trades in stock
	 * 
	 * @return - 0.0 if totalQuantity of trade is 0 else calculated value
	 */
	public Double getVolumeWeightedStockPrice() {
		LocalDateTime startTime = LocalDateTime.now().minusMinutes(AppConstants.TIME);
		SortedMap<LocalDateTime, Trade> resultedTrades = this.trades.tailMap(startTime);
		Double tradedPrice = 0.0;
		int totalQuantity = 0;
		for (Trade trade : resultedTrades.values()) {
			tradedPrice += trade.getPrice() * trade.getQuantityOfShares();
			totalQuantity += trade.getQuantityOfShares();
		}

		if (totalQuantity <= 0) {
			return 0.0;
		}

		return tradedPrice / totalQuantity;
	}

	public String getStockSymbol() {
		return stockSymbol;
	}

	public void setStockSymbol(String stockSymbol) {
		this.stockSymbol = stockSymbol;
	}

	public StockTypeEnum getType() {
		return type;
	}

	public void setType(StockTypeEnum type) {
		this.type = type;
	}

	public Double getLastDividend() {
		return lastDividend;
	}

	public void setLastDividend(Double lastDividend) {
		this.lastDividend = lastDividend;
	}

	public Double getFixedDividend() {
		return fixedDividend;
	}

	public void setFixedDividend(Double fixedDividend) {
		this.fixedDividend = fixedDividend;
	}

	public int getParValue() {
		return parValue;
	}

	public void setParValue(int parValue) {
		this.parValue = parValue;
	}

	public TreeMap<LocalDateTime, Trade> getTrades() {
		return trades;
	}

	public void setTrades(TreeMap<LocalDateTime, Trade> trades) {
		this.trades = trades;
	}

	@Override
	public String toString() {
		return "Stock Details: StockType: " + this.getType().name() + " lastDividend: " + this.getLastDividend()
				+ " fixedDividend: " + this.getFixedDividend() + " parValue: " + this.getParValue() + "\n"
				+ this.getTrades();
	}

}
