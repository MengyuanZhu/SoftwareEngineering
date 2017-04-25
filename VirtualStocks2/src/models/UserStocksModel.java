
package models;

import java.sql.Timestamp;
import java.text.DecimalFormat;

/**
 * UserStocksModel is a class that holds the information on user's stock transaction.
 * 
 * @author      Hyeun Kang
 */
public class UserStocksModel {
	String stockTicker;
	double stockPrice;
	Timestamp date;
	int numStocks;
	DecimalFormat decimalFormatter =  new DecimalFormat("#.##");

	/**
	 * constructor for the UserStocksModel class that constructs user's transacted stock information such as stock ticker(symbol),
	 * purchased price, purchased time, and number of stocks
	 */	
	public UserStocksModel(String stockTicker, double stockPrice, Timestamp date, int numStocks){
		this.stockTicker = stockTicker;
		this.stockPrice = stockPrice;
		this.date = date;
		this.numStocks = numStocks;
	}
	
	/**
	 * getter for transacted stock ticker(symbol) and converts to upper-case
	 */
	public String getStockTicker(){
		return stockTicker.toUpperCase();
	}
	
	/**
	 * getter for transacted stock price and converts to double
	 */
	public double getStockPrice(){
		return Double.parseDouble(decimalFormatter.format(stockPrice));
	}
}
