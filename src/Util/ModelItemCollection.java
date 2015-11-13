package Util;

import Model.Customer;
import Model.ModelItem;
import Model.Order;
import Model.Tariff;

import javax.xml.bind.annotation.*;
import java.util.HashMap;

/**
 * Created by Кирилл on 01.11.2015.
 */

@XmlRootElement(name = "Collection")
@XmlSeeAlso({Tariff.class, Customer.class, Order.class})
public class ModelItemCollection<T extends ModelItem> {

    private HashMap<Integer, T> ModIt= new HashMap<>();

    public ModelItemCollection(){}

    public void setModIt(HashMap<Integer, T> ModIt){
        this.ModIt = ModIt;
    }

    @XmlElement(name = "Model")
    public HashMap<Integer, T> getModIt(){
        return (HashMap<Integer, T>)ModIt;
    }
}
