package csc301.mvcExample;

import java.math.BigDecimal;
import java.util.Observable;
import java.util.Observer;

import csc301.mvcExample.model.Stock;


public class Main {

		
	public static void main(String[] args) {
		Stock stock = new Stock("AMZN", new BigDecimal("295.73"));
	
		// We create an implementation of the Observer interface on the spot.
		// For more info, search for "java anonymous class" online. 
		stock.addObserver(new Observer() {	
			@Override
			public void update(Observable o, Object arg) {
				System.out.println(o);
			}
		});
		
		
		stock.setPrice(new BigDecimal("280.3"));
		stock.setPrice(new BigDecimal("297.42"));
		stock.setPrice(new BigDecimal("295.99"));
	}
}
