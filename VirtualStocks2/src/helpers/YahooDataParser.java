package helpers;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Timestamp;
import java.util.ArrayList;

import com.google.gson.Gson;

import models.StockModel;
import models.Volume;
import models.YahooStockModel;


public class YahooDataParser {
	boolean error = false;
	
	public String getValue(String line){
		String value = line.substring(line.indexOf(':') + 1, line.length());
		return value;
	}
	
	public YahooStockModel getJSON(String symbol) {
		HttpURLConnection urlConnection = null;
		StringBuilder result = new StringBuilder();
		ArrayList<String> data = new ArrayList<String>();

        try {
            URL url = new URL("http://chartapi.finance.yahoo.com/instrument/1.0/" + symbol + "/chartdata;type=quote;range=1d/csv");
            urlConnection = (HttpURLConnection) url.openConnection();
            InputStream in = new BufferedInputStream(urlConnection.getInputStream());

            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            String line;
            while ((line = reader.readLine()) != null) {
                result.append(line);
                data.add(line);
            }
        }
        catch(IOException e){
        	error = true;
        	System.out.println("Error: Please enter a correct ticker symbol.");
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        finally {
            urlConnection.disconnect();
        }
        // Yahoo Stocks CSV Parser
        YahooStockModel model = new YahooStockModel();
        if(!error){
        	try{
		        model.setTicker(getValue(data.get(1)));
		        model.setCompanyName(getValue(data.get(2)));
		        model.setExchangeName(getValue(data.get(3)));
		        model.setUnit(getValue(data.get(4)));
		        model.setTimeZone(getValue(data.get(5)));
		        model.setCurrency(getValue(data.get(6)));
		        model.setGmtOffSet(Integer.parseInt(getValue(data.get(7))));
		        model.setPreviousClose(Double.parseDouble(getValue(data.get(8))));
		        model.setMinTimeStamp((Long.parseLong(getValue(data.get(9)).split(",")[0])));
		        model.setMaxTimeStamp((Long.parseLong(getValue(data.get(9)).split(",")[1])));
		        for(int i = 0; i < data.get(10).split(",").length-1; i++){
		        	model.addLabel(Long.parseLong(getValue(data.get(10).split(",")[i])));
		        }
		        for(int i = 17; i < data.size(); i++){
		        	Volume volume = new Volume();
		        	volume.setTimeStamp(Long.parseLong(data.get(i).split(",")[0]));
		        	volume.setClose(Double.parseDouble(data.get(i).split(",")[1]));
		        	volume.setHigh(Double.parseDouble(data.get(i).split(",")[2]));
		        	volume.setLow(Double.parseDouble(data.get(i).split(",")[3]));
		        	volume.setOpen(Double.parseDouble(data.get(i).split(",")[4]));
		        	volume.setVolume(Integer.parseInt(data.get(i).split(",")[5]));
		        	model.addSeries(volume);
		        }
        	}
        	catch(Exception e){
        		model = new YahooStockModel();
        	}
        }
        return model;
	}
}
