package controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class HistoryController {
	
	ObservableList<String> options = 
		    FXCollections.observableArrayList(
		        "Past 1 day",
		        "Past 1 week",
		        "Past 1 month"
		    );
	
	@FXML
	private ComboBox<String> dateRangeComboBox;
	/**
	@FXML
	private TableView<Stock> historyTableView;
	
	@FXML
	private TableColumn<Stock, String> stockNameColumn;
	
	@FXML
	private TableColumn<Stock, String> typeColumn;
	
	@FXML
	private TableColumn<Stock, String> priceColumn;
	
	@FXML
	private TableColumn<Stock, String> dateColumn;
	
	private ObservableList<Stock> stockData = FXCollections.observableArrayList(
			new Stock("MSFT","69.00"));
	//historyTableView.setItems(getStockData());
	
	
    
    @FXML
    private void initialize() {
    	dateRangeComboBox.setItems(options);
    	
    	
    	
        stockNameColumn.setCellValueFactory(cellData -> cellData.getValue().stockNameProperty());
        priceColumn.setCellValueFactory(cellData -> cellData.getValue().priceProperty());
  	
    }
    
    private ObservableList<Stock> getStockData() {
        return stockData;
    }
    
    */
    
	

}
