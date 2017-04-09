package controllers;

import javafx.fxml.FXML;
import javafx.stage.Stage;
import models.UserStocksModel;
import models.YahooStockModel;

import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.util.ArrayList;

import helpers.DatabaseHelper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Series;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;

public class BuyStockController {

	@FXML Button confirmButton;
	@FXML Button cancelButton;
	@FXML Text stockPriceText;
	@FXML Text numStocksText;
	@FXML Text totalAmountText;
	@FXML Text totalFundsText;
	@FXML Text newFundsText;
	@FXML TableView<UserStocksModel> stocksTableView;
	@FXML TableColumn<UserStocksModel, String> stocksColumn;
	@FXML TableColumn<UserStocksModel, Double> priceColumn;
	double totalAmount;
	double stockPrice;
	int numStocks;
	int userId;
	YahooStockModel stock;
	DecimalFormat decimalFormatter =  new DecimalFormat("#.##");
	DatabaseHelper dbHelper = new DatabaseHelper();
	
	public void setInformation(int userId, YahooStockModel stock, int numStocks){
		this.stock = stock;
		this.stockPrice = stock.getPreviousClose();
		this.totalAmount = stockPrice * numStocks;
		this.userId = userId;
		this.numStocks = numStocks;
		double userTotalFunds = dbHelper.getUserTotalFunds(userId);
		double newFunds = userTotalFunds - totalAmount;
		stockPriceText.setText("$" + Double.toString(stockPrice));
		numStocksText.setText(Integer.toString(numStocks));
		totalAmountText.setText("$" + decimalFormatter.format(totalAmount));
		totalFundsText.setText("$" + decimalFormatter.format(userTotalFunds));
		newFundsText.setText("$" + decimalFormatter.format(newFunds));		
	}

	@FXML public void handleConfirmButton(ActionEvent event) {
		dbHelper.addBankTransactionToDb(userId, totalAmount * -1);
		dbHelper.addTransactionToDb(stock.getTicker(), stockPrice, userId, numStocks);
		stocksTableView.getItems().clear();
		ArrayList<UserStocksModel> userStocks = dbHelper.getUserStocks(userId);
		ObservableList<UserStocksModel> data = FXCollections.observableArrayList(userStocks);
		stocksTableView.setItems(data);
		stocksColumn.setCellValueFactory(new PropertyValueFactory<UserStocksModel, String>("stockTicker"));
		priceColumn.setCellValueFactory(new PropertyValueFactory<UserStocksModel, Double>("stockPrice"));
	    Stage stage = (Stage) confirmButton.getScene().getWindow();
	    stage.close();
	}

	@FXML public void handleCancelButton(ActionEvent event) {
	    Stage stage = (Stage) cancelButton.getScene().getWindow();
	    stage.close();
	}

}
