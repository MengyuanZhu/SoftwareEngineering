/**
 * SQLDatabase is the database helper service class that establishes
 * connection to the SQLite database as well as maintains the CRUD
 * operations necessary for database manipulation.
 * Some features of this class are:
 * <ul>
 * <li>Create a database if non existent.
 * <li>Create user table if non existent.
 * <li>Create Transaction table if non existent.
 * <li>Establish connection as well as disconnect to the database.
 * <li>Insert new users to the users table.
 * <li>Insert new transactions to the transactions table.
 * </ul>
 *
 * @author      SungJae Kim
 */

package helpers;
import java.sql.*;

import models.UserModel;

public class DatabaseHelper {
	private static final String DATABASE_NAME = "portfolio.db";
	Connection connection = null;
	Statement stmt = null;
	
	public void connect(){
		try{
			Class.forName("org.sqlite.JDBC");
			connection = DriverManager.getConnection("jdbc:sqlite:" + DATABASE_NAME);
		} catch(Exception e){
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		System.out.println("Connection successful");
		//TESTING:
		createUsersTable();
		createTransactionsTable();
		UserModel user = new UserModel();
		user.setFirstName("Jay");
		user.setLastName("Kim");
		user.setPassword("password");
		user.setUserName("jaykim");
		user.setEmail("jaykim@test.com");
		user.setTotalStockAmount(350);
		addUserToDb(user);
	}
	public void createUsersTable(){
		try{
			stmt = connection.createStatement();
			String sql = "CREATE TABLE IF NOT EXISTS Users" + 
						 "(Id 			INTEGER		 PRIMARY KEY		AUTOINCREMENT," + 
						 " Firstname 	TEXT					NOT NULL," +
						 " Lastname		TEXT					NOT NULL," +
						 " Username		TEXT					NOT NULL," +
						 " Password		TEXT					NOT NULL," +
						 " TotalAmount	DOUBLE					NOT NULL," +
						 " Email		TEXT					NOT NULL" +
						 ");";
			stmt.executeUpdate(sql);
			stmt.close();
		} catch(Exception e){
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		System.out.println("Table creation successful");
	}
	public void createTransactionsTable(){
		try{
			stmt = connection.createStatement();
			String sql = "CREATE TABLE IF NOT EXISTS Transactions" +
						 "(Id 				INTEGER		 PRIMARY KEY		AUTOINCREMENT," +
						 " Price 			DOUBLE				NOT NULL," +
						 " Time				TIMESTAMP			NOT NULL," +
						 " UserId			INTEGER				NOT NULL," +
						 " NumberOfStocks	INTEGER				NOT NULL" +
						 ");";
			stmt.executeUpdate(sql);
			stmt.close();
		} catch(Exception e){
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
	}
	/**
	 * Adds user to the Users table.
	 *
	 * @param  user  a user object containing the user information
	 * @see         UserModel
	 */
	public void addUserToDb(UserModel user){
		try{
			Class.forName("org.sqlite.JDBC");
			connection = DriverManager.getConnection("jdbc:sqlite:" + DATABASE_NAME);
			String query = "INSERT INTO Users(Firstname, Lastname, Username, Password, TotalAmount, Email) " + 
						   "SELECT ?, ?, ?, ?, ?, ?" + 
						   "WHERE NOT EXISTS (SELECT * FROM Users WHERE Username = ?);";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, user.getFirstName());
			statement.setString(2, user.getLastName());
			statement.setString(3, user.getUserName());
			statement.setString(4, user.getPassword());
			statement.setDouble(5, user.getTotalStockAmount());
			statement.setString(6, user.getEmail());
			statement.setString(7, user.getUserName());
			statement.executeUpdate();
			connection.close();
		} catch(Exception e){
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		System.out.println("User added successfully");
	}
	/**
	 * Adds transaction to the Transactions table.
	 *
	 * @param  price  the price of the stock bought
	 * @param  date  the time of transaction
	 * @param  userId  the id of the user making the transaction
	 * @param  numStocks  the number of stocks bought
	 */
	public void addTransactionToDb(double price, Timestamp date, int userId, int numStocks){
		try{
			String query = "INSERT INTO Transactions(Price, Time, UserId, NumberOfStocks) " + 
						   "VALUES(?, ?, ?, ?);";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setDouble(1, price);
			statement.setTimestamp(2, date);
			statement.setInt(3, userId);
			statement.setInt(4, numStocks);
			statement.executeUpdate();
		} catch(Exception e){
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		System.out.println("Transaction added successfully");
	}
	public boolean authenticateUser(String username, String password){
		boolean found = false;
		try{
			Class.forName("org.sqlite.JDBC");
			connection = DriverManager.getConnection("jdbc:sqlite:" + DATABASE_NAME);
			String query = "SELECT EXISTS(SELECT 1 FROM Users WHERE Username = ? AND Password = ?);";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, username);
			statement.setString(2, password);
			ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                found = rs.getBoolean(1);
            }
            connection.close();
		} catch(Exception e){
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		return found;
	}
	public boolean checkExistingUsername(String username){
		boolean found = false;
		try{
			Class.forName("org.sqlite.JDBC");
			connection = DriverManager.getConnection("jdbc:sqlite:" + DATABASE_NAME);
			String query = "SELECT EXISTS(SELECT 1 FROM Users WHERE Username = ?);";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, username);
			ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                found = rs.getBoolean(1);
            }
            connection.close();
		} catch(Exception e){
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		return found;
	}
}
