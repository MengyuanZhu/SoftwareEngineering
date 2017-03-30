package virtualStock.model;
import java.util.*;
/**
 * @author jay
 *
 */
public class UserModel {
	private String userName;
	private String password; //Hash password later
	private String firstName;
	private String lastName;
	private String fullName;
	private String email;
	private int userId;
	private double totalStockAmount;
	private LinkedList<StockModel> stockPortfolio = new LinkedList<StockModel>();
	
	
	public String getUserName(){
		return userName;
	}
	public void setUserName(String userName){
		this.userName = userName;
	}
	public String getFirstName(){
		return firstName;
	}
	public void setFirstName(String firstName){
		this.firstName = firstName;
	}
	public String getLastName(){
		return lastName;
	}
	public void setLastName(String lastName){
		this.lastName = lastName;
	}
	public String getFullName(){
		fullName = firstName + " " + lastName;
		return fullName;
	}
	public int getUserId(){
		return userId;
	}
	public void setUserId(int userId){
		this.userId = userId;
	}
	public String getPassword(){
		return password;
	}
	public void setPassword(String password){
		this.password = password;
	}
	public LinkedList<StockModel> getPortfolioData(){
		return stockPortfolio;
	}
	public void buyStock(StockModel stock, int numOfStocks){
		stockPortfolio.add(stock);
		totalStockAmount += (stock.getPrice() * numOfStocks);
	}
	public double getTotalStockAmount(){
		return totalStockAmount;
	}
	public void setTotalStockAmount(double amount){
		this.totalStockAmount = amount;
	}
	public String getEmail(){
		return email;
	}
	public void setEmail(String email){
		this.email = email;
	}
}
