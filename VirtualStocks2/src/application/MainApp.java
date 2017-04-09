package application;

import java.io.IOException;

import helpers.DataParser;
import helpers.DatabaseHelper;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import models.StockModel;

public class MainApp extends Application {

	private Stage primaryStage;
    private BorderPane rootLayout;
    private DatabaseHelper dbHelper = new DatabaseHelper();

	@Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("VirtualStocks");
//        dbHelper.connect();
        //establishDatabase();
//        dbHelper.addBankTransactionToDb(1, 50);
        initLogin();
    }
//
//	public void establishDatabase(){
//		DatabaseHelper sql = new DatabaseHelper();
//		sql.connect();
//		DataParser parser = new DataParser();
//		String symbol = "MSFT";
//		String data = parser.getJSON(symbol);
//		StockModel stock = parser.parseJSON(data);
//	}
	
    /**
     * Initializes the login page.
     */
    public void initLogin() {
        try {
            // Load root layout from fxml file.
	        FXMLLoader loader = new FXMLLoader();
	        loader.setLocation(MainApp.class.getResource("../views/LoginView.fxml"));
	        AnchorPane page = (AnchorPane) loader.load();

	        Scene scene = new Scene(page);
	        primaryStage.setScene(scene);
	        primaryStage.setResizable(false);
	        primaryStage.show();
	        
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
	
	
//    /**
//     * Initializes the root layout.
//     */
//    public void initRootLayout() {
//        try {
//            // Load root layout from fxml file.
//            FXMLLoader loader = new FXMLLoader();
//            loader.setLocation(MainApp.class.getResource("../views/RootLayout.fxml"));
//            rootLayout = (BorderPane) loader.load();
//
//            // Show the scene containing the root layout.
//            Scene scene = new Scene(rootLayout);
//            primaryStage.setScene(scene);
//            primaryStage.show();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    /**
//     * Shows the person overview inside the root layout.
//     */
//    public void showPersonOverview() {
//        try {
//            // Load person overview.
//            FXMLLoader loader = new FXMLLoader();
//            loader.setLocation(MainApp.class.getResource("../views/PortfolioOverview.fxml"));
//            AnchorPane personOverview = (AnchorPane) loader.load();
//
//            // Set person overview into the center of root layout.
//            rootLayout.setCenter(personOverview);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
