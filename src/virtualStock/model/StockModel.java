package virtualStock.model;
/**
 * @author jay
 *
 */
public class StockModel {
	private String t;
	private String e;
	private double l;
	private String lt;
	
	public String getTicker(){
		return t;
	}
	public void setTicker(String ticker){
		this.t = ticker;
	}
	public String getExchange(){
		return e;
	}
	public void setExchange(String exchange){
		this.e = exchange;
	}
	public double getPrice(){
		return l;
	}
	public void setPrice(int price){
		this.l = price;
	}
	public String getLastTradeTime(){
		return lt;
	}
	public void setLastTradeTime(String lastTradeTime){
		this.lt = lastTradeTime;
	}
	
	public void printInformation(){
		System.out.println("Ticker: " + getTicker());
		System.out.println("Exchange: " + getExchange());
		System.out.println("Last Price: "+ getPrice());
		System.out.println("Last Trade Time: " + getLastTradeTime());
	}
}
