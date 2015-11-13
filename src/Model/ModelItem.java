package Model;


/**
 * Created by Кирилл on 27.10.2015.
 */

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlType( propOrder = {"number"})
public abstract class ModelItem {
    private int number;

    public ModelItem(){}

    @XmlElement(name = "Number")
    public void setNumber(int number){
        this.number = number;
    }

    public int getNumber(){
        return number;
    }

//    @Override
//    public abstract int hashCode();

    @Override
    public abstract boolean equals(Object a);

    @Override
    public abstract String toString();

}
