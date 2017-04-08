package controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import models.UserModel;
import models.YahooStockModel;

import java.io.IOException;

import helpers.DatabaseHelper;
import helpers.YahooDataParser;
import javafx.event.ActionEvent;
import javafx.scene.input.MouseEvent;

public class MainController {
	@FXML private MenuItem closeMenu;
	@FXML private MenuItem logoutMenu;
	@FXML private Text welcomeText;
	@FXML private Text userInfo;
	@FXML private TextField stockSearchText;
	private String username;
	private UserModel user;
	private DatabaseHelper dbHelper = new DatabaseHelper();

	public void setUsername(String username){
		this.username = username;
		user = dbHelper.getUserInfo(dbHelper.getUserId(username));
		welcomeText.setText("Welcome, " + user.getFullName());
		userInfo.setText("User ID: " + user.getUserId() + "\n\nUsername: " + user.getUserName() + "\n\nFirst Name: " + user.getFirstName() + "\n\nLast Name: " + user.getLastName() + "\n\nEmail: " + user.getEmail());
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
	     try{
		    	YahooDataParser yahooParser = new YahooDataParser();
		    	YahooStockModel stock = yahooParser.getJSON(stockSearchText.getText());
		    	if(stock.getCompanyName() != null){
		    		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../Views/StockView.fxml"));
		            Parent root = (Parent) fxmlLoader.load();
		            Stage stage = new Stage();
		            stage.setTitle("VirtualStocks");
		            stage.setScene(new Scene(root));  
		            StockController stockController = fxmlLoader.<StockController>getController();
		            stockController.setStock(stock);
		            stage.show();
		    	}
		    	else{
			    	Alert alert = new Alert(AlertType.WARNING);
			    	alert.setTitle("Warning Dialog");
			    	alert.setHeaderText("Error:");
			    	alert.setContentText("Invalid symbol.\nPlease try again.");
			    	alert.showAndWait();
		    	}
	        
	     } catch (IOException e) {
	         e.printStackTrace();
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
	        BankAccountController bankController = fxmlLoader.<BankAccountController>getController();
	        bankController.setUserId(user.getUserId());
	        stage.show();
		} catch (IOException e) {
	         e.printStackTrace();
	    }
	}

	

}
