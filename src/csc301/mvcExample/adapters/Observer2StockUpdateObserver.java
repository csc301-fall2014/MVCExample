package csc301.mvcExample.adapters;

import java.util.Observable;
import java.util.Observer;

import csc301.mvcExample.model.Stock;
import csc301.mvcExample.model.observers.StockUpdateObserver;

/**
 * This adapter can be attached as an observer to a Stock instance.
 * 
 * It keeps track of the last stock-price it has seen, and whenever it 
 * observes a change, it passes the old and current prices to 
 * its observer's. 
 */
public class Observer2StockUpdateObserver implements Observer {

	private Stock lastSeen;
	StockUpdateObserver observer;
	
	public Observer2StockUpdateObserver(StockUpdateObserver observer) {
		this.observer = observer;
	}
	
	
	@Override
	public void update(Observable observable, Object additionalArgument) {
		Stock stock = (Stock) observable;
		observer.onUpdate(lastSeen, stock);
		lastSeen = new Stock(stock.getId(), stock.getPrice());
	}
		
}
