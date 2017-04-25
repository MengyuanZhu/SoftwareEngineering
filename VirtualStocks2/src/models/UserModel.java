

package models;
import java.util.*;

/**
 * UserModel is a class that holds all information about the user.
 * The class includes functions:
 * <ul>
 * <li>get and set user name
 * <li>get and set first name
 * <li>get and set last name
 * <li>get full name
 * <li>get and set user ID
 * <li>get and set user password
 * <li>get and set email
 * <li>get and set total amount
 * <li>get and set stock amount
 * <li>buy stock function adds and updates stock to the database
 * </ul>
 * @author      Hyeun Kang
 */
public class UserModel {
	private String userName;
	private String password; //Hash password later
	private String firstName;
	private String lastName;
	private String fullName;
	private String email;
	private int userId;
	private double totalAmount;
	private double stockAmount;
	private LinkedList<StockModel> stockPortfolio = new LinkedList<StockModel>();
	
	/**
	 * Getter for user name
	 */
	public String getUserName(){
		return userName;
	}
	
	/**
	 * Setter for user name
	 */
	public void setUserName(String userName){
		this.userName = userName;
	}
	
	/**
	 * Getter for First name
	 */
	public String getFirstName(){
		return firstName;
	}
	
	/**
	 * Getter for First name
	 */
	public void setFirstName(String firstName){
		this.firstName = firstName;
	}
	
	/**
	 * Getter for Last name
	 */
	public String getLastName(){
		return lastName;
	}
	
	/**
	 * Setter for Last name
	 */
	public void setLastName(String lastName){
		this.lastName = lastName;
	}
	
	/**
	 * Getter for full name
	 */
	public String getFullName(){
		fullName = firstName + " " + lastName;
		return fullName;
	}
	
	/**
	 * Getter for user Id
	 */
	public int getUserId(){
		return userId;
	}
	
	/**
	 * Getter for user ID
	 */
	public void setUserId(int userId){
		this.userId = userId;
	}
	
	/**
	 * Getter for password
	 */
	public String getPassword(){
		return password;
	}
	
	/**
	 * Setter for password
	 */
	public void setPassword(String password){
		this.password = password;
	}
	
	/**
	 * gets data from database
	 */
	public LinkedList<StockModel> getPortfolioData(){
		return stockPortfolio;
	}
	
	/**
	 * adds and update the total number and value of the stocks the user possesses
	 * in the database
	 */
	public void buyStock(StockModel stock, int numOfStocks){
		stockPortfolio.add(stock);
		totalAmount += (stock.getPrice() * numOfStocks);
	}
	
	/**
	 * Getter for Total amount
	 */
	public double getTotalAmount(){
		return totalAmount;
	}
	
	/**
	 * Setter for Total amount
	 */
	public void setTotalAmount(double amount){
		this.totalAmount = amount;
	}
	
	/**
	 * Getter for Stock amount
	 */
	public double getStockAmount(){
		return stockAmount;
	}
	
	/**
	 * Setter for Stock amount
	 */
	public void setStockAmount(double amount){
		this.stockAmount = amount;
	}
	
	/**
	 * Getter for user email
	 */
	public String getEmail(){
		return email;
	}
	
	/**
	 * Setter for user email
	 */
	public void setEmail(String email){
		this.email = email;
	}
}
