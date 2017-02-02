package virtualStock.view;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import virtualStock.MainApp;
import virtualStock.model.Stock;
import virtualStock.util.DateUtil;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

/**
 * @author mzhu7
 *
 */
public class StockOverviewController {
    @FXML
    private TableView<Stock> stockTable;
    @FXML
    private TableColumn<Stock, String> stockColumn;
    @FXML
    private TableColumn<Stock, String> priceColumn;
    @FXML
    private TableColumn<Stock, String> sharesColumn;    
    
    
    @FXML
    private Label stockLabel;    
    @FXML
    private Label priceLabel;    
    @FXML
    private Label sharesLabel;    
    @FXML
    private Label openLabel;
    @FXML
    private Label todayHighLabel;
    @FXML
    private Label todayLowLabel;
    @FXML
    private Label yearHighLabel;
    @FXML
    private Label yearLowLabel;
    @FXML
    private Label volumeLabel;
    @FXML
    private Label averageVolumeLabel;
    @FXML
    private Label marketCapLabel;
    @FXML
    private Label PERatioLabel;
    @FXML
    private Label divYieldLabel;

    // Reference to the main application.
    private MainApp mainApp;

    /**
     * The constructor.
     * The constructor is called before the initialize() method.
     */
    public StockOverviewController() {
    }

    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
  
    @FXML
    private void initialize() {
        // Initialize the person table with the two columns.
        stockColumn.setCellValueFactory(
                cellData -> cellData.getValue().stockNameProperty());
        priceColumn.setCellValueFactory(
                cellData -> cellData.getValue().priceProperty());
        sharesColumn.setCellValueFactory(
        		cellData-> cellData.getValue().sharesProperty());

        // Clear person details.
        showPersonDetails(null);

        // Listen for selection changes and show the person details when changed.
        stockTable.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> showPersonDetails(newValue));
    }
    
    

    /**
     * Is called by the main application to give a reference back to itself.
     * 
     * @param mainApp
     */
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;

        // Add observable list data to the table
        stockTable.setItems(mainApp.getPersonData());
    }
    
    
    /**
     * Fills all text fields to show details about the person.
     * If the specified person is null, all text fields are cleared.
     * 
     * @param person the person or null
     */
    private void showPersonDetails(Stock myStock) {
        if (myStock != null) {
            // Fill the labels with info from the stock object.
        	stockLabel.setText(myStock.getStockName());
        	priceLabel.setText(myStock.getPrice());
        	sharesLabel.setText(myStock.getShares());
            openLabel.setText(myStock.getOpen());
            todayHighLabel.setText(myStock.getTodayHigh());
            todayLowLabel.setText(myStock.getTodayLow());
            yearHighLabel.setText(myStock.getYearHigh());
            yearLowLabel.setText(myStock.getYearLow());
            volumeLabel.setText(myStock.getVolume());
            averageVolumeLabel.setText(myStock.getAverageVolume());
            marketCapLabel.setText(myStock.getMarketCap());
            PERatioLabel.setText(myStock.getPERatio());
            divYieldLabel.setText(myStock.getDivYield());

        } else {
            // Person is null, remove all the text.
        	stockLabel.setText("");
        	priceLabel.setText("");
        	sharesLabel.setText("");
            openLabel.setText("");
            todayHighLabel.setText("");
            todayLowLabel.setText("");
            yearHighLabel.setText("");
            yearLowLabel.setText("");
            volumeLabel.setText("");
            averageVolumeLabel.setText("");
            marketCapLabel.setText("");
            PERatioLabel.setText("");
            divYieldLabel.setText("");
        }
    }
    
    /**
     * Called when the user clicks on the delete button.
     */
    @FXML
    private void handleDeletePerson() {
        int selectedIndex = stockTable.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            stockTable.getItems().remove(selectedIndex);
        } else {
            // Nothing selected.
            Alert alert = new Alert(AlertType.WARNING);
            alert.initOwner(mainApp.getPrimaryStage());
            alert.setTitle("No Selection");
            alert.setHeaderText("No Person Selected");
            alert.setContentText("Please select a person in the table.");

            alert.showAndWait();
        }
    }
    
    /**
     * Called when the user clicks the new button. Opens a dialog to edit
     * details for a new person.
     */
    @FXML
    private void handleNewPerson() {
        Stock tempPerson = new Stock();
        boolean okClicked = mainApp.showStockPurchaseDialog(tempPerson);
        if (okClicked) {
            mainApp.getPersonData().add(tempPerson);
        }
    }

    /**
     * Called when the user clicks the edit button. Opens a dialog to edit
     * details for the selected person.
     */
    @FXML
    private void handleEditPerson() {
        Stock selectedPerson = stockTable.getSelectionModel().getSelectedItem();
        if (selectedPerson != null) {
            boolean okClicked = mainApp.showStockPurchaseDialog(selectedPerson);
            if (okClicked) {
                showPersonDetails(selectedPerson);
            }

        } else {
            // Nothing selected.
            Alert alert = new Alert(AlertType.WARNING);
            alert.initOwner(mainApp.getPrimaryStage());
            alert.setTitle("No Selection");
            alert.setHeaderText("No Person Selected");
            alert.setContentText("Please select a person in the table.");

            alert.showAndWait();
        }
    }
}
