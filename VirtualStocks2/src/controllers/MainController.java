package controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import models.UserModel;
import models.UserStocksModel;
import models.YahooStockModel;

import java.io.IOException;
import java.util.ArrayList;

import helpers.DatabaseHelper;
import helpers.YahooDataParser;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Series;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;

public class MainController {
	@FXML private MenuItem closeMenu;
	@FXML private MenuItem logoutMenu;
	@FXML private Text welcomeText;
	@FXML private Text userInfo;
	@FXML private TextField stockSearchText;
	private String username;
	private UserModel user;
	private DatabaseHelper dbHelper = new DatabaseHelper();
	private YahooStockModel stock;
	@FXML private LineChart<String, Double> stockChart;
	@FXML NumberAxis priceAxis;
	@FXML Text stockInfoText;
	@FXML Text stockNameText;
	@FXML TextField amountField;
	@FXML TableView<UserStocksModel> stocksTableView;
	@FXML TableColumn<UserStocksModel, String> stocksColumn;
	@FXML TableColumn<UserStocksModel, Double> priceColumn;

	public void setUsername(String username){
		this.username = username;
		user = dbHelper.getUserInfo(dbHelper.getUserId(username));
		welcomeText.setText("Welcome, " + user.getFullName());
		userInfo.setText("User ID: " + user.getUserId() + 
				"\n\nUsername: " + user.getUserName() + 
				"\n\nFirst Name: " + user.getFirstName() + 
				"\n\nLast Name: " + user.getLastName() + 
				"\n\nEmail: " + user.getEmail());
		ArrayList<UserStocksModel> userStocks = dbHelper.getUserStocks(user.getUserId());
		ObservableList<UserStocksModel> data = FXCollections.observableArrayList(userStocks);
		stocksTableView.setItems(data);
		stocksColumn.setCellValueFactory(new PropertyValueFactory<UserStocksModel, String>("stockTicker"));
		priceColumn.setCellValueFactory(new PropertyValueFactory<UserStocksModel, Double>("stockPrice"));
	}
	@FXML public void handleMenuClose(ActionEvent event) {
	    Stage stage = (Stage) closeMenu.getParentPopup().getOwnerWindow().getScene().getWindow();
	    stage.close();
	}
	@FXML public void handleLogout(ActionEvent event) {
		Stage stage = (Stage) logoutMenu.getParentPopup().getOwnerWindow().getScene().getWindow();
		stage.close();
	     try{
	            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../Views/LoginView.fxml"));
	            Parent root = (Parent) fxmlLoader.load();
	            stage = new Stage();
	            stage.initModality(Modality.APPLICATION_MODAL);
	            stage.setTitle("VirtualStocks");
	            stage.setScene(new Scene(root));  
	            stage.show();
	        
		     } catch (IOException e) {
		         e.printStackTrace();
		     }
	}
	@FXML public void handleSearchStockText(MouseEvent event) {
		stockSearchText.setText("");
	}
	@FXML public void handleStockSearch(ActionEvent event) {
//	     try{
//		    	YahooDataParser yahooParser = new YahooDataParser();
//		    	YahooStockModel stock = yahooParser.getJSON(stockSearchText.getText());
//		    	if(stock.getCompanyName() != null){
//		    		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../Views/StockView.fxml"));
//		            Parent root = (Parent) fxmlLoader.load();
//		            Stage stage = new Stage();
//		            stage.setTitle("VirtualStocks");
//		            stage.setScene(new Scene(root));  
//		            StockController stockController = fxmlLoader.<StockController>getController();
//		            stockController.setStock(stock);
//		            stage.show();
//		    	}
//		    	else{
//			    	Alert alert = new Alert(AlertType.WARNING);
//			    	alert.setTitle("Warning Dialog");
//			    	alert.setHeaderText("Error:");
//			    	alert.setContentText("Invalid symbol.\nPlease try again.");
//			    	alert.showAndWait();
//		    	}
//	        
//	     } catch (IOException e) {
//	         e.printStackTrace();
//	     }
		YahooDataParser yahooParser = new YahooDataParser();
		stock = yahooParser.getJSON(stockSearchText.getText());
		if(stock.getCompanyName() != null){
			stockNameText.setText(stock.getCompanyName() + " ("+ stock.getTicker().toUpperCase() + ")");
			stockInfoText.setText("Exchange: " + stock.getExchangeName() + 
								  "\n\nCurrency: " + stock.getCurrency() +
								  "\n\nLast Closed Price: " + stock.getSeries().get(stock.getSeries().size() - 1).getClose());
			stockChart.setTitle(stock.getSeries().get(0).getTimeStamp().toString().split(" ")[0]);
			Series<String, Double> series = new XYChart.Series<>();
			series.setName("");
			double max = stock.getSeries().get(0).getClose();
			double min = stock.getSeries().get(0).getClose();
			for(int i = 0; i < stock.getSeries().size(); i+=15){
				series.getData().add(new XYChart.Data<String, Double>(stock.getSeries().get(i).getTimeStamp().toString().split(" ")[1].substring(0, 5), stock.getSeries().get(i).getClose()));
				if(stock.getSeries().get(i).getClose() > max){
					max = stock.getSeries().get(i).getClose();
				}
				if(stock.getSeries().get(i).getClose() < min){
					min = stock.getSeries().get(i).getClose();
				}
			}
			priceAxis.setAutoRanging(false);
			priceAxis.setLowerBound(min - 0.1);
			priceAxis.setUpperBound(max + 0.1);
			priceAxis.setTickUnit(0.01);
			stockChart.setLegendVisible(false);
			if(stockChart.getData().isEmpty()){
				stockChart.getData().add(series);
			}
			else{
				stockChart.getData().remove(0);
				stockChart.getData().add(series);
			}
		}
    	else{
	    	Alert alert = new Alert(AlertType.WARNING);
	    	alert.setTitle("Warning Dialog");
	    	alert.setHeaderText("Error:");
	    	alert.setContentText("Invalid symbol.\nPlease try again.");
	    	alert.showAndWait();
		}
	}
	@FXML public void handleAbout(ActionEvent event) {
		//TODO Make into a popup window.
		System.out.println("Group: Batman");
		System.out.println("Members: \nMengyuan Zhu (Group Leader) \nSungJae Kim \nHyeun Kang \nSharon Kim");
	}
	@FXML public void handleEdit(ActionEvent event) {
		try{
	        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../Views/EditAccountView.fxml"));
	        Parent root = (Parent) fxmlLoader.load();
	        Stage stage = new Stage();
	        stage.initModality(Modality.APPLICATION_MODAL);
	        stage.setTitle("VirtualStocks");
	        stage.setScene(new Scene(root));  
	        stage.setResizable(false);
	        EditAccountController editController = fxmlLoader.<EditAccountController>getController();
	        editController.setUser(user);
	        stage.show();
		} catch (IOException e) {
	         e.printStackTrace();
	    }
	}
	@FXML public void handleBank(ActionEvent event) {
		try{
	        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../Views/BankAccountView.fxml"));
	        Parent root = (Parent) fxmlLoader.load();
	        Stage stage = new Stage();
	        stage.initModality(Modality.APPLICATION_MODAL);
	        stage.setTitle("VirtualStocks");
	        stage.setScene(new Scene(root));  
	        stage.setResizable(false);
	        BankAccountController bankController = fxmlLoader.<BankAccountController>getController();
	        bankController.setUserId(user.getUserId());
	        stage.show();
		} catch (IOException e) {
	         e.printStackTrace();
	    }
	}
	@FXML public void handleBuy(ActionEvent event) {
		if(stock != null){
			if(amountField.getText().matches("^\\d{1,3}$")){
				double currentFunds = dbHelper.getUserTotalFunds(user.getUserId());
				double stockTotal = Integer.parseInt(amountField.getText()) * stock.getPreviousClose();
				double newFunds = currentFunds - stockTotal;
				if(newFunds >= 0){
					try{
				        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../Views/BuyStockView.fxml"));
				        Parent root = (Parent) fxmlLoader.load();
				        Stage stage = new Stage();
				        stage.initModality(Modality.APPLICATION_MODAL);
				        stage.setTitle("VirtualStocks");
				        stage.setScene(new Scene(root));  
				        stage.setResizable(false);
				        BuyStockController buyStockController = fxmlLoader.<BuyStockController>getController();
				        buyStockController.setInformation(user.getUserId(), stock, Integer.parseInt(amountField.getText()));
				        buyStockController.stocksTableView = stocksTableView;
				        buyStockController.priceColumn = priceColumn;
				        buyStockController.stocksColumn = stocksColumn;
				        stage.show();
					} catch (IOException e){
						e.printStackTrace();
					}
				}
				else{
			    	Alert alert = new Alert(AlertType.WARNING);
			    	alert.setTitle("Warning Dialog");
			    	alert.setHeaderText("Error:");
			    	alert.setContentText("Insufficient funds.\nPlease try again.");
			    	alert.showAndWait();
				}
			}
			else{
		    	Alert alert = new Alert(AlertType.WARNING);
		    	alert.setTitle("Warning Dialog");
		    	alert.setHeaderText("Error:");
		    	alert.setContentText("Not a valid amount.\nPlease try again.");
		    	alert.showAndWait();
			}
		}
    	else{
	    	Alert alert = new Alert(AlertType.WARNING);
	    	alert.setTitle("Warning Dialog");
	    	alert.setHeaderText("Error:");
	    	alert.setContentText("Please search for a stock.\nPlease try again.");
	    	alert.showAndWait();
		}
	}
}
