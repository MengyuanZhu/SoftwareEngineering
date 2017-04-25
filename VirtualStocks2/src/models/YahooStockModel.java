
package models;

import java.sql.Timestamp;
import java.util.ArrayList;

/**
 * YahooStockModel is a class that holds the stock information from yahoo finance API parsed by YahooDataParser.
 * The class includes functions:
 * <ul>
 * <li>get and set ticker
 * <li>get and set company name
 * <li>get and set exchange name
 * <li>get and set unit
 * <li>get and set time zone
 * <li>get and set currency
 * <li>get and set GMT offset
 * <li>get and set previous close
 * <li>get and set Min Time stamp
 * <li>get and set Max Time stamp
 * <li>get and add labels 
 * <li>get and add series
 * <li>prints out the stock information
 * </ul>
 * @author      Hyeun Kang
 */
public class YahooStockModel {
	String ticker;
	String companyName;
	String exchangeName;
	String unit;
	String timeZone;
	String currency;
	int gmtOffSet;
	double previousClose;
	Timestamp minTimeStamp;
	Timestamp maxTimeStamp;
	ArrayList<Long> labels = new ArrayList<Long>();
	ArrayList<Volume> series = new ArrayList<Volume>();
	
	/**
	 * Getter for Ticker
	 */
	public String getTicker(){
		return ticker;
	}
	
	/**
	 * Setter for Ticker
	 */
	public void setTicker(String ticker){
		this.ticker = ticker;
	}
	
	/**
	 * Getter for company name
	 */
	public String getCompanyName(){
		return companyName;
	}
	
	/**
	 * Setter for company name
	 */
	public void setCompanyName(String name){
		this.companyName = name;
	}
	
	/**
	 * Getter for exchange name
	 */
	public String getExchangeName(){
		return exchangeName;
	}
	
	/**
	 * Setter for exchange name
	 */
	public void setExchangeName(String name){
		this.exchangeName = name;
	}
	
	/**
	 * Getter for Unit
	 */
	public String getUnit(){
		return unit;
	}
	
	/**
	 * Setter for Unit
	 */
	public void setUnit(String unit){
		this.unit = unit;
	}
	
	/**
	 * Getter for time zone
	 */
	public String getTimeZone(){
		return timeZone;
	}
	
	/**
	 * Setter for time zone
	 */
	public void setTimeZone(String timeZone){
		this.timeZone = timeZone;
	}
	
	/**
	 * Getter for currency
	 */
	public String getCurrency(){
		return currency;
	}
	
	/**
	 * Setter for currency
	 */
	public void setCurrency(String currency){
		this.currency = currency;
	}
	
	/**
	 * Getter for GMT offset
	 */
	public int getGmtOffset(){
		return gmtOffSet;
	}
	
	/**
	 * Setter for GMT offset
	 */
	public void setGmtOffSet(int offset){
		this.gmtOffSet = offset;
	}
	
	/**
	 * Getter for previousclose
	 */
	public double getPreviousClose(){
		return previousClose;		
	}
	
	/**
	 * Setter for previous close
	 */
	public void setPreviousClose(double close){
		this.previousClose = close;
	}
	
	/**
	 * Getter for Min time stamp
	 */
	public Timestamp getMinTimeStamp(){
		return minTimeStamp;
	}
	
	/**
	 * Setter for Min time stamp
	 */
	public void setMinTimeStamp(long time){
		this.minTimeStamp = new Timestamp(time * 1000);
	}
	
	/**
	 * Getter for Max time stamp
	 */
	public Timestamp getMaxTimeStamp(){
		return maxTimeStamp;
	}
	
	/**
	 * Setter for Max time stamp
	 */
	public void setMaxTimeStamp(long time){
		this.maxTimeStamp = new Timestamp(time * 1000);
	}
	
	/**
	 * Getter for labels from arraylist named 'labels'
	 */
	public ArrayList<Long> getLabels(){
		return labels;
	}
	
	/**
	 * Adds label to the arraylist named 'labels'
	 */
	public void addLabel(long label){
		this.labels.add(label);
	}
	
	/**
	 * Getter for volume from arraylist 'series'
	 */
	public ArrayList<Volume> getSeries(){
		return series;
	}
	
	/**
	 * Adds volume to the arraylist named 'series'
	 */
	public void addSeries(Volume volume){
		this.series.add(volume);
	}
	
	/**
	 * prints out all information from above methods for the stock parsed by 
	 * YahooDataParser from yahoo finance API
	 */
	public void printStockModel(){
		System.out.println("Ticker: " + this.ticker);
		System.out.println("Company Name: " + this.companyName);
		System.out.println("Exchange Name: " + this.exchangeName);
		System.out.println("Unit: " + this.unit);
		System.out.println("Time Zone: " + this.timeZone);
		System.out.println("Currency: " + this.currency);
		System.out.println("GMT Off Set: " + this.gmtOffSet);
		System.out.println("Previous Close: " + this.previousClose);
		System.out.println("Min Time Stamp: " + this.minTimeStamp);
		System.out.println("Max Time Stamp: " + this.maxTimeStamp);
		System.out.print("Labels: ");
		for(int i = 0; i < labels.size(); i++){
			System.out.print(this.labels.get(i) + " ");
		}
		System.out.println("");
		System.out.println("Series: ");
		for(int i = 0; i < series.size(); i++){
			this.series.get(i).printSeries();
		}
	}
}