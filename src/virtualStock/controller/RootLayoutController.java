package virtualStock.controller;



import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

import virtualStock.MainApp;


/**
 * @author mzhu7
 *
 */
public class RootLayoutController {

    // Reference to the main application
    private MainApp mainApp;

    /**
     * Is called by the main application to give a reference back to itself.
     * 
     * @param mainApp
     */
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    /**
     * Creates an empty address book.
     */
    @FXML
    private void handleSignIn() {
        mainApp.getStockData().clear();
   
        mainApp.showSignIn();
    }

    /**
     * Opens a FileChooser to let the user select an address book to load.
     */
    @FXML
    private void handleSignUp() {
    	mainApp.showSignUp();
    }

    /**
     * Saves the file to the person file that is currently open. If there is no
     * open file, the "save as" dialog is shown.
     */
    @FXML
    private void handleSave() {

    }

    /**
     * Opens a FileChooser to let the user select a file to save to.
     */
    @FXML
    private void handleSaveAs() {
     
    }

    /**
     * Opens an about dialog.
     */
    @FXML
    private void handleAbout() {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Virtual Stock");
        alert.setHeaderText("About");
        alert.setContentText("Author: Mengyuan Zhu");

        alert.showAndWait();
    }

    /**
     * Closes the application.
     */
    @FXML
    private void handleExit() {
        System.exit(0);
    }
    
    /**
     * Opens the birthday statistics.
     */
    @FXML
    private void handleShowPortfolio() {
      mainApp.showPortfolio();
    }
    
    
    /**
     * Opens the day statistics.
     */
    @FXML
    private void handleShowAccount() {    
      mainApp.showAccount();
    }
    
    /**
     * Opens the day statistics.
     */
    @FXML
    private void handleShowBanking() {    
      mainApp.showBanking();
    }
    
    /**
     * Opens the day statistics.
     */
    @FXML
    private void handleShowHistory() {    
      mainApp.showHistory();
    }
    
    /**
     * Opens the day statistics.
     */
    @FXML
    private void handleShowSettings() {    
      mainApp.showSetting();
    }
    
    
    
    
    
    
}