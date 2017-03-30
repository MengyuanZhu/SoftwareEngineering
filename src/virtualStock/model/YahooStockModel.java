package virtualStock.model;

import java.sql.Timestamp;
import java.util.ArrayList;
/**
 * @author jay
 *
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
	public String getTicker(){
		return ticker;
	}
	public void setTicker(String ticker){
		this.ticker = ticker;
	}
	public String getCompanyName(){
		return companyName;
	}
	public void setCompanyName(String name){
		this.companyName = name;
	}
	public String getExchangeName(){
		return exchangeName;
	}
	public void setExchangeName(String name){
		this.exchangeName = name;
	}
	public String getUnit(){
		return unit;
	}
	public void setUnit(String unit){
		this.unit = unit;
	}
	public String getTimeZone(){
		return timeZone;
	}
	public void setTimeZone(String timeZone){
		this.timeZone = timeZone;
	}
	public String getCurrency(){
		return currency;
	}
	public void setCurrency(String currency){
		this.currency = currency;
	}
	public int getGmtOffset(){
		return gmtOffSet;
	}
	public void setGmtOffSet(int offset){
		this.gmtOffSet = offset;
	}
	public double getPreviousClose(){
		return previousClose;		
	}
	public void setPreviousClose(double close){
		this.previousClose = close;
	}
	public Timestamp getMinTimeStamp(){
		return minTimeStamp;
	}
	public void setMinTimeStamp(long time){
		this.minTimeStamp = new Timestamp(time * 1000);
	}
	public Timestamp getMaxTimeStamp(){
		return maxTimeStamp;
	}
	public void setMaxTimeStamp(long time){
		this.maxTimeStamp = new Timestamp(time * 1000);
	}
	public ArrayList<Long> getLabels(){
		return labels;
	}
	public void addLabel(long label){
		this.labels.add(label);
	}
	public ArrayList<Volume> getSeries(){
		return series;
	}
	public void addSeries(Volume volume){
		this.series.add(volume);
	}
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