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
public class Farma_La_Carmela implements ActionListener {
    
    private String query;
    private MainJFrame viewApp;
    private LinkedList<Medicina> medicinas;
    private LinkedList<Producto> productos;
    
    public Farma_La_Carmela(MainJFrame viewApp, LinkedList<Medicina> medicinas, LinkedList<Producto> productos ){
        this.viewApp = viewApp;
        this.medicinas = medicinas;
        this.productos = productos;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        MainJPanel panel;
        switch (command){
            case "NEW_MED":
                try{
                    panel= this.viewApp.getPanelMed();
                    if(panel.getNombre().isEmpty()||panel.getCosto()==0||panel.getPres().isEmpty()||panel.getPvp()==0){
                        JOptionPane.showMessageDialog(null, "LLene correctamente todos los campos");
                        break;
                    }
                    query="{call getid_medicina('" + panel.getNombre()+ "','"+panel.getPres()+"','"+panel.getExpDate()+"')}";
                    ResultSet rs = DataConection.ejecutarProcedureSelect(query);
                    if(!rs.next()){
                        addMedicina(panel.getNombre(),panel.getPres(),panel.getStock(),panel.getCosto(),panel.getPvp(), panel.getExpDate(),panel.getCategoria(),panel.getRest(), panel.getComponentes());
                        JOptionPane.showMessageDialog(null, "Medicamento agregado correctamente");
                    }else{
                        JOptionPane.showMessageDialog(null, "Medicamento Repetido");
                    }
                }catch(SQLException ex) {
                    Logger.getLogger("Erorr").log(Level.SEVERE, null, ex);
                }
                break;
            case "MOD_MED":
                    panel= this.viewApp.getPanelMed();
                    if(panel.getNombre().isEmpty()||panel.getCosto()==0||panel.getPres().isEmpty()||panel.getPvp()==0){
                        JOptionPane.showMessageDialog(null, "LLene correctamente todos los campos");
                        break;
                    }
                    if(panel.idSelected!=0){
                        query="{call update_medicina(" +panel.idSelected+",'"+ panel.getNombre()+ "','"+panel.getPres()+"',"+null+","+panel.getStock()+","+0+","+panel.getCosto()+","+panel.getPvp()+",'"+ panel.getExpDate()+"','"+panel.getCategoria()+"','"+panel.getRest()+"')}";
                        DataConection.ejecutarprocedure(query);
                        JOptionPane.showMessageDialog(null, "Medicamento modificado correctamente");
                    }else{
                        JOptionPane.showMessageDialog(null, "Seleccione el medicamento a modificar");
                    }
                break;
            case "NEW_PRO":
                try{
                    panel= this.viewApp.getPanelPro();
                    if(panel.getNombre().isEmpty()||panel.getCosto()==0||panel.getPres().isEmpty()||panel.getPvp()==0){
                        JOptionPane.showMessageDialog(null, "LLene correctamente todos los campos");
                        break;
                    }
                    query="{call getid_producto('" + panel.getNombre()+ "','"+panel.getPres()+"','"+panel.getExpDate()+"')}";
                    ResultSet rs = DataConection.ejecutarProcedureSelect(query);
                    if(!rs.next()){
                        addProducto(panel.getNombre(),panel.getPres(),null,panel.getStock(),0,panel.getCosto(),panel.getPvp(), new java.sql.Date(Calendar.getInstance().getTime().getTime()),panel.getExpDate(),panel.getCategoria(),panel.getRest());
                        JOptionPane.showMessageDialog(null, "Producto agregado correctamente");
                    }else{
                        JOptionPane.showMessageDialog(null, "Producto Repetido");
                    }
                }catch(SQLException ex) {
                    Logger.getLogger("Erorr").log(Level.SEVERE, null, ex);
                }
                break;
            case "MOD_PRO":
                    panel= this.viewApp.getPanelPro();
                    if(panel.getNombre().isEmpty()||panel.getCosto()==0||panel.getPres().isEmpty()||panel.getPvp()==0){
                        JOptionPane.showMessageDialog(null, "LLene correctamente todos los campos");
                        break;
                    }
                    if(panel.idSelected!=0){
                        query="{call update_producto(" +panel.idSelected+",'"+ panel.getNombre()+ "','"+panel.getPres()+"',"+null+","+panel.getStock()+","+0+","+panel.getCosto()+","+panel.getPvp()+",'"+ panel.getExpDate()+"','"+panel.getCategoria()+"','"+panel.getRest()+"')}";
                        DataConection.ejecutarprocedure(query);
                        JOptionPane.showMessageDialog(null, "Producto modificado correctamente");
                    }else{
                        JOptionPane.showMessageDialog(null, "Seleccione el producto a modificar");
                    }
                break;
        }
    }
    
   
    
    public void addMedicina(String nombre, String presentacion, int stock, double costo, double pvp, java.sql.Date fExp, String categoria,  String restriccion, String[] comp) {
        medicinas.add(new Medicina(nombre, presentacion, null, stock, 0, costo, pvp, new java.sql.Date(Calendar.getInstance().getTime().getTime()), fExp,categoria, restriccion, comp));
    }

    public void addProducto(String nombre, String presentacion, String cod_barras, int stock, int stock_min, double costo, double pvp, java.sql.Date fecha_ingreso, java.sql.Date fecha_caducidad, String cat,String res) {
        productos.add(new Producto(nombre, presentacion, cod_barras, stock, stock_min, costo, pvp, fecha_ingreso, fecha_caducidad,cat,res));
    }

    public LinkedList searchMedicina(String name){
        query="{call search_medicina('" + name + "')}";
        ResultSet rs = DataConection.ejecutarProcedureSelect(query);
        LinkedList list= new LinkedList<String[]>();
        try {
            while(rs.next()){
                list.add(new String[]{rs.getString(1), rs.getString(2),rs.getString(3), rs.getString(4), rs.getString(5)});                
            }
        } catch (SQLException ex) {
            Logger.getLogger("Erorr").log(Level.SEVERE, null, ex);
        }
        return list;
    }
    
}
