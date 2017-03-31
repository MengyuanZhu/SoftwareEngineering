package controllers;

import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Series;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.text.Text;
import models.YahooStockModel;

import javafx.event.ActionEvent;

public class StockController {
	
	@FXML private Text stockInfoText;
	@FXML private Text stockNameText;
	@FXML private TextField amountField;
	@FXML private LineChart<String, Double> stockChart;
	@FXML private NumberAxis priceAxis;
	
	public void setStock(YahooStockModel stock){
		stockNameText.setText(stock.getCompanyName() + " ("+ stock.getTicker().toUpperCase() + ")");
		stockInfoText.setText("Exchange: " + stock.getExchangeName() + 
							  "\n\nCurrency: " + stock.getCurrency() +
							  "\n\nLast Closed Price: " + stock.getSeries().get(stock.getSeries().size() - 1).getClose());
		stockChart.setTitle(stock.getSeries().get(0).getTimeStamp().toString().split(" ")[0]);
		Series<String, Double> series = new XYChart.Series<>();
		series.setName("");
		double max = stock.getSeries().get(0).getClose();
		double min = stock.getSeries().get(0).getClose();
		for(int i = 0; i < stock.getSeries().size(); i+=15){
			series.getData().add(new XYChart.Data<String, Double>(stock.getSeries().get(i).getTimeStamp().toString().split(" ")[1].substring(0, 5), stock.getSeries().get(i).getClose()));
			if(stock.getSeries().get(i).getClose() > max){
				max = stock.getSeries().get(i).getClose();
			}
			if(stock.getSeries().get(i).getClose() < min){
				min = stock.getSeries().get(i).getClose();
			}
		}
		priceAxis.setAutoRanging(false);
		priceAxis.setLowerBound(min - 0.1);
		priceAxis.setUpperBound(max + 0.1);
		priceAxis.setTickUnit(0.01);
		stockChart.setLegendVisible(false);
		stockChart.getData().add(series);
	}

	@FXML public void handleBuy(ActionEvent event) {
		if(!amountField.getText().matches("^\\d{1,3}$")){
	    	Alert alert = new Alert(AlertType.WARNING);
	    	alert.setTitle("Warning Dialog");
	    	alert.setHeaderText("Error:");
	    	alert.setContentText("Not a valid amount.\nPlease try again.");
	    	alert.showAndWait();
		}
		else{
			System.out.println(amountField.getText());
		}
	}
}
