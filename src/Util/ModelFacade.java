package Util;

import Model.Customer;
import Model.Order;
import Model.Tariff;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;

/**
 * Created by ������ on 04.11.2015.
 */
public final class ModelFacade {
    private ModelItemCollection<Customer> Customers = new ModelItemCollection<>();
    private ModelItemCollection<Order> Orders = new ModelItemCollection<>();
    private ModelItemCollection<Tariff> Tariffs = new ModelItemCollection<>();

    private static ModelFacade instance = null;

    private ModelFacade(){}

    public static synchronized ModelFacade getInstance(){
        if(instance == null){
            instance = new ModelFacade();
            //TODO check this code
            instance.setOrders(instance.readObjects("src/res/Orders.xml"));
            instance.setCustomers(instance.readObjects("src/res/Customers.xml"));
            instance.setTariffs(instance.readObjects("src/res/Tariffs.xml"));
        }
        return instance;
    }

    public void setCustomers(ModelItemCollection<Customer> Customers){
        this.Customers = Customers;
    }

    public void setOrders(ModelItemCollection<Order> Orders){
        this.Orders = Orders;
    }

    public void setTariffs(ModelItemCollection<Tariff> Tariffs){
        this.Tariffs = Tariffs;
    }

    public ModelItemCollection<Customer> getCustomers(){
        return Customers;
    }

    public ModelItemCollection<Order> getOrders(){
        return Orders;
    }

    public ModelItemCollection<Tariff> getTariffs(){
        return Tariffs;
    }

    public Order getOrderById(int id){
        return Orders.getModIt().get(id);
    }

    public Customer getCustomerById(int id){
        return Customers.getModIt().get(id);
    }

    public Tariff getTariffById(int id){
        return Tariffs.getModIt().get(id);
    }

    public void addOrder(Order order){
        Orders.getModIt().put(order.getNumber(), order);
        writeObjects("src/res/Orders.xml",Orders);
    }

    public void addCustomer(Customer customer){
        Customers.getModIt().put(customer.getNumber(),customer);
        writeObjects("src/res/Customers.xml",Customers);
    }

    public void addTariff(Tariff tariff){
        Tariffs.getModIt().put(tariff.getNumber(),tariff);
        writeObjects("src/res/Tariffs.xml",Tariffs);
    }
    public void removeOrder(int id){
        Orders.getModIt().remove(id);
        writeObjects("src/res/Orders.xml",Orders);
    }
    public void removeCustomer(int id){
        Customers.getModIt().remove(id);
        writeObjects("src/res/Customers.xml",Customers);
    }
    public void removeTariff(int id){
        Tariffs.getModIt().remove(id);
        writeObjects("src/res/Tariffs.xml",Tariffs);
    }
    public void writeObjects(String filename,ModelItemCollection models){
        try {
            File file = new File(filename);

            JAXBContext jaxbContext = JAXBContext.newInstance(ModelItemCollection.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            jaxbMarshaller.marshal(models, file);

        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    public ModelItemCollection readObjects(String filename){
        ModelItemCollection modcoll = new ModelItemCollection();
        try {

            File file = new File(filename);
            JAXBContext jaxbContext = JAXBContext.newInstance(ModelItemCollection.class);

            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            modcoll = (ModelItemCollection) jaxbUnmarshaller.unmarshal(file);

        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return modcoll;
    }

}
