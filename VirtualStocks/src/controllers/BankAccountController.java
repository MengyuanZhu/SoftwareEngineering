package controllers;

import javafx.fxml.FXML;

import java.text.DecimalFormat;

import helpers.DatabaseHelper;
import javafx.event.ActionEvent;
import javafx.scene.control.TextField;
import models.UserModel;
import javafx.scene.text.Text;

public class BankAccountController {

	int userId;
	DatabaseHelper dbHelper = new DatabaseHelper();
	DecimalFormat decimalFormatter =  new DecimalFormat("#.##");
	@FXML TextField amountTextField;
	@FXML Text amountText;
	
	public void setUserId(int userId){
		this.userId = userId;
		double totalFunds = dbHelper.getUserTotalFunds(this.userId);
		amountText.setText("$" + decimalFormatter.format(totalFunds));
	}

	@FXML public void handleWithdrawButton(ActionEvent event) {
		double amount = Double.parseDouble(amountTextField.getText());
		amount *= -1;
		dbHelper.addBankTransactionToDb(this.userId, amount);
		double totalFunds = dbHelper.getUserTotalFunds(this.userId);
		amountText.setText("$" + decimalFormatter.format(totalFunds));
	}

	@FXML public void handleDepositButton(ActionEvent event) {
		double amount = Double.parseDouble(amountTextField.getText());
		dbHelper.addBankTransactionToDb(this.userId, amount);
		double totalFunds = dbHelper.getUserTotalFunds(this.userId);
		amountText.setText("$" + decimalFormatter.format(totalFunds));
	}

}
