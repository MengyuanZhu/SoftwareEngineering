package virtualStock.view;

import java.io.IOException;
import java.text.DateFormatSymbols;
import java.util.Arrays;
import java.util.Locale;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import virtualStock.model.StockDayData;
import javafx.scene.chart.XYChart;
public class StockDayController {

	@FXML
    final private NumberAxis xAxis = new NumberAxis();
	
    final NumberAxis yAxis = new NumberAxis();
	
    @FXML
    private final LineChart<Number, Number> lineChart = new LineChart<Number, Number>(
            xAxis, yAxis);

    

    private ObservableList<String> monthNames = FXCollections.observableArrayList();

    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    private void initialize() {
    }
    
    
    public void setStockQuote(String string) throws IOException{
    	String chartData=StockDayData.getUrlSource("http://chartapi.finance.yahoo.com/instrument/1.0/MSFT/chartdata;type=quote;range=1d/csv");
    	
        xAxis.setLabel("Number of Month");
        

        lineChart.setTitle("Line Chart");
        XYChart.Series<Number, Number> series = new XYChart.Series<Number, Number>();
        series.setName("My Data");
        // populating the series with data
        series.getData().add(new XYChart.Data<Number, Number>(1, 23));
        series.getData().add(new XYChart.Data<Number, Number>(2, 114));
        series.getData().add(new XYChart.Data<Number, Number>(3, 15));
        series.getData().add(new XYChart.Data<Number, Number>(4, 124));

        
        lineChart.getData().add(series);

        
        
    	
    }
	
}
