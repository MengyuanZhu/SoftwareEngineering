

package helpers;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import com.google.gson.Gson;

import models.StockModel;

/**
 * DataParser is the parser class that takes in JSON data from google finance API
 * and stores it into the StockModel class using GSON
 * @author      Hyeun Kang
 */
public class DataParser {
	boolean error = false;
	
	/**
	 *Creates new StockModel class object called data.
	 *Json file is parsed to data using gson.
	 *
	 */
	public StockModel parseJSON(String string){
		StockModel data = new StockModel();
		if(error == false){
			Gson gson = new Gson();
			data = gson.fromJson(string, StockModel.class);
		}
		return data;
	}
	
	/**
	 * Initiates the connection to google finance URL and reads in the stock information that is in JSON format.
	 */
	public String getJSON(String symbol) {
		HttpURLConnection urlConnection = null;
		StringBuilder result = new StringBuilder();

        try {
            URL url = new URL("http://finance.google.com/finance/info?q=NASDAQ%3A" + symbol);
            urlConnection = (HttpURLConnection) url.openConnection();
            InputStream in = new BufferedInputStream(urlConnection.getInputStream());

            BufferedReader reader = new BufferedReader(new InputStreamReader(in));

            String line;
            while ((line = reader.readLine()) != null) {
                result.append(line);
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
        return result.toString().replaceAll("//", "").replaceAll("\\[", "").replaceAll("\\]", "");
	}
}
