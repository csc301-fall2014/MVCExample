package csc301.mvcExample;

import java.math.BigDecimal;

import csc301.mvcExample.model.Stock;


public class Main {

	public static void main(String[] args) {
		Stock stock = new Stock("AMZN", new BigDecimal("295.73"));
		System.out.println(stock);
	}
}
