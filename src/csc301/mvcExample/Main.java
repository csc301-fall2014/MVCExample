package csc301.mvcExample;

import java.math.BigDecimal;

import csc301.mvcExample.view.GUI;
import csc301.mvcExample.view.GUIEventListener;

public class Main {
	
	public static class DummyEventHandler implements GUIEventListener {
	
		@Override
		public void onPauseClicked() {
			System.out.println("Pause clicked");
		}

		@Override
		public void onResumeClicked() {
			System.out.println("Resume clicked");
		}
	}
	
	
	

	public static void main(String[] args) throws InterruptedException {
		GUI gui = new GUI();
		gui.addEventListener(new DummyEventHandler());
		gui.setVisible(true);
		
		BigDecimal price = new BigDecimal("296.65");
		for (int i = 0; i < 5; i++) {
			price = price.add(new BigDecimal(i));
			gui.onStockUpdate("AMZN", price, true, new BigDecimal("1.2"));
			Thread.sleep(1000);
		}
	}

	
}
