package csc301.mvcExample.model.observers;

public interface StockUpdateObservable {

	public void addObserver(StockUpdateObserver o);
	public void removeObserver(StockUpdateObserver o);
	
}
