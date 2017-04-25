


package models;

/**
 * StockModel is a class that gets and sets various stock information.
 * The class includes functions:
 * <ul>
 * <li>get and set ticker
 * <li>get and set stock exchange
 * <li>get and set stock price
 * <li>get and set last trade time
 * <li>prints out all stock information
 * </ul>
 * @author      Hyeun Kang
 */
public class StockModel {
	private String t;
	private String e;
	private double l;
	private String lt;
	
	/**
	 * Getter for Ticker
	 */
	public String getTicker(){
		return t;
	}
	
	/**
	 * Setter for Ticker
	 */
	public void setTicker(String ticker){
		this.t = ticker;
	}
	
	/**
	 * getter for Stock exchange
	 */
	public String getExchange(){
		return e;
	}
	
	/**
	 * Setter for stock exchange
	 */

	public void setExchange(String exchange){
		this.e = exchange;
	}
	
	/**
	 * Getter for Price
	 */
	public double getPrice(){
		return l;
	}
	
	/**
	 * Setter for Price
	 */
	public void setPrice(int price){
		this.l = price;
	}
	
	/**
	 * Getter for last trade time
	 */
	public String getLastTradeTime(){
		return lt;
	}
	
	/**
	 * Setter for last trade time
	 */
	public void setLastTradeTime(String lastTradeTime){
		this.lt = lastTradeTime;
	}
	
	/**
	 * Prints out stock information
	 */
	public void printInformation(){
		System.out.println("Ticker: " + getTicker());
		System.out.println("Exchange: " + getExchange());
		System.out.println("Last Price: "+ getPrice());
		System.out.println("Last Trade Time: " + getLastTradeTime());
	}
}
