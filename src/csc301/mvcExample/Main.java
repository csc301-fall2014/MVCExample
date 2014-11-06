package csc301.mvcExample;

import csc301.mvcExample.service.SimpleStockMarket;
import csc301.mvcExample.view.GUI;

public class Main {

	public static void main(String[] args) {
		SimpleStockMarket stockMarket = new SimpleStockMarket();
		GUI gui = new GUI();
		
		Controller controller = new Controller(stockMarket, gui, stockMarket);
		gui.addEventListener(controller);
		gui.setVisible(true);
		controller.start();
	}
}
