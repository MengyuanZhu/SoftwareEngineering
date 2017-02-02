package virtualStock.model;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


/**
 * @author mzhu7
 *
 */
@XmlRootElement(name = "persons")
public class StockListWrapper {

    private List<Stock> persons;

    @XmlElement(name = "person")
    public List<Stock> getPersons() {
        return persons;
    }

    public void setPersons(List<Stock> persons) {
        this.persons = persons;
    }
}