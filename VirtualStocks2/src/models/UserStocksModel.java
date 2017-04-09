package models;

import java.sql.Timestamp;
import java.text.DecimalFormat;

public class UserStocksModel {
	String stockTicker;
	double stockPrice;
	Timestamp date;
	int numStocks;
	DecimalFormat decimalFormatter =  new DecimalFormat("#.##");
	
	public UserStocksModel(String stockTicker, double stockPrice, Timestamp date, int numStocks){
		this.stockTicker = stockTicker;
		this.stockPrice = stockPrice;
		this.date = date;
		this.numStocks = numStocks;
	}
	public String getStockTicker(){
		return stockTicker.toUpperCase();
	}
	public double getStockPrice(){
		return Double.parseDouble(decimalFormatter.format(stockPrice));
	}
}
