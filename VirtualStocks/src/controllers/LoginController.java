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
import javafx.scene.control.Button;
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
	@FXML private Button signinButton;
	private DatabaseHelper dbHelper = new DatabaseHelper();
	
	@FXML
	private void handleLogin(ActionEvent event) {
	    boolean authenticated = dbHelper.authenticateUser(usernameText.getText(), passwordText.getText());
	    if(authenticated){
	    	System.out.println("authenticated");
	    	Stage stage = (Stage) signinButton.getScene().getWindow();
		    stage.close();
		    initRootLayout();
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
	            Parent root = (Parent) fxmlLoader.load();
	            Stage stage = new Stage();
	            stage.initModality(Modality.APPLICATION_MODAL);
	            stage.initStyle(StageStyle.UNDECORATED);
	            stage.setTitle("Sign Up");
	            stage.setScene(new Scene(root));  
	            stage.show();
	        
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	
	@FXML
	private void initRootLayout() {
	     try{
	            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../Views/MainView.fxml"));
	            Parent root = (Parent) fxmlLoader.load();
	            Stage stage = new Stage();
	            stage.setTitle("VirtualStocks");
	            stage.setScene(new Scene(root));  
	            MainController mainController = fxmlLoader.<MainController>getController();
	            mainController.setUsername(usernameText.getText());
	            stage.show();
	        
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	
	@FXML
	private void handleUsername(MouseEvent event) {
//	    usernameText.setText("");
	    usernameText.setFocusTraversable(true);
	    passwordText.setFocusTraversable(true);
	}
	
	@FXML
	private void handlePassword(MouseEvent event) {
//	    passwordText.setText("");
	    usernameText.setFocusTraversable(true);
	    passwordText.setFocusTraversable(true);
	}
}
