


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

/**
 * SignupController is a class that connects the SignupView to the database
 * The class includes functions:
 * <ul>
 * <li>Add new user to the database
 * </ul>
 * @author      Hyeun Kang
 */
public class SignupController {
	
	@FXML private TextField firstnameText;
	@FXML private TextField lastnameText;
	@FXML private TextField usernameText;
	@FXML private TextField emailText;
	@FXML private PasswordField passwordText;
	@FXML private Button cancelButton;
	private DatabaseHelper dbHelper = new DatabaseHelper();
	
	public static final Pattern VALID_EMAIL_ADDRESS_REGEX = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
	
	/**
	 * This method adds new user to the database
	 * The method includes functions:
	 * <ul>
	 * <li>Check if username already exists in the database and if it already exists, prompt the user with error message
	 * <li>Check if username contains a blank space and if it contains, prompt the user with error message
	 * <li>Check if email is valid and if it is not, prompt the user with error message
	 * <li>If username passes all the tests, add the new user and all of its information to the database.
	 * </ul>
	 */
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
		    newUser.setTotalAmount(0); //Initialized to 0 because new user.
		    newUser.setStockAmount(0);
		    newUser.setUserName(usernameText.getText());
		    dbHelper.addUser(newUser);
		    Stage stage = (Stage) cancelButton.getScene().getWindow();
		    stage.close();
		}
	}
	
	/**
	 * closes the signupview window(cancel)
	 */
	@FXML
	private void handleCancel(ActionEvent event) {
	    Stage stage = (Stage) cancelButton.getScene().getWindow();
	    stage.close();
	}
	
	/**
	 * Initialize the username text box
	 */
	@FXML
	private void handleUsername(MouseEvent event) {
	    usernameText.setText("");
	}
	
	/**
	 * Initialize the email text box
	 */
	@FXML
	private void handleEmail(MouseEvent event) {
		emailText.setText("");
	}
	
	/**
	 * Initialize the password text box
	 */
	@FXML
	private void handlePassword(MouseEvent event) {
	    passwordText.setText("");
	}

	/**
	 * Initialize the first name text box 
	 */
	@FXML
	public void handleFirstName(MouseEvent event) {
		firstnameText.setText("");
	}

	/**
	 * Initialize the last name text box 
	 */
	@FXML
	public void handleLastName(MouseEvent event) {
		lastnameText.setText("");
	}
	
	/**
	 * Initialize the email text box 
	 */
	public static boolean validateEmail(String email) {
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX .matcher(email);
        return matcher.find();
	}
	
}
