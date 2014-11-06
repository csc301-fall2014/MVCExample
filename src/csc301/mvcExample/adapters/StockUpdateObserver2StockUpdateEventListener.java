package csc301.mvcExample.adapters;

import java.math.BigDecimal;

import csc301.mvcExample.model.Stock;
import csc301.mvcExample.model.observers.StockUpdateObserver;
import csc301.mvcExample.view.StockUpdateEventListener;

public class StockUpdateObserver2StockUpdateEventListener implements StockUpdateObserver {

	private StockUpdateEventListener listener;
	
	public StockUpdateObserver2StockUpdateEventListener(StockUpdateEventListener listener) {
		this.listener = listener;
	}
	
	
	@Override
	public void onUpdate(Stock before, Stock after) {
		boolean hasGoneUp = true;
		BigDecimal percentChanged = new BigDecimal(0);
	
		if(before != null){
			hasGoneUp = before.getPrice().compareTo(after.getPrice()) < 0;
			percentChanged = after.getPrice().divide(before.getPrice(), 4, BigDecimal.ROUND_HALF_UP)
					.subtract(new BigDecimal(1)).abs()
					.movePointRight(2).setScale(2);
		}
		
		listener.onStockUpdate(after.getId(), after.getPrice(), hasGoneUp, percentChanged);
	}

	
	
}
