package virtualStock.model;

import java.sql.Timestamp;
/**
 * @author jay
 *
 */
public class Volume{
	Timestamp timeStamp;
	double close;
	double high;
	double low;
	double open;
	int volume;
	public void setTimeStamp(long time){
		this.timeStamp = new Timestamp(time * 1000);
	}
	public Timestamp getTimeStamp(){
		return timeStamp;
	}
	public void setClose(double close){
		this.close = close;
	}
	public double getClose(){
		return close;
	}
	public void setHigh(double high){
		this.high = high;
	}
	public double getHigh(){
		return high;
	}
	public void setLow(double low){
		this.low = low;
	}
	public double getLow(){
		return low;
	}
	public void setOpen(double open){
		this.open = open;
	}
	public double getOpen(){
		return open;
	}
	public void setVolume(int volume){
		this.volume = volume;
	}
	public int getVolume(){
		return volume;
	}
	public void printSeries(){
		System.out.println("Timestamp: " + timeStamp + " Close: " + close + " High: " + high + " Low: " + low + " Open: " + open + " Volume: " + volume);
	}
}