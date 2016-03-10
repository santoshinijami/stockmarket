package com.jpmc.examples.stockmarket;

import com.jpmc.examples.stockmarket.enums.TradeTypeEnum;

/**
 * This class defines the parameters for each Trade
 * 
 * @author santoshini.jami
 *
 */
public class Trade {
	private int quantityOfShares;
	private Double price;
	private TradeTypeEnum type;

	public Trade(TradeTypeEnum type, int quantityOfShares, Double price) {
		this.setQuantityOfShares(quantityOfShares);
		this.setType(type);
		this.setPrice(price);
	}

	public int getQuantityOfShares() {
		return quantityOfShares;
	}

	public void setQuantityOfShares(int quantityOfShares) {
		this.quantityOfShares = quantityOfShares;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public TradeTypeEnum getType() {
		return type;
	}

	public void setType(TradeTypeEnum type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "Trade Details: TradeType: " + this.getType().name() + " quantityOfShares: " + this.getQuantityOfShares()
				+ " price: " + this.getPrice();
	}
}
