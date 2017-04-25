
package models;
import java.util.Date;


/**
 * TransactionModel is a class that stores transaction information.
 * The class includes functions:
 * <ul>
 * <li>get and set transaction ID
 * <li>get and set stock price
 * <li>get and set purchase date
 * <li>get and set user ID of purchaser
 * <li>get and set number of stocks purchased.
 * </ul>
 * @author      Hyeun Kang
 */
public class TransactionModel {
	private int transactionId;
	private double stockPrice;
	private Date boughtDate;
	private int userId;
	private  int numBought;
	
	/**
	 * Getter for transaction ID
	 */
	public int getTransactionId(){
		return transactionId;
	}
	
	/**
	 * Setter for transaction ID
	 */
	public void setTransactionId(int id){
		this.transactionId = id;
	}
	
	/**
	 * Getter for stock price of the stock transacted
	 */
	public double getStockPrice(){
		return stockPrice;
	}
	
	/**
	 * Setter for stock price of the stock transacted
	 */
	public void setStockPrice(double price){
		this.stockPrice = price;
	}
	
	/**
	 * Getter for purchase date and time
	 */
	public Date getBoughtDate(){
		return boughtDate;
	}
	
	/**
	 * Setter for purchase date and time
	 */
	public void setBoughtDate(Date date){
		this.boughtDate = date;
	}
	
	/**
	 * Getter for user ID of purchaser
	 */
	public int getUserId(){
		return userId;
	}
	
	/**
	 * Setter for user ID of purchaser
	 */
	public void setUserId(int id){
		this.userId = id;
	}
	
	/**
	 * Getter for number of stocks purchased
	 */
	public int getNumBought(){
		return numBought;
	}
	
	/**
	 * Setter for number of stocks purchased
	 */
	public void setNumBought(int num){
		this.numBought = num;
	}
}
