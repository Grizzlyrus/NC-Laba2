package Controller;

import Model.*;
import Util.ModelFacade;
import Util.ModelItemCollection;
import View.View;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * Created by ������ on 27.10.2015.
 */
public class Controller {
    private View view;
    private ModelFacade modelFacade;
    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    private static final int MAIN_MENU=1;
    private static final int TABLE_MENU=2;
    private static final int ACTION_MENU=3;
    private static final int SHOW_MENU=4;
    private static final int ADD_MENU=5;
    private static final int REDACT_MENU=6;
    private static final int DELETE_MENU=7;
    private static final int SELECT_MENU=9;
    private static final int FIND_MENU=8;
    private static final int BACK_MENU=10;

    private static final int ORDER_TABLE=1;
    private static final int TARIFF_TABLE=3;
    private static final int CUSTOMER_TABLE=2;

    private static final String MAIN_MENU_STRING ="[1] Select table\n[2] Show all tables\n[3] Exit";
    private static final String TABLE_MENU_STRING="[1] Order\n[2] Customer\n[3] Tariff\n[4] <-- Back";
    private static final String ACTION_MENU_STRING="[1] Show table\n[2] Add row\n[3] Redact row\n[4] Delete row\n" +
            "[5] Find rows\n[6] Select row\n[7] <-- Back";

    private int currentMenu;
    private int currentTable;

    public Controller() {
        view=new View(this);
        currentMenu=MAIN_MENU;
        modelFacade=ModelFacade.getInstance();
    }

    public void run(){
    view.print(MAIN_MENU_STRING);
    view.read();
}

public void analysis(String s){
    try {
        switch (currentMenu) {
            case MAIN_MENU:
                executeMain(s);
                break;
            case TABLE_MENU:
                executeTable(s);
                break;
            case ACTION_MENU:
                executeAction(s);
                break;
            case SHOW_MENU:

                break;
            case ADD_MENU:

                break;
            case REDACT_MENU:

                break;
            case DELETE_MENU:

                break;
            case FIND_MENU:

                break;
            case SELECT_MENU:

                break;
            case BACK_MENU:

                break;
        }
    }catch (Exception e){
        view.print(e.getMessage());
        currentMenu=MAIN_MENU;
        view.print(MAIN_MENU_STRING);
    }
}

    private void executeMain(String s) {
        checkNumberInput(s, 3);
        switch (Integer.parseInt(s)){
            case 1:
                currentMenu=TABLE_MENU;
                view.print(TABLE_MENU_STRING);
                break;
            case 2:
                showAllTables();
                view.print(MAIN_MENU_STRING);
                break;
            case 3:
                System.exit(0);
                break;
        }
    }

    private void executeTable(String s) {
        checkNumberInput(s, 4);
        switch (Integer.parseInt(s)){
            case 1:
                currentMenu=ACTION_MENU;
                currentTable =ORDER_TABLE;
                view.print(ACTION_MENU_STRING);
                break;
            case 2:
                currentMenu=ACTION_MENU;
                currentTable =CUSTOMER_TABLE;
                view.print(ACTION_MENU_STRING);
                break;
            case 3:
                currentMenu=ACTION_MENU;
                currentTable =TARIFF_TABLE;
                view.print(ACTION_MENU_STRING);
                break;
            case 4:
                currentMenu=MAIN_MENU;
                view.print(MAIN_MENU_STRING);
                break;
        }
    }

    private void executeAction(String s) {
        checkNumberInput(s,7);
        switch (Integer.parseInt(s)){
            case 1:
                //currentMenu=SHOW_MENU;
                showTable();
                break;
            case 2:
                //currentMenu=ADD_MENU;
                addRow();
                break;
            case 3:
                redactRow();
                break;
            case 4:
                deleteRow();
                break;
            case 5:
                findRow();
                break;
            case 6:
                selectRow();
                break;
            case 7:
                currentMenu=TABLE_MENU;
                view.print(TABLE_MENU_STRING);
                break;
        }
    }

