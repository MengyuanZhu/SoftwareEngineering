package controllers;

import javafx.fxml.FXML;
import javafx.scene.text.Text;
import models.YahooStockModel;
import javafx.event.ActionEvent;

public class StockController {
	
	@FXML private Text stockInfoText;
	@FXML private Text stockNameText;
	
	public void setStock(YahooStockModel stock){
		stockNameText.setText(stock.getCompanyName() + " ("+ stock.getTicker().toUpperCase() + ")");
		stockInfoText.setText("Exchange: " + stock.getExchangeName() + 
							  "\n\nCurrency: " + stock.getCurrency() +
							  "\n\nLast Trade Price: " + stock.getSeries().get(0).getClose());
	}

	@FXML public void handleBuy(ActionEvent event) {
		
	}
}
