package virtualStock.model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author mzhu7
 *
 */
public class StockDayData {

	public static void main(String[] args) throws IOException{
		
		String chartData=getUrlSource("http://chartapi.finance.yahoo.com/instrument/1.0/MSFT/chartdata;type=quote;range=1d/csv");
		int i=0;
		for (String retval: chartData.split("\n")) {
			i=i+1;
			if (i>17){				
				//System.out.println(retval);
				
				Date time = new Date(Long.valueOf(retval.split(",")[0])*1000);
				SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
				System.out.println(sdf.format(time).toString()+" "+retval.split(",")[1]);
			}	         
	      }		
	}
	
	public static String getUrlSource(String url) throws IOException {
        URL yahoo = new URL(url);
        URLConnection yc = yahoo.openConnection();
        BufferedReader in = new BufferedReader(new InputStreamReader(
                yc.getInputStream(), "UTF-8"));
        String inputLine;
        StringBuilder a = new StringBuilder();
        while ((inputLine = in.readLine()) != null){
        	 a.append(inputLine);
        	 a.append("\n");
        }
        in.close();

        return a.toString();
    }
	
	
}
