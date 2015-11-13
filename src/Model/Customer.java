package Model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * Created by ������ on 27.10.2015.
 */

@XmlType( propOrder = {"name","phonenum","adress"})
public class Customer extends ModelItem{
    private String name;
    String phonenum;
    String adress;

    public Customer(){}

    @XmlElement(name = "Name")
    public void setName(String name){
        this.name = name;
    }

    @XmlElement(name = "Phone_number")
    public void setPhonenum(String phonenum){
        this.phonenum = phonenum;
    }

    @XmlElement(name = "Adress")
    public void setAdress(String adress){
        this.adress = adress;
    }

    public String getName(){
        return  name;
    }

    public String getPhonenum(){
        return phonenum;
    }

    public String getAdress(){
        return adress;
    }

//    @Override
//    public int hashCode() {
//        return 0;
//    }

    @Override
    public boolean equals(Object a) {
        if (a == null){return false;}
        if (a == this){return true;}
        if (getClass()!=a.getClass()) {return  false;}
        Customer o = (Customer)a;
        return (this.getNumber() == o.getNumber() && this.getName().equals(o.getName()) && this.getPhonenum().equals(o.getPhonenum()) && this.getAdress().equals(o.getAdress()));
    }

    @Override
    public String toString() {
        StringBuilder strbuild = new StringBuilder();
        return strbuild.append("ID ")
                .append(this.getNumber())
                .append("; Name ")
                .append(this.getName())
                .append("; Phone number ")
                .append(this.getPhonenum())
                .append("; Adress ")
                .append(this.getAdress())
                .toString();
    }
}