    private void showAllTables() {
        view.print("Orders:");
        view.print(show(modelFacade.getOrders()));
        view.print("Customers:");
        view.print(show(modelFacade.getCustomers()));
        view.print("Tariffs:");
        view.print(show(modelFacade.getTariffs()));
    }

    private void showTable() {
        switch (currentTable){
            case ORDER_TABLE:
                view.print("Orders:");
                view.print(show(modelFacade.getOrders()));
                break;
            case CUSTOMER_TABLE:
                view.print("Customers:");
                view.print(show(modelFacade.getCustomers()));
                break;
            case TARIFF_TABLE:
                view.print("Tariffs:");
                view.print(show(modelFacade.getTariffs()));
                break;
        }
        view.print("-------------------");
        view.print("Press any key");
        view.readAtr();
        view.print(ACTION_MENU_STRING);
    }

    private void addRow() {
        switch (currentTable){
            case ORDER_TABLE:
                addOrder();
                break;
            case CUSTOMER_TABLE:
                addCustomer();
                break;
            case TARIFF_TABLE:
                addTariff();
                break;
        }
        view.print("-------------------");
        view.print("Press any key");
        view.readAtr();
        view.print(ACTION_MENU_STRING);
    }

    private void redactRow() {
        switch (currentTable){
            case ORDER_TABLE:
                redactOrder();
                break;
            case CUSTOMER_TABLE:
                redactCustomer();
                break;
            case TARIFF_TABLE:
                redactTariff();
                break;
        }
        view.print("-------------------");
        view.print("Press any key");
        view.readAtr();
        view.print(ACTION_MENU_STRING);
    }

    private void deleteRow() {
        switch (currentTable){
            case ORDER_TABLE:
                deleteOrder();
                break;
            case CUSTOMER_TABLE:
                deleteCustomer();
                break;
            case TARIFF_TABLE:
                deleteTariff();
                break;
        }
        view.print("-------------------");
        view.print("Press any key");
        view.readAtr();
        view.print(ACTION_MENU_STRING);
    }

    private void selectRow() {
        switch (currentTable){
            case ORDER_TABLE:
                showOrder();
                break;
            case CUSTOMER_TABLE:
                showCustomer();
                break;
            case TARIFF_TABLE:
                showTariff();
                break;
        }
    }

    private void findRow() {
        switch (currentTable){
            case ORDER_TABLE:
                findOrder();
                break;
            case CUSTOMER_TABLE:
                findCustomer();
                break;
            case TARIFF_TABLE:
                findTariff();
                break;
        }
    }

    private void findOrder(){
        boolean isFind = false;
        String buffString;
        Pattern p1,p2,p3,p4,p5;
        HashMap<Integer,Order> hm= ModelFacade.getInstance().getOrders().getModIt();
        view.print("Input order ID pattern");
        buffString = view.readAtr();
        p1 = Pattern.compile(buffString);

        view.print("Input customer ID pattern");
        buffString = view.readAtr();
        p2 = Pattern.compile(buffString);

        view.print("Input tariff ID pattern");
        buffString = view.readAtr();
        p3 = Pattern.compile(buffString);

        view.print("Input order date pattern");
        buffString = view.readAtr();
        p4 = Pattern.compile(buffString);

        view.print("Input order sum pattern");
        buffString = view.readAtr();
        p5 = Pattern.compile(buffString);

        for(Integer i: hm.keySet()){
            if (p1.matcher(i.toString()).matches()
                    && p2.matcher(String.valueOf(hm.get(i).getCustomernum())).matches()
                    && p3.matcher(String.valueOf(hm.get(i).getTariffnum())).matches()
                    && p4.matcher(String.valueOf(hm.get(i).getDate())).matches()
                    && p5.matcher(String.valueOf(hm.get(i).getSum())).matches()
                    ){
                isFind = true;
                view.print(hm.get(i).toString());
            }
        }
        if(!isFind){
            view.print("No rows find");
        }
        view.print("Press any key");
        view.readAtr();
        view.print(ACTION_MENU_STRING);
    }

