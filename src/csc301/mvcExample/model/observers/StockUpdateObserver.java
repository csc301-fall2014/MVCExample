package csc301.mvcExample.model.observers;

import csc301.mvcExample.model.Stock;

public interface StockUpdateObserver {

	public void onUpdate(Stock before, Stock after);
	
}
