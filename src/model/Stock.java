package virtualStock.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * @author mzhu7
 *
 */
public class Stock {

    private final StringProperty stockName;
    private final StringProperty price;
    private final StringProperty shares;
    private final StringProperty open;
    private final StringProperty todayHigh;
    private final StringProperty todayLow;
    private final StringProperty yearHigh;
    private final StringProperty yearLow;
    private final StringProperty volume;
    private final StringProperty averageVolume;
    private final StringProperty marketCap;
    private final StringProperty PERatio;
    private final StringProperty divYield;

    /**
     * Default constructor.
     */
    public Stock() {
        this(null);
    }
    
    public Stock(String stockName) {
        this.stockName = new SimpleStringProperty(stockName);
        this.price = new SimpleStringProperty("");
        this.shares = new SimpleStringProperty("");
        this.open = new SimpleStringProperty("");
        this.todayHigh = new SimpleStringProperty("");
        this.todayLow = new SimpleStringProperty("");
        this.yearHigh = new SimpleStringProperty("");
        this.yearLow = new SimpleStringProperty("");
        this.volume = new SimpleStringProperty("");
        this.averageVolume = new SimpleStringProperty("");
        this.marketCap = new SimpleStringProperty("");
        this.PERatio = new SimpleStringProperty("");
        this.divYield = new SimpleStringProperty("");
    } 

    public String getStockName() {
        return stockName.get();
    }

    public void setStockName(String stockName) {
        this.stockName.set(stockName);
    }

    public StringProperty stockNameProperty() {
        return stockName;
    }
    
    public String getPrice() {
        return price.get();
    }

    public void setPrice(String price) {
        this.price.set(price);
    }

    public StringProperty priceProperty() {
        return price;
    }
    
    public String getShares() {
        return shares.get();
    }

    public void setShares(String shares) {
        this.shares.set(shares);
    }

    public StringProperty sharesProperty() {
        return shares;
    }
    
    public String getOpen() {
        return open.get();
    }

    public void setOpen(String open) {
        this.open.set(open);
    }

    public StringProperty openProperty() {
        return open;
    }
    
    public String getTodayHigh() {
        return todayHigh.get();
    }

    public void setTodayHigh(String todayHigh) {
        this.todayHigh.set(todayHigh);
    }

    public StringProperty todayHighProperty() {
        return todayHigh;
    }
    
    public String getTodayLow() {
        return todayLow.get();
    }

    public void setTodayLow(String todayLow) {
        this.todayLow.set(todayLow);
    }

    public StringProperty todayLowProperty() {
        return todayLow;
    }
    
    public String getYearHigh() {
        return yearHigh.get();
    }

    public void setYearHigh(String yearHigh) {
        this.yearHigh.set(yearHigh);
    }

    public StringProperty yearHighProperty() {
        return yearHigh;
    }
    
    public String getYearLow() {
        return yearLow.get();
    }

    public void setYearLow(String yearLow) {
        this.yearLow.set(yearLow);
    }

    public StringProperty yearLowProperty() {
        return yearLow;
    }
    
    public String getVolume() {
        return volume.get();
    }

    public void setVolume(String volume) {
        this.volume.set(volume);
    }

    public StringProperty volumeProperty() {
        return volume;
    }
    
    public String getAverageVolume() {
        return averageVolume.get();
    }

    public void setAverageVolume(String averageVolume) {
        this.averageVolume.set(averageVolume);
    }

    public StringProperty averageVolumeProperty() {
        return averageVolume;
    }
    
    public String getMarketCap() {
        return marketCap.get();
    }

    public void setMarketCap(String marketCap) {
        this.marketCap.set(marketCap);
    }

    public StringProperty marketCapProperty() {
        return marketCap;
    }
    
    public String getPERatio() {
        return PERatio.get();
    }

    public void setPERatio(String PERatio) {
        this.PERatio.set(PERatio);
    }

    public StringProperty PERatioProperty() {
        return PERatio;
    }
    
    public String getDivYield() {
        return divYield.get();
    }

    public void setDivYield(String divYield) {
        this.divYield.set(divYield);
    }

    public StringProperty divYieldProperty() {
        return divYield;
    }   
}