/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package farma_la_carmela.controller;

import farma_la_carmela.Interface.*;
import farma_la_carmela.Model.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author usuario1
 */
public class ButtonController implements ActionListener {
    
    private String query;
    private MainJFrame viewApp;
    private ArticleList articulos;
//    private LinkedList<Medicina> medicinas;
//    private LinkedList<Producto> productos;
    
    public ButtonController(MainJFrame viewApp, ArticleList articulos ){
       this.viewApp = viewApp;
       this.articulos=articulos;
//        this.medicinas = medicinas;
//        this.productos = productos;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        MainJPanel panel;
        switch (command){
            case "NEW":
                panel= this.viewApp.getPanelArticulo();
                if(panel.getNombre().isEmpty()||panel.getCosto()==0||panel.getPres().isEmpty()||panel.getPvp()==0){
                    JOptionPane.showMessageDialog(null, "LLene correctamente todos los campos");
                    break;
                }

                if(this.articulos.containsArticle(panel.getNombre(), panel.getPres(), panel.getExpDate())){
                    JOptionPane.showMessageDialog(null, "Articulo Repetido");   
                }else{
                    articulos.add(new Article(panel.getNombre(),panel.getPres(),null,panel.getStock(),0,panel.getCosto(),panel.getPvp(),new java.sql.Date(Calendar.getInstance().getTime().getTime()), panel.getExpDate(),panel.getCategoria(),panel.getRest(), panel.getComponentes()));
                    JOptionPane.showMessageDialog(null, "Articulo agregado correctamente");
                }
                break;
            case "MOD":
                panel= this.viewApp.getPanelArticulo();
                if(panel.getNombre().isEmpty()||panel.getCosto()==0||panel.getPres().isEmpty()||panel.getPvp()==0){
                    JOptionPane.showMessageDialog(null, "LLene correctamente todos los campos");
                    break;
                }
                if(panel.idSelected!=0){
                    query="{call update_articulo(" +panel.idSelected+",'"+ panel.getNombre()+ "','"+panel.getPres()+"',"+null+","+panel.getStock()+","+0+","+panel.getCosto()+","+panel.getPvp()+",'"+ panel.getExpDate()+"','"+panel.getCategoria()+"','"+panel.getRest()+"')}";
                    DataConection.ejecutarprocedure(query);
                    JOptionPane.showMessageDialog(null, "Medicamento modificado correctamente");
                }else{
                    JOptionPane.showMessageDialog(null, "Seleccione el medicamento a modificar");
                }
                break;
        }
    }

//    public LinkedList searchMedicina(String name){
//        query="{call search_medicina('" + name + "')}";
//        ResultSet rs = DataConection.ejecutarProcedureSelect(query);
//        LinkedList list= new LinkedList<String[]>();
//        try {
//            while(rs.next()){
//                list.add(new String[]{rs.getString(1), rs.getString(2),rs.getString(3), rs.getString(4), rs.getString(5)});                
//            }
//        } catch (SQLException ex) {
//            Logger.getLogger("Erorr").log(Level.SEVERE, null, ex);
//        }
//        return list;
//    }
    
}
