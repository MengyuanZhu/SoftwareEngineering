package virtualStock.model;
import java.util.Date;
/**
 * @author jay
 *
 */
public class TransactionModel {
	private int transactionId;
	private double stockPrice;
	private Date boughtDate;
	private int userId;
	private  int numBought;
	
	public int getTransactionId(){
		return transactionId;
	}
	public void setTransactionId(int id){
		this.transactionId = id;
	}
	public double getStockPrice(){
		return stockPrice;
	}
	public void setStockPrice(double price){
		this.stockPrice = price;
	}
	public Date getBoughtDate(){
		return boughtDate;
	}
	public void setBoughtDate(Date date){
		this.boughtDate = date;
	}
	public int getUserId(){
		return userId;
	}
	public void setUserId(int id){
		this.userId = id;
	}
	public int getNumBought(){
		return numBought;
	}
	public void setNumBought(int num){
		this.numBought = num;
	}
}
