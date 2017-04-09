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
	
	public static boolean validateAmount(String amount) {
        Matcher matcher = VALID_AMOUNT_REGEX .matcher(amount);
        return matcher.find();
	}

}
