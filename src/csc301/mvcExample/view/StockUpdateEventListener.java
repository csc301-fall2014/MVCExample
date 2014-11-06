package csc301.mvcExample.view;

import java.math.BigDecimal;

/**
 * Our view (i.e. GUI) wants to listen to stock-update events, but we don't want
 * the view to depend on the model.
 * 
 * This interface defines how the view wants to get its stock-update events.
 */
public interface StockUpdateEventListener {

	public void onStockUpdate(String stockId, BigDecimal currentPrice, boolean hasGoneUp, BigDecimal percentage);
	
}
