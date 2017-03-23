package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
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
	private DatabaseHelper dbHelper = new DatabaseHelper();

	@FXML public void handleSubmit(ActionEvent event) {
		//TODO: Update tables in database here on submit.
		String firstName = firstNameField.getText();
		String lastName = firstNameField.getText();
		String email = emailField.getText();
		String password = passwordField.getText();
		String confirmPassword = confirmPasswordField.getText();
		if(password.equals(confirmPassword)){
			//Use dbHelper to update user information.
			System.out.println("Update user information here");
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

}
