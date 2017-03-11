
import farma_la_carmela.Interface.MainJFrame;
import farma_la_carmela.Model.DataConection;
import farma_la_carmela.Model.Medicina;
import farma_la_carmela.Model.Producto;
import farma_la_carmela.controller.Farma_La_Carmela;
import java.util.LinkedList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author usuario1
 */
public class App {
    public static void main(String[] args){
        DataConection.performConnection();
        MainJFrame frame = new MainJFrame();
        LinkedList<Medicina> med = new LinkedList<Medicina>();
        LinkedList<Producto> prod = new LinkedList<Producto>();
        Farma_La_Carmela farma = new Farma_La_Carmela(frame,med, prod);
        frame.controller(farma);
        frame.setVisible(true);
        
    }
}
