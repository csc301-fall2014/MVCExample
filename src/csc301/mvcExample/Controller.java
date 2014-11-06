package csc301.mvcExample;

import java.math.BigDecimal;
import java.util.Random;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import csc301.mvcExample.adapters.StockUpdateObserver2StockUpdateEventListener;
import csc301.mvcExample.model.observers.StockUpdateObservable;
import csc301.mvcExample.service.StockDAO;
import csc301.mvcExample.view.GUIEventListener;
import csc301.mvcExample.view.StockUpdateEventListener;


/**
 * The controller connects between the model and view.
 *
 * Whenever it observes a stock update in the model, it triggers
 * a stock-update-event that the view can act on.
 * 
 * To make things interesting, this controller also updates stocks
 * randomly, once per second.
 */
public class Controller implements GUIEventListener, Runnable{
	
	// Constants
	public static final String STOCK_ID_AMZN = "AMZN";
	public static final String STOCK_ID_GOOG = "GOOG";
	
	
	// Instance variables used to repeatedly update the stocks
	private StockDAO stockDAO;
	ScheduledExecutorService taskScheduler;
	private ScheduledFuture<?> scheduledTask;
	private Random rand;
	

	public Controller(StockUpdateObservable stockMarket, StockUpdateEventListener view, StockDAO stockDAO) {
		// Hook up the Model and the View
		stockMarket.addObserver(new StockUpdateObserver2StockUpdateEventListener(view));
		
		this.stockDAO = stockDAO;
		this.taskScheduler = new ScheduledThreadPoolExecutor(1);
		this.rand = new Random();
	}
	
	
	
	public void start(){		
		stockDAO.createOrUpdate(STOCK_ID_AMZN, new BigDecimal("296.5"));
		stockDAO.createOrUpdate(STOCK_ID_GOOG, new BigDecimal("545.92"));
		onResumeClicked();
	}
	

	@Override
	public void onPauseClicked() {
		if(scheduledTask != null){
			scheduledTask.cancel(false);
		}
	}


	@Override
	public void onResumeClicked() {
		scheduledTask = taskScheduler.scheduleAtFixedRate(this, 0, 1, TimeUnit.SECONDS);
	}
	

	@Override
	public void run() {
		// "Randomly" update the price of one of the two stocks ...
		String id = rand.nextBoolean() ? STOCK_ID_AMZN : STOCK_ID_GOOG;
		BigDecimal currentPrice = stockDAO.getStock(id).getPrice();
		double factor = 1 + (0.1 * (rand.nextDouble() - 0.5)); // Random number between 0.95 and 1.05
		stockDAO.createOrUpdate(id, currentPrice.multiply(new BigDecimal(factor)));
	}

}
