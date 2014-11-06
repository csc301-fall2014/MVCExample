package csc301.mvcExample.service;

import java.math.BigDecimal;

import csc301.mvcExample.model.Stock;

public interface StockDAO {

	public void createOrUpdate(String stockId, BigDecimal price);
	public Stock getStock(String stockId);
	
}
