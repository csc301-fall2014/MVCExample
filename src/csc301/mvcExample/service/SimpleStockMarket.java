package csc301.mvcExample.service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import csc301.mvcExample.adapters.Observer2StockUpdateObserver;
import csc301.mvcExample.model.Stock;
import csc301.mvcExample.model.observers.StockUpdateObservable;
import csc301.mvcExample.model.observers.StockUpdateObserver;

/**
 * An in-memory implementation of a stock market.
 * Notice that this class implements 3 different interfaces:
 * 
 * 1. StockDAO - It stores and gives access to Stock instances.
 * 2. StockUpdateObserver - It observes price updates for each stock.
 * 3. StockUpdateObservable - It allows others to observe price updates for all stocks.
 *    That is, application code doesn't need to observe each individual stock, it can
 *    just observe this SimpleStockMarketImpl.   
 */
public class SimpleStockMarket implements StockDAO, StockUpdateObserver, StockUpdateObservable {

	
	Map<String, Stock> id2stock = new HashMap<String, Stock>();
	Set<StockUpdateObserver> observers = new HashSet<StockUpdateObserver>();

	
	@Override
	public void createOrUpdate(String stockId, BigDecimal price) {
		if(id2stock.containsKey(stockId)){	
			id2stock.get(stockId).setPrice(price);
		} else {
			// Create a new Stock, and make sure we observe it for updates
			Stock stock = new Stock(stockId, price);
			stock.addObserver(new Observer2StockUpdateObserver(this));			
			id2stock.put(stock.getId(), stock);
		}
	}




	@Override
	public Stock getStock(String stockId) {
		if(id2stock.containsKey(stockId)){	
			return id2stock.get(stockId);
		} else {
			return null;
		}
	}
	

	
	@Override
	public void addObserver(StockUpdateObserver observer) {
		observers.add(observer);
	}

	@Override
	public void removeObserver(StockUpdateObserver observer) {
		observers.remove(observer);
	}

	
	@Override
	public void onUpdate(Stock before, Stock after) {
		for(StockUpdateObserver observer : observers){
			observer.onUpdate(before, after);
		}
	}



}