    private void findTariff(){
        boolean isFind = false;
        String buffString;
        Pattern p1,p2,p3,p4;
        HashMap<Integer,Tariff> hm= ModelFacade.getInstance().getTariffs().getModIt();
        view.print("Input tariff ID pattern");
        buffString = view.readAtr();
        p1 = Pattern.compile(buffString);

        view.print("Input tariff name pattern");
        buffString = view.readAtr();
        p2 = Pattern.compile(buffString);

        view.print("Input tariff cost pattern");
        buffString = view.readAtr();
        p3 = Pattern.compile(buffString);

        view.print("Input tariff speed pattern");
        buffString = view.readAtr();
        p4 = Pattern.compile(buffString);

        for(Integer i: hm.keySet()){
            if (p1.matcher(i.toString()).matches()
                    && p2.matcher(hm.get(i).getName()).matches()
                    && p3.matcher(String.valueOf(hm.get(i).getCost())).matches()
                    && p4.matcher(String.valueOf(hm.get(i).getSpeed())).matches())
            {
            isFind = true;
            view.print(hm.get(i).toString());
            }
        }
        if(!isFind){
            view.print("No rows find");
        }
        view.print("Press any key");
        view.readAtr();
        view.print(ACTION_MENU_STRING);
    }

    private void findCustomer(){
        boolean isFind = false;
        String buffString;
        Pattern p1,p2,p3,p4;
        HashMap<Integer,Customer> hm= ModelFacade.getInstance().getCustomers().getModIt();
        view.print("Input customer ID pattern");
        buffString = view.readAtr();
        p1 = Pattern.compile(buffString);

        view.print("Input customer name pattern");
        buffString = view.readAtr();
        p2 = Pattern.compile(buffString);

        view.print("Input phone number pattern");
        buffString = view.readAtr();
        p3 = Pattern.compile(buffString);

        view.print("Input adress pattern");
        buffString = view.readAtr();
        p4 = Pattern.compile(buffString);

        for(Integer i: hm.keySet()){
            if (p1.matcher(i.toString()).matches()
                    && p2.matcher(hm.get(i).getName()).matches()
                    && p3.matcher(String.valueOf(hm.get(i).getPhonenum())).matches()
                    && p4.matcher(hm.get(i).getAdress()).matches())
            {
                isFind = true;
                view.print(hm.get(i).toString());
            }
        }
        if(!isFind){
            view.print("No rows find");
        }
        view.print("Press any key");
        view.readAtr();
        view.print(ACTION_MENU_STRING);
    }

