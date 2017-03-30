package virtualStock.controller;



import virtualStock.MainApp;
import virtualStock.util.DatabaseHelper;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class SignInController {
	private MainApp mainApp;
	
	@FXML
	private Button signInButton;
	
	@FXML
	private Button signUpButton;
	
	@FXML
	private TextField usernameTextField;
	
	@FXML
	private PasswordField passwordPasswordField;
	
	private DatabaseHelper dbHelper = new DatabaseHelper();
	
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

	
	@FXML
	private void handleSignIn(ActionEvent event) {
	    boolean authenticated = dbHelper.authenticateUser(usernameTextField.getText(), passwordPasswordField.getText());
	    if(authenticated){
	    	System.out.println("authenticated");
	    	Stage stage = (Stage) signInButton.getScene().getWindow();
		    stage.close();
		    mainApp.initRootLayout();
		    mainApp.showStockOverview();
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
	private void handleSignUp(ActionEvent event) {
	     mainApp.showSignUp();
	}
 

	@FXML
	private void handleUsername(MouseEvent event) {

		usernameTextField.setFocusTraversable(true);
		passwordPasswordField.setFocusTraversable(true);
	}
	
	@FXML
	private void handlePassword(MouseEvent event) {

		usernameTextField.setFocusTraversable(true);
		passwordPasswordField.setFocusTraversable(true);
	}
}
