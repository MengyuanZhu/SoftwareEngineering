package virtualStock.model;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


/**
 * @author mzhu7
 *
 */
@XmlRootElement(name = "stocks")
public class StockListWrapper {

    private List<Stock> stocks;

    @XmlElement(name = "stock")
    public List<Stock> getPersons() {
        return stocks;
    }

    public void setPersons(List<Stock> stocks) {
        this.stocks = stocks;
    }
}