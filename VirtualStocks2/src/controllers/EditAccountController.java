package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import models.UserModel;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import helpers.DatabaseHelper;
import javafx.event.ActionEvent;

public class EditAccountController {
	@FXML private TextField firstNameField;
	@FXML private TextField lastNameField;
	@FXML private TextField emailField;
	@FXML private TextField passwordField;
	@FXML private TextField confirmPasswordField;
	@FXML private Button submitButton;
	@FXML private Button cancelButton;
	private UserModel user;
	private DatabaseHelper dbHelper = new DatabaseHelper();
	
	public static final Pattern VALID_EMAIL_ADDRESS_REGEX = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
	
	public void setUser(UserModel user){
		this.user = user;
		firstNameField.setText(user.getFirstName());
		lastNameField.setText(user.getLastName());
		emailField.setText(user.getEmail());
	}
	@FXML public void handleSubmit(ActionEvent event) {
		//TODO: Update tables in database here on submit.
		String firstName = firstNameField.getText();
		String lastName = lastNameField.getText();
		String email = emailField.getText();
		String password = passwordField.getText();
		String confirmPassword = confirmPasswordField.getText();
		if(password.isEmpty()){
	    	Alert alert = new Alert(AlertType.WARNING);
	    	alert.setTitle("Warning Dialog");
	    	alert.setHeaderText("Error:");
	    	alert.setContentText("Passwords must be entered.\nPlease try again.");
	    	alert.showAndWait();
		}
		else if(!validateEmail(email)){
	    	Alert alert = new Alert(AlertType.WARNING);
	    	alert.setTitle("Warning Dialog");
	    	alert.setHeaderText("Error:");
	    	alert.setContentText("Valid email required.\nPlease try again.");
	    	alert.showAndWait();
		}
		else if(password.equals(confirmPassword)){
			dbHelper.updateUser(user.getUserName(), firstName, lastName, password, email);
			Stage stage = (Stage) submitButton.getScene().getWindow();
			stage.close();
		}
		else{
	    	Alert alert = new Alert(AlertType.WARNING);
	    	alert.setTitle("Warning Dialog");
	    	alert.setHeaderText("Error:");
	    	alert.setContentText("Passwords must match.\nPlease try again.");
	    	alert.showAndWait();
		}
	}

	@FXML public void handleCancel(ActionEvent event) {
	    Stage stage = (Stage) cancelButton.getScene().getWindow();
	    stage.close();
	}
	public static boolean validateEmail(String email) {
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX .matcher(email);
        return matcher.find();
	}

}
