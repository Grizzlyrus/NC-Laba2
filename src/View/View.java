package View;

import Controller.Controller;
import java.util.Scanner;

/**
 * Created by ������ on 27.10.2015.
 */
public class View {
    private Controller controller;
    private Scanner scan;



    public View(Controller controller){
        this.controller = controller;
    }


    public void read(){
        scan=new Scanner(System.in);
        while (true){
            controller.analysis(scan.nextLine());
        }
    }

    public String readAtr(){
        return scan.nextLine();
    }

    public void print(String s){
        System.out.println(s);
    }
}
