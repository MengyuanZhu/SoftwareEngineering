package controllers;

import java.io.IOException;

import application.MainApp;
import helpers.DatabaseHelper;
import javafx.event.ActionEvent;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


public class LoginController {
	
	@FXML private TextField usernameText;
	@FXML private PasswordField passwordText;
	DatabaseHelper dbHelper = new DatabaseHelper();
	
	@FXML
	private void handleLogin(ActionEvent event) {
	    boolean authenticated = dbHelper.authenticateUser(usernameText.getText(), passwordText.getText());
	    if(authenticated){
	    	System.out.println("authenticated");
	    }
	    else{
	    	Alert alert = new Alert(AlertType.WARNING);
	    	alert.setTitle("Warning Dialog");
	    	alert.setHeaderText("Error:");
	    	alert.setContentText("Wrong login information.\nPlease try again.");
	    	alert.showAndWait();
	    }
	}
	
	@FXML
	private void handleSignup(ActionEvent event) {
	     try{
	            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../Views/SignupView.fxml"));
	            Parent root1 = (Parent) fxmlLoader.load();
	            Stage stage = new Stage();
	            stage.initModality(Modality.APPLICATION_MODAL);
	            stage.initStyle(StageStyle.UNDECORATED);
	            stage.setTitle("ABC");
	            stage.setScene(new Scene(root1));  
	            stage.show();
	        
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	
	@FXML
	private void handleUsername(MouseEvent event) {
	    usernameText.setText("");
	}
	
	@FXML
	private void handlePassword(MouseEvent event) {
	    passwordText.setText("");
	}
}
