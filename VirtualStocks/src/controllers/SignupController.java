package controllers;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
import models.UserModel;


public class SignupController {
	
	@FXML private TextField firstnameText;
	@FXML private TextField lastnameText;
	@FXML private TextField usernameText;
	@FXML private TextField emailText;
	@FXML private PasswordField passwordText;
	@FXML private Button cancelButton;
	DatabaseHelper dbHelper = new DatabaseHelper();
	
	public static final Pattern VALID_EMAIL_ADDRESS_REGEX = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
	
	@FXML
	private void handleSignup(ActionEvent event) {
		if(dbHelper.checkExistingUsername(usernameText.getText())==true){
	    	Alert alert = new Alert(AlertType.WARNING);
	    	alert.setTitle("Warning Dialog");
	    	alert.setHeaderText("Error:");
	    	alert.setContentText("Existing username.\nPlease try again.");
	    	alert.showAndWait();
		}
		else if(usernameText.getText().contains(" ")){
	    	Alert alert = new Alert(AlertType.WARNING);
	    	alert.setTitle("Warning Dialog");
	    	alert.setHeaderText("Error:");
	    	alert.setContentText("Username cannot contain spaces.\nPlease try again.");
	    	alert.showAndWait();
		}
		else if(!validateEmail(emailText.getText())){
	    	Alert alert = new Alert(AlertType.WARNING);
	    	alert.setTitle("Warning Dialog");
	    	alert.setHeaderText("Error:");
	    	alert.setContentText("Invalid email.\nPlease try again.");
	    	alert.showAndWait();
		}
		else{
		    UserModel newUser = new UserModel();
		    newUser.setFirstName(firstnameText.getText());
		    newUser.setLastName(lastnameText.getText());
		    newUser.setEmail(emailText.getText());
		    newUser.setPassword(passwordText.getText()); //If going to be hashed, set this to hashed password.
		    newUser.setTotalStockAmount(0); //Initialized to 0 because new user.
		    newUser.setUserName(usernameText.getText());
		    dbHelper.addUserToDb(newUser);
		    Stage stage = (Stage) cancelButton.getScene().getWindow();
		    stage.close();
		}
	}
	
	@FXML
	private void handleCancel(ActionEvent event) {
	    Stage stage = (Stage) cancelButton.getScene().getWindow();
	    stage.close();
	}
	
	@FXML
	private void handleUsername(MouseEvent event) {
	    usernameText.setText("");
	}
	
	@FXML
	private void handleEmail(MouseEvent event) {
		emailText.setText("");
	}
	
	@FXML
	private void handlePassword(MouseEvent event) {
	    passwordText.setText("");
	}

	@FXML
	public void handleFirstName(MouseEvent event) {
		firstnameText.setText("");
	}

	@FXML
	public void handleLastName(MouseEvent event) {
		lastnameText.setText("");
	}
	
	public static boolean validateEmail(String email) {
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX .matcher(email);
        return matcher.find();
}
	
}