    private void addOrder() {
        Order order =new Order();
        String buffString;
        int number=0;
        boolean isNum;
        boolean isAvailable = false;
        do {
            view.print("Input order ID");
            buffString=view.readAtr();
            isNum = isNumber(buffString);
            if (isNum){
                number=Integer.parseInt(buffString);
                if (number == 0) {
                    return;
                }
                isAvailable = checkId(number,modelFacade.getOrders());
                if (!isAvailable) {
                    view.print("ID " + number + " already exist");
                }
            }
        }while (!(isNum&&isAvailable));

        order.setNumber(number);

        do {
            view.print("Input customer ID");
            buffString=view.readAtr();
            isNum = isNumber(buffString);
            if (isNum){
                number=Integer.parseInt(buffString);
                if (number == 0) {
                    return;
                }
                isAvailable = checkId(number,modelFacade.getCustomers());
                if (isAvailable) {
                    view.print("Customer ID " + number + " is not exist");
                }
            }
        }while (!(isNum&&!isAvailable));

        order.setCustomernum(number);

        do {
            view.print("Input tariff ID");
            buffString = view.readAtr();
            isNum = isNumber(buffString);
            if (isNum) {
                number = Integer.parseInt(buffString);
                if (number == 0) {
                    return;
                }
                isAvailable = checkId(number, modelFacade.getTariffs());
                if (isAvailable) {
                    view.print("Tariff ID " + number + "is not exist");
                }
            }
        }while (!(isNum&&!isAvailable));

        order.setTariffnum(number);


        do {
            view.print("Input date");
            buffString=view.readAtr();
            if ((isNumber(buffString))&&(Integer.parseInt(buffString) == 0)) {
                return;
            }
        }while (checkDate(buffString));

        try {
            order.setDate(dateFormat.parse(buffString));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        order.setSum(modelFacade.getTariffById(order.getTariffnum()).getCost());

        modelFacade.addOrder(order);
        view.print("Order created");
    }

    private void redactOrder() {
        String buffString ;
        int number=0;
        boolean isNum;
        boolean isAvailable=false;
        do{
            view.print("Input order ID");
            buffString=view.readAtr();
            isNum = isNumber(buffString);
            if (isNum){
                number=Integer.parseInt(buffString);
                if (number == 0) {
                    return;
                }
                isAvailable = checkId(number,modelFacade.getOrders());
                if (isAvailable) {
                    view.print("Order ID " + number + " is not exist");
                }
            }
        }while (!(isNum&&!isAvailable));
        Order order=modelFacade.getOrderById(Integer.parseInt(buffString));
        view.print("Order\nID: "+order.getNumber()+
                "\nCustomer: "+modelFacade.getCustomerById(order.getCustomernum())+
                "\nTariff: "+modelFacade.getTariffById(order.getTariffnum())+
                "\nDate " + order.getDate()+
                "\nSum: " + order.getSum());

        view.print("-----------------------");

        boolean isChange=true;
        do {
            view.print("Input customer ID");
            buffString=view.readAtr();
            if (buffString.trim().equals("")) {
                isChange=false;
                break;
            }
            isNum = isNumber(buffString);
            if (isNum){
                number=Integer.parseInt(buffString);
                if (number == 0) {
                    return;
                }
                isAvailable = checkId(number,modelFacade.getCustomers());
                if (isAvailable) {
                    view.print("Customer ID " + number + " is not exist");
                }
            }
        }while (!(isNum&&!isAvailable));
        if (isChange) {
            order.setCustomernum(number);
        }
        isChange=true;
        do {
            view.print("Input tariff ID");
            buffString = view.readAtr();
            if (buffString.trim().equals("")) {
                isChange=false;
                break;
            }
            isNum = isNumber(buffString);
            if (isNum) {
                number = Integer.parseInt(buffString);
                if (number == 0) {
                    return;
                }
                isAvailable = checkId(number, modelFacade.getTariffs());
                if (isAvailable) {
                    view.print("Tariff ID " + number + "is not exist");
                }
            }
        }while (!(isNum&&!isAvailable));
        if (isChange) {
            order.setTariffnum(number);
        }
        isChange=true;

        do {
            view.print("Input date");
            buffString=view.readAtr();
            if (buffString.trim().equals("")) {
                isChange=false;
                break;
            }
            if ((isNumber(buffString))&&(Integer.parseInt(buffString) == 0)) {
                return;
            }
        }while (checkDate(buffString));
        if (isChange) {
            try {
                order.setDate(dateFormat.parse(buffString));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        order.setSum(modelFacade.getTariffById(order.getTariffnum()).getCost());
        modelFacade.addOrder(order);
        view.print("Order changed");

    }

    private void deleteOrder() {
        String buffString;
        boolean isNum;
        int number=0;
        boolean isAvailable=false;
        do {
            view.print("Input order ID");
            buffString=view.readAtr();
            isNum=isNumber(buffString);
            if (isNum) {
                number=Integer.parseInt(buffString);
                if (number == 0) {
                    return;
                }
                isAvailable = checkId(number,modelFacade.getOrders());
                if (isAvailable) {
                    view.print("This order ID: " + number + " is not exist");
                }
            }
        }while (!(isNum&&!isAvailable));
        Order order=modelFacade.getOrderById(Integer.parseInt(buffString));
        view.print("Order id: "+order.getNumber()+
                "\nCustomer: "+modelFacade.getCustomerById(order.getCustomernum())+
                "\nTariff: "+modelFacade.getTariffById(order.getTariffnum())+
                "\nDate " + order.getDate()+
                "\nSum: " + order.getSum());
        view.print("Do you want to delete this order?");
        view.print("[1] Yes\n[2] No");
        do{
            buffString=view.readAtr();
            isNum=isNumber(buffString);
            if (isNum) {
                number=Integer.parseInt(buffString);
                switch (number){
                    case 1:
                        modelFacade.removeOrder(order.getNumber());
                        break;
                    case 2:
                        return;
                    default:
                        isNum=false;
                        break;
                }
            }
        }while (!isNum);

    }

    private void addCustomer() {
        Customer customer=new Customer();
        String buffString;
        int number=0;
        boolean isNum;
        boolean isAvailable=false;
        do {
            view.print("Input customer ID");
            buffString=view.readAtr();
            isNum = isNumber(buffString);
            if (isNum){
                number=Integer.parseInt(buffString);
                if (number == 0) {
                    return;
                }
                isAvailable = checkId(number,modelFacade.getCustomers());
                if (!isAvailable) {
                    view.print("ID: " + number + " is already exist");
                }
            }
        }while (!(isNum&&isAvailable));

        customer.setNumber(number);

        do {
            view.print("Input customer name");
            buffString=view.readAtr();
            if ((isNumber(buffString))&&(Integer.parseInt(buffString) == 0)) {
                return;
            }
        }while (checkName(buffString));

        customer.setName(buffString);

        do {
            view.print("Input customer phone number");
            buffString=view.readAtr();
            if ((isNumber(buffString))&&(Integer.parseInt(buffString) == 0)) {
                return;
            }
        }while (checkPhone(buffString));

        customer.setPhonenum(buffString);

        do {
            view.print("Input customer address");
            buffString=view.readAtr();
            if ((isNumber(buffString))&&(Integer.parseInt(buffString) == 0)) {
                return;
            }
        }while (checkName(buffString));

        customer.setAdress(buffString);

        modelFacade.addCustomer(customer);
        view.print("Customer created");

    }

    private void redactCustomer() {
        String buffString ;
        int number=  0;
        boolean isNum;
        boolean isAvailable=false;
        do{
            view.print("Input customer ID");
            buffString=view.readAtr();
            isNum = isNumber(buffString);
            if (isNum){
                number=Integer.parseInt(buffString);
                if (number == 0) {
                    return;
                }
                isAvailable = checkId(number,modelFacade.getCustomers());
                if (isAvailable) {
                    view.print("Customer ID: " + number + " is not exist");
                }
            }
        }while (!(isNum&&!isAvailable));
        Customer customer=modelFacade.getCustomerById(Integer.parseInt(buffString));
        view.print("Customer\nID: "+customer.getNumber()+
                "\nName:"+customer.getName()+
                "\nPhone: "+customer.getPhonenum()+
                "\nAddress: " +customer.getAdress());
        view.print("-----------------------");

        boolean isChange=true;
        do {
            view.print("Input customer name");
            buffString=view.readAtr();
            if (buffString.trim().equals("")) {
                isChange=false;
                break;
            }
            if ((isNumber(buffString))&&(Integer.parseInt(buffString) == 0)) {
                return;
            }
        }while (checkName(buffString));
        if (isChange) {
            customer.setName(buffString);
        }
        isChange=true;
        do {
            view.print("Input customer phone number");
            buffString=view.readAtr();
            if (buffString.trim().equals("")) {
                isChange=false;
                break;
            }
            if ((isNumber(buffString))&&(Integer.parseInt(buffString) == 0)) {
                return;
            }
        }while (checkPhone(buffString));
        if (isChange) {
            customer.setPhonenum(buffString);
        }
        isChange=true;
        do {
            view.print("Input customer address");
            buffString=view.readAtr();
            if (buffString.trim().equals("")) {
                isChange=false;
                break;
            }
            if ((isNumber(buffString))&&(Integer.parseInt(buffString) == 0)) {
                return;
            }
        }while (checkName(buffString));
        if (isChange) {
            customer.setAdress(buffString);

        }

        modelFacade.addCustomer(customer);
        view.print("Customer changed");
    }

    private void deleteCustomer() {
        String buffString;
        boolean isNum;
        int number=0;
        boolean isAvailable=false;
        do {
            view.print("Input customer ID");
            buffString=view.readAtr();
            isNum=isNumber(buffString);
            if (isNum) {
                number=Integer.parseInt(buffString);
                if (number == 0) {
                    return;
                }
                isAvailable = checkId(number,modelFacade.getCustomers());
                if (isAvailable) {
                    view.print("This customer ID: " + number + " not exist");
                }
            }
        }while (!(isNum&&!isAvailable));
        Customer customer=modelFacade.getCustomerById(Integer.parseInt(buffString));
        view.print("Customer\nId: " + customer.getNumber() +
                "\nName:" + customer.getName() +
                "\nPhone: " + customer.getPhonenum() +
                "\nAddress: " + customer.getAdress());
        view.print("Do you want to delete this customer?");
        view.print("[1] Yes\n[2] No");
        do{
            buffString=view.readAtr();
            isNum=isNumber(buffString);
            if (isNum) {
                number=Integer.parseInt(buffString);
                switch (number){
                    case 1:
                        modelFacade.removeCustomer(customer.getNumber());
                        break;
                    case 2:
                        return;
                    default:
                        isNum=false;
                        break;
                }
            }
        }while (!isNum);
    }

    private void addTariff() {
        Tariff tariff=new Tariff();
        String buffString;
        int number=0;
        boolean isNum;
        boolean isAvailable=false;
        do {
            view.print("Input tariff ID");
            buffString=view.readAtr();
            isNum = isNumber(buffString);
            if (isNum){
                number=Integer.parseInt(buffString);
                if (number == 0) {
                    return;
                }
                isAvailable = checkId(number,modelFacade.getTariffs());
                if (!isAvailable) {
                    view.print("ID: " + number + " is already exist");
                }
            }
        }while (!(isNum&&isAvailable));
        tariff.setNumber(number);

        do {
            view.print("Input tariff name");
            buffString=view.readAtr();
            if ((isNumber(buffString))&&(Integer.parseInt(buffString) == 0)) {
                return;
            }
        }while (checkName(buffString));

        tariff.setName(buffString);

        do {
            view.print("Input tariff speed (Mb/s)");
            buffString=view.readAtr();
            if ((isNumber(buffString))&&(Integer.parseInt(buffString) == 0)) {
                return;
            }
        }while (checkDouble(buffString));

        tariff.setSpeed(Double.parseDouble(buffString));

        do {
            view.print("Input tariff cost");
            buffString=view.readAtr();
            if ((isNumber(buffString))&&(Integer.parseInt(buffString) == 0)) {
                return;
            }
        }while (checkDouble(buffString));

        tariff.setCost(Double.parseDouble(buffString));

        modelFacade.addTariff(tariff);

        view.print("Tariff created");
    }

    private void redactTariff() {
        String buffString ;
        int number=0;
        boolean isNum;
        boolean isAvailable=false;
        do{
            view.print("Input tariff ID");
            buffString=view.readAtr();
            isNum = isNumber(buffString);
            if (isNum){
                number=Integer.parseInt(buffString);
                if (number == 0) {
                    return;
                }
                isAvailable = checkId(number,modelFacade.getTariffs());
                if (isAvailable) {
                    view.print("Tariff ID: " + number + " is not exist");
                }
            }
        }while (!(isNum&&!isAvailable));
        Tariff tariff=modelFacade.getTariffById(Integer.parseInt(buffString));
        view.print("Tariff\nId: "+tariff.getNumber()+
                "\nName:"+tariff.getName()+
                "\nSpeed: "+tariff.getSpeed()+
                "\nCost: " +tariff.getCost());
        view.print("-----------------------");

        boolean isChange=true;
        do {
            view.print("Input tariff name");
            buffString=view.readAtr();
            if (buffString.trim().equals("")) {
                isChange=false;
                break;
            }
            if ((isNumber(buffString))&&(Integer.parseInt(buffString) == 0)) {
                return;
            }
        }while (checkName(buffString));
        if (isChange) {
            tariff.setName(buffString);
        }
        isChange=true;
        do {
            view.print("Input tariff speed (Mb/s)");
            buffString=view.readAtr();
            if (buffString.trim().equals("")) {
                isChange=false;
                break;
            }
            if ((isNumber(buffString))&&(Integer.parseInt(buffString) == 0)) {
                return;
            }
        }while (checkDouble(buffString));
        if (isChange) {
            tariff.setSpeed(Double.parseDouble(buffString));
        }
        isChange=true;
        do {
            view.print("Input tariff cost");
            buffString=view.readAtr();
            if (buffString.trim().equals("")) {
                isChange=false;
                break;
            }
            if ((isNumber(buffString))&&(Integer.parseInt(buffString) == 0)) {
                return;
            }
        }while (checkDouble(buffString));
        if (isChange) {
            tariff.setCost(Double.parseDouble(buffString));

        }
        modelFacade.addTariff(tariff);

        view.print("Tariff changed");
    }

    private void deleteTariff() {
        String buffString;
        boolean isNum;
        int number=0;
        boolean isAvailable=false;
        do {
            view.print("input id tariff");
            buffString=view.readAtr();
            isNum=isNumber(buffString);
            if (isNum) {
                number=Integer.parseInt(buffString);
                if (number == 0) {
                    return;
                }
                isAvailable = checkId(number,modelFacade.getTariffs());
                if (isAvailable) {
                    view.print("This id tariff not exist");
                }
            }
        }while (!(isNum&&!isAvailable));
        Tariff tariff=modelFacade.getTariffById(Integer.parseInt(buffString));
        view.print("Tariff\nId: "+tariff.getNumber()+
                "\nName:"+tariff.getName()+
                "\nSpeed: "+tariff.getSpeed()+
                "\nCost: " +tariff.getCost());
        view.print("Do you want to delete this tariff?");
        view.print("[1] Yes\n[2] No");
        do{
            buffString=view.readAtr();
            isNum=isNumber(buffString);
            if (isNum) {
                number=Integer.parseInt(buffString);
                switch (number){
                    case 1:
                        modelFacade.removeTariff(tariff.getNumber());
                        break;
                    case 2:
                        return;
                    default:
                        isNum=false;
                        break;
                }
            }
        }while (!isNum);
    }

    private void showOrder(){
        String buffString ;
        int number=0;
        boolean isNum;
        boolean isAvailable=false;
        do{
            view.print("Input order ID");
            buffString=view.readAtr();
            isNum = isNumber(buffString);
            if (isNum){
                number=Integer.parseInt(buffString);
                if (number == 0) {
                    return;
                }
                isAvailable = checkId(number,modelFacade.getOrders());
                if (isAvailable) {
                    view.print("Order ID: " + number + " is not exist");
                }
            }
        }while (!(isNum&&!isAvailable));
        Order order=modelFacade.getOrderById(Integer.parseInt(buffString));
        view.print("Order\nID: "+order.getNumber()+
                "\nCustomer: "+modelFacade.getCustomerById(order.getCustomernum())+
                "\nTariff: "+modelFacade.getTariffById(order.getTariffnum())+
                "\nDate " + order.getDate()+
                "\nSum: " + order.getSum());
        view.print("Press any key");
        view.readAtr();
        view.print(ACTION_MENU_STRING);
    }

    private void showCustomer() {
        String buffString ;
        int number=0;
        boolean isNum;
        boolean isAvailable=false;
        do{
            view.print("Input customer ID");
            buffString=view.readAtr();
            isNum = isNumber(buffString);
            if (isNum){
                number=Integer.parseInt(buffString);
                if (number == 0) {
                    return;
                }
                isAvailable = checkId(number,modelFacade.getCustomers());
                if (isAvailable) {
                    view.print("Customer ID: " + number + " is not exist");
                }
            }
        }while (!(isNum&&!isAvailable));
        Customer customer=modelFacade.getCustomerById(Integer.parseInt(buffString));
        view.print("Customer\nID: "+customer.getNumber()+
                "\nName:"+customer.getName()+
                "\nPhone: "+customer.getPhonenum()+
                "\nAddress: " +customer.getAdress());
        view.print("Press any key");
        view.readAtr();
        view.print(ACTION_MENU_STRING);
    }

    private void showTariff() {
        String buffString ;
        int number=0;
        boolean isNum;
        boolean isAvailable=false;
        do{
            view.print("Input tariff ID");
            buffString=view.readAtr();
            isNum = isNumber(buffString);
            if (isNum){
                number=Integer.parseInt(buffString);
                if (number == 0) {
                    return;
                }
                isAvailable = checkId(number,modelFacade.getTariffs());
                if (isAvailable) {
                    view.print("Tariff ID: " + number + " is not exist");
                }
            }
        }while (!(isNum&&!isAvailable));
        Tariff tariff=modelFacade.getTariffById(Integer.parseInt(buffString));
        view.print("Tariff\nId: "+tariff.getNumber()+
                "\nName:"+tariff.getName()+
                "\nSpeed: "+tariff.getSpeed()+
                "\nCost: " +tariff.getCost());
        view.print("Press any key");
        view.readAtr();
        view.print(ACTION_MENU_STRING);
    }

    private void checkNumberInput(String s, int i){
        if ((Integer.parseInt(s)<=0)||(Integer.parseInt(s)>i)){
            throw new IllegalArgumentException("Incorrect input value.\nValue must be between 1 and "+i+".");
        }
    }

    private boolean checkDouble(String buffString) {
        try {
        if (Double.parseDouble(buffString)<0) {
            view.print("Number must be > 0");
            return true;
        }else {
            return false;
        }
        }catch (NumberFormatException e){
            view.print("Input number");
            return true;
        }
    }


    private boolean checkPhone(String buffString) {
        Pattern p1, p2;
        p1 = Pattern.compile("^(8-)?\\d{3}-\\d{3}-\\d{2}-\\d{2}$");
        p2 = Pattern.compile("^(8-)?\\d{10}$");

        if (buffString.trim().equals("")) {
            view.print("Input text should not be empty");
            return true;
        } else if(!p1.matcher(buffString).matches() || !p2.matcher(buffString).matches()){
            view.print("Incorrect phone number format");
            return true;
        }
        return false;
    }

    private boolean checkName(String buffString) {
        if (buffString.trim().equals("")) {
            view.print("Input text should not be empty");
            return true;
        }
        return false;
    }

    private boolean isNumber(String buffString) {
        try {
            int n=Integer.parseInt(buffString);
            if (n <0) {
                view.print("Number must b positive or 0");
                return false;
            }
            return true;
        }catch (NumberFormatException e){
            view.print(e.getMessage());
            return false;
        }
    }

    private boolean checkDate(String buffString) {
        try {
            long delta= Calendar.getInstance().getTimeInMillis()-dateFormat.parse(buffString).getTime();
            if (delta < 0) {
                view.print("Input other date");
                return true;
            }else {
                return false;
            }
        } catch (ParseException e) {
            view.print("Incorrect date format");
            return true;
        }
    }

    private boolean checkId(int id, ModelItemCollection<?extends ModelItem> modelItemCollection) {
         return modelItemCollection.getModIt().get(id) == null;
    }

    private String show(ModelItemCollection< ? extends ModelItem> modelItemCollection) {
        HashMap<Integer,? extends ModelItem> modelItems=modelItemCollection.getModIt();
        StringBuilder s=new StringBuilder();
        for(Map.Entry<Integer,? extends ModelItem> entry: modelItems.entrySet())
        {
            s.append(entry.getValue().toString()).append("\n");
        }

        return s.toString();
    }

    public static void main(String[] args) {
        Controller controller =new Controller();
        controller.run();
    }

}
