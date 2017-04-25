

package controllers;

import javafx.fxml.FXML;

import java.text.DecimalFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import helpers.DatabaseHelper;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.text.Text;

/**
 * BankAccountController is a class that connects the BankAccountView to the database.
 * The class includes functions:
 * <ul>
 * <li>Get User's total funds from the database
 * <li>Withdraw money from bank account
 * <li>Deposit money to bank account
 * <li>Validate the format of amount the user inputs
 * </ul>
 * @author      Hyeun Kang
 */
public class BankAccountController {

	int userId;
	DatabaseHelper dbHelper = new DatabaseHelper();
	DecimalFormat decimalFormatter =  new DecimalFormat("#.##");
	@FXML TextField amountTextField;
	@FXML Text amountText;
	
	public static final Pattern VALID_AMOUNT_REGEX = Pattern.compile("^(\\$?\\d{1,3}(,?\\d{3})?(\\.\\d\\d?)?|\\(\\$?\\d{1,3}(,?\\d{3})?(\\.\\d\\d?)?\\))$", Pattern.CASE_INSENSITIVE);
	
	public void setUserId(int userId){
		this.userId = userId;
		double totalFunds = dbHelper.getUserTotalFunds(this.userId);
		amountText.setText("$" + decimalFormatter.format(totalFunds));
	}
	/**
	 * Withdraws money from user's account in database
	 * if the format does not pass validAmount method, prompt user with error window
	 */
	@FXML public void handleWithdrawButton(ActionEvent event) {
		if(validateAmount(amountTextField.getText())){
			double amount = Double.parseDouble(amountTextField.getText());
			amount *= -1;
			dbHelper.addBankTransactionToDb(this.userId, amount);
			double totalFunds = dbHelper.getUserTotalFunds(this.userId);
			amountText.setText("$" + decimalFormatter.format(totalFunds));
		}
		else{
			Alert alert = new Alert(AlertType.WARNING);
	    	alert.setTitle("Warning Dialog");
	    	alert.setHeaderText("Error:");
	    	alert.setContentText("Incorrect amount.\nPlease try again.");
	    	alert.showAndWait();
		}
	}

	/**
	 * Adds money to user's account in database
	 * if the format of your input does not pass the validAmount method, 
	 * prompt user with error window
	 */
	@FXML public void handleDepositButton(ActionEvent event) {
		if(validateAmount(amountTextField.getText())){
			double amount = Double.parseDouble(amountTextField.getText());
			dbHelper.addBankTransactionToDb(this.userId, amount);
			double totalFunds = dbHelper.getUserTotalFunds(this.userId);
			amountText.setText("$" + decimalFormatter.format(totalFunds));
		}
		else{
			Alert alert = new Alert(AlertType.WARNING);
	    	alert.setTitle("Warning Dialog");
	    	alert.setHeaderText("Error:");
	    	alert.setContentText("Incorrect amount.\nPlease try again.");
	    	alert.showAndWait();
		}
	}
	
	/**
	 * Validates if the user input matches the VALID_AMOUNT_REGEX
	 */
	public static boolean validateAmount(String amount) {
        Matcher matcher = VALID_AMOUNT_REGEX .matcher(amount);
        return matcher.find();
	}

}
