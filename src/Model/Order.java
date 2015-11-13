package Model;

import Util.DateAdapter;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by ������ on 27.10.2015.
 */

@XmlType( propOrder = {"customernum","tariffnum","date","sum"})
@XmlRootElement(name = "Order")
public class Order extends ModelItem {

    private int customernum;
    private int tariffnum;
    private Date date;
    private double sum;

    public Order(){}

    @XmlElement(name = "Customer_number")
    public void setCustomernum(int customernum){
        this.customernum = customernum;
    }

    @XmlElement(name = "Tariff_number")
    public void setTariffnum(int tariffnum){
        this.tariffnum = tariffnum;
    }

    @XmlElement(name = "Order_date")
    @XmlJavaTypeAdapter(DateAdapter.class)
    public void setDate(Date date){
        this.date = date;
    }

    @XmlElement(name = "Order_sum")
    public void setSum(double sum){
        this.sum = sum;
    }

    public int getCustomernum(){
        return customernum;
    }

    public int getTariffnum(){
        return tariffnum;
    }

    public Date getDate(){
        return date;
    }

    public double getSum(){
        return sum;
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
        Order o = (Order)a;
        return (this.getNumber() == o.getNumber() && this.getCustomernum() == o.getCustomernum() && this.getTariffnum() == o.getTariffnum() && this.getDate() == o.getDate() && this.getSum() == o.getSum());
    }

    @Override
    public String toString() {
        StringBuilder strbuild = new StringBuilder();
        DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
        return strbuild.append("ID ")
                .append(this.getNumber())
                .append("; Customer ID ")
                .append(this.getCustomernum())
                .append("; Tariff ID ")
                .append(this.getTariffnum())
                .append("; Date ")
                .append(df.format(this.getDate()))
                .append("; Sum. of order ")
                .append(this.getSum())
                .toString();
    }
}
