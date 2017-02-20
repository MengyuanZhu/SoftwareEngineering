package virtualStock;


import java.io.File;
import java.io.IOException;
import java.util.prefs.Preferences;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import virtualStock.model.Stock;
import virtualStock.model.StockListWrapper;
import virtualStock.view.PortfolioController;
import virtualStock.view.StockPurchaseController;
import virtualStock.view.StockOverviewController;
import virtualStock.view.RootLayoutController;
import virtualStock.view.StockDayController;


/**
 * @author mzhu7
 *
 */
public class MainApp extends Application {

    private Stage primaryStage;
    private BorderPane rootLayout;
    private ObservableList<Stock> personData = FXCollections.observableArrayList();


    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Virtual Stock");
        this.primaryStage.getIcons().add(new Image("file:resources/images/stock.png"));

        initRootLayout();

        showStockOverview();
    }
    
    public MainApp() {
        // Add some sample data  
    }
    
    
    public void showSignIn() {
        try {
	        // Load the fxml file and create a new stage for the popup.
	        FXMLLoader loader = new FXMLLoader();
	        loader.setLocation(MainApp.class.getResource("view/SignIn.fxml"));
	        AnchorPane page = (AnchorPane) loader.load();
	        Stage dialogStage = new Stage();
	        dialogStage.setTitle("Sign In");
	        dialogStage.initModality(Modality.WINDOW_MODAL);
	        dialogStage.initOwner(primaryStage);
	        Scene scene = new Scene(page);
	        dialogStage.setScene(scene);
	        dialogStage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void showAccount() {
        try {
	        // Load the fxml file and create a new stage for the popup.
	        FXMLLoader loader = new FXMLLoader();
	        loader.setLocation(MainApp.class.getResource("view/Account.fxml"));
	        AnchorPane page = (AnchorPane) loader.load();
	        Stage dialogStage = new Stage();
	        dialogStage.setTitle("Account");
	        dialogStage.initModality(Modality.WINDOW_MODAL);
	        dialogStage.initOwner(primaryStage);
	        Scene scene = new Scene(page);
	        dialogStage.setScene(scene);
	        dialogStage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void showBanking() {
        try {
	        // Load the fxml file and create a new stage for the popup.
	        FXMLLoader loader = new FXMLLoader();
	        loader.setLocation(MainApp.class.getResource("view/Banking.fxml"));
	        AnchorPane page = (AnchorPane) loader.load();
	        Stage dialogStage = new Stage();
	        dialogStage.setTitle("Banking");
	        dialogStage.initModality(Modality.WINDOW_MODAL);
	        dialogStage.initOwner(primaryStage);
	        Scene scene = new Scene(page);
	        dialogStage.setScene(scene);
	        dialogStage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void showSetting() {
        try {
	        // Load the fxml file and create a new stage for the popup.
	        FXMLLoader loader = new FXMLLoader();
	        loader.setLocation(MainApp.class.getResource("view/Setting.fxml"));
	        AnchorPane page = (AnchorPane) loader.load();
	        Stage dialogStage = new Stage();
	        dialogStage.setTitle("Setting");
	        dialogStage.initModality(Modality.WINDOW_MODAL);
	        dialogStage.initOwner(primaryStage);
	        Scene scene = new Scene(page);
	        dialogStage.setScene(scene);
	        dialogStage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void showHistory() {
        try {
	        // Load the fxml file and create a new stage for the popup.
	        FXMLLoader loader = new FXMLLoader();
	        loader.setLocation(MainApp.class.getResource("view/History.fxml"));
	        AnchorPane page = (AnchorPane) loader.load();
	        Stage dialogStage = new Stage();
	        dialogStage.setTitle("History");
	        dialogStage.initModality(Modality.WINDOW_MODAL);
	        dialogStage.initOwner(primaryStage);
	        Scene scene = new Scene(page);
	        dialogStage.setScene(scene);
	        dialogStage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    

    
    public void showStockOverview() {
        try {
            // Load person overview.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/StockOverview.fxml"));
            AnchorPane personOverview = (AnchorPane) loader.load();

            // Set person overview into the center of root layout.
            rootLayout.setCenter(personOverview);

            // Give the controller access to the main app.
            StockOverviewController controller = loader.getController();
            controller.setMainApp(this);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Opens a dialog to edit details for the specified person. If the user
     * clicks OK, the changes are saved into the provided person object and true
     * is returned.
     * 
     * @param person the person object to be edited
     * @return true if the user clicked OK, false otherwise.
     */
    public boolean showStockPurchaseDialog(Stock myStock) {
        try {
            // Load the fxml file and create a new stage for the popup dialog.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/StockPurchaseDialog.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            // Create the dialog Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Buy Stock");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            // Set the person into the controller.
            StockPurchaseController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setStock(myStock);

            // Show the dialog and wait until the user closes it
            dialogStage.showAndWait();

            return controller.isOkClicked();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Returns the main stage.
     * @return
     */
    public Stage getPrimaryStage() {
        return primaryStage;
    }

    
    public static void main(String[] args) {
        launch(args);
    }
    
    public ObservableList<Stock> getPersonData() {
        return personData;
    }
    
    /**
     * Returns the person file preference, i.e. the file that was last opened.
     * The preference is read from the OS specific registry. If no such
     * preference can be found, null is returned.
     * 
     * @return
     */
    public File getPersonFilePath() {
        Preferences prefs = Preferences.userNodeForPackage(MainApp.class);
        String filePath = prefs.get("filePath", null);
        if (filePath != null) {
            return new File(filePath);
        } else {
            return null;
        }
    }

    /**
     * Sets the file path of the currently loaded file. The path is persisted in
     * the OS specific registry.
     * 
     * @param file the file or null to remove the path
     */
    public void setPersonFilePath(File file) {
        Preferences prefs = Preferences.userNodeForPackage(MainApp.class);
        if (file != null) {
            prefs.put("filePath", file.getPath());
            String username="Mengyuan Zhu";
            // Update the stage title.
            primaryStage.setTitle("Virtual Stock - " + username);
        } else {
            prefs.remove("filePath");

            // Update the stage title.
            primaryStage.setTitle("Virtual Stock");
        }
    }


	/**
	 * Loads person data from the specified file. The current person data will
	 * be replaced.
	 * 
	 * @param file
	 */
	public void loadPersonDataFromFile(File file) {
	    try {
	        JAXBContext context = JAXBContext
	                .newInstance(StockListWrapper.class);
	        Unmarshaller um = context.createUnmarshaller();
	
	        // Reading XML from the file and unmarshalling.
	        StockListWrapper wrapper = (StockListWrapper) um.unmarshal(file);
	
	        personData.clear();
	        personData.addAll(wrapper.getPersons());
	
	        // Save the file path to the registry.
	        setPersonFilePath(file);
	
	    } catch (Exception e) { // catches ANY exception
	        Alert alert = new Alert(AlertType.ERROR);
	        alert.setTitle("Error");
	        alert.setHeaderText("Could not load data");
	        alert.setContentText("Could not load data from file:\n" + file.getPath());
	
	        alert.showAndWait();
	    }
	}
	
	/**
	 * Saves the current person data to the specified file.
	 * 
	 * @param file
	 */
	public void savePersonDataToFile(File file) {
	    try {
	        JAXBContext context = JAXBContext
	                .newInstance(StockListWrapper.class);
	        Marshaller m = context.createMarshaller();
	        m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
	
	        // Wrapping our person data.
	        StockListWrapper wrapper = new StockListWrapper();
	        wrapper.setPersons(personData);
	
	        // Marshalling and saving XML to the file.
	        m.marshal(wrapper, file);
	
	        // Save the file path to the registry.
	        setPersonFilePath(file);
	    } catch (Exception e) { // catches ANY exception
	        Alert alert = new Alert(AlertType.ERROR);
	        alert.setTitle("Error");
	        alert.setHeaderText("Could not save data");
	        alert.setContentText("Could not save data to file:\n" + file.getPath());
	
	        alert.showAndWait();
	    }
	}
	
	
	/**
	 * Initializes the root layout and tries to load the last opened
	 * person file.
	 */
	public void initRootLayout() {
	    try {
	        // Load root layout from fxml file.
	        FXMLLoader loader = new FXMLLoader();
	        loader.setLocation(MainApp.class
	                .getResource("view/RootLayout.fxml"));
	        rootLayout = (BorderPane) loader.load();

	        // Show the scene containing the root layout.
	        Scene scene = new Scene(rootLayout);
	        primaryStage.setScene(scene);

	        // Give the controller access to the main app.
	        RootLayoutController controller = loader.getController();
	        controller.setMainApp(this);

	        primaryStage.show();
	    } catch (IOException e) {
	        e.printStackTrace();
	    }

	    // Try to load last opened person file.
	    File file = getPersonFilePath();
	    if (file != null) {
	        loadPersonDataFromFile(file);
	    }
	}
	
	/**
	 * Opens a dialog to show birthday statistics.
	 */
	public void showPortfolio() {
	    try {
	        // Load the fxml file and create a new stage for the popup.
	        FXMLLoader loader = new FXMLLoader();
	        loader.setLocation(MainApp.class.getResource("view/Portfolio.fxml"));
	        AnchorPane page = (AnchorPane) loader.load();
	        Stage dialogStage = new Stage();
	        dialogStage.setTitle("Portfolio");
	        dialogStage.initModality(Modality.WINDOW_MODAL);
	        dialogStage.initOwner(primaryStage);
	        Scene scene = new Scene(page);
	        dialogStage.setScene(scene);

	        // Set the persons into the controller.
	        PortfolioController controller = loader.getController();
	        controller.setPersonData(personData);

	        dialogStage.show();

	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}
	
	/**
	 * Opens a dialog to show birthday statistics.
	 */
	public void showSignUp() {
	    try {
	        // Load the fxml file and create a new stage for the popup.
	        FXMLLoader loader = new FXMLLoader();
	        loader.setLocation(MainApp.class.getResource("view/SignUp.fxml"));
	        AnchorPane page = (AnchorPane) loader.load();
	        Stage dialogStage = new Stage();
	        dialogStage.setTitle("SignUp");
	        dialogStage.initModality(Modality.WINDOW_MODAL);
	        dialogStage.initOwner(primaryStage);
	        Scene scene = new Scene(page);
	        dialogStage.setScene(scene);
	        dialogStage.show();

	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}
	
	
	/**
	 * Opens a dialog to show birthday statistics.
	 */
	public void showDayDataStatistics() {
	    try {
	        // Load the fxml file and create a new stage for the popup.
	        FXMLLoader loader = new FXMLLoader();
	        loader.setLocation(MainApp.class.getResource("view/StockDay.fxml"));
	        AnchorPane page = (AnchorPane) loader.load();
	        Stage dialogStage = new Stage();
	        dialogStage.setTitle("Day Statistics");
	        dialogStage.initModality(Modality.WINDOW_MODAL);
	        dialogStage.initOwner(primaryStage);
	        Scene scene = new Scene(page);
	        dialogStage.setScene(scene);

	        StockDayController controller = loader.getController();
	        // Set the persons into the controller.
	        
	        controller.setStockQuote("MSFT");

	        dialogStage.show();

	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}

	
	
}
