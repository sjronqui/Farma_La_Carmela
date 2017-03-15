/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package farma_la_carmela.controller;

import farma_la_carmela.Interface.MainJFrame;
import farma_la_carmela.Interface.MainJPanel;
import farma_la_carmela.Model.Article;
import farma_la_carmela.Model.ArticleList;
import farma_la_carmela.Model.DataConection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 *
 * @author Saulitron
 */
public class TableController implements ListSelectionListener{
        
    private String query;
    private MainJPanel panel;
    private ArticleList articulos;
    
    public TableController(MainJFrame viewApp){
       this.panel = viewApp.getPanelArticulo();
       this.articulos=viewApp.getArticulos();
    }
    
 private void setArticle(String name, String pres, Date fex){
     Article tmp=this.articulos.getArticle(name, pres, fex);
     System.out.println(tmp.getId());
        panel.idSelected=tmp.getId();
        panel.setNombre(tmp.getNombre());
        panel.setPresentation(tmp.getPresentacion());
        panel.setStock(String.valueOf(tmp.getStock()));
        panel.setCost(String.valueOf(tmp.getCosto()));
        panel.setPvp(String.valueOf(tmp.getPvp()));
        panel.setCategory(tmp.getCategoria());
        panel.setRest(tmp.getRestriccion());
        panel.setExp(tmp.getFecha_caducidad());
        StringBuilder sb=new StringBuilder();
        query = "{call get_gen_art('" + tmp.getId()+"')}";
        ResultSet rs = DataConection.ejecutarProcedureSelect(query);
     try {
         while(rs.next()){
             sb.append(rs.getString(2)+"\n");
         }
     } catch (SQLException ex) {
         Logger.getLogger(TableController.class.getName()).log(Level.SEVERE, null, ex);
     }
        panel.setComp(sb.toString());
    }
    
  private void setArticle(int id){
     Article tmp=this.articulos.getArticle(id);
     
        panel.idSelected=tmp.getId();
        panel.setNombre(tmp.getNombre());
        panel.setPresentation(tmp.getPresentacion());
        panel.setStock(String.valueOf(tmp.getStock()));
        panel.setCost(String.valueOf(tmp.getCosto()));
        panel.setPvp(String.valueOf(tmp.getPvp()));
        panel.setCategory(tmp.getCategoria());
        panel.setRest(tmp.getRestriccion());
        panel.setExp(tmp.getFecha_caducidad());
        StringBuilder sb=new StringBuilder();
        query = "{call get_gen_art(" + tmp.getId()+")}";
        ResultSet rs = DataConection.ejecutarProcedureSelect(query);
     try {
         while(rs.next()){
             sb.append(rs.getString(2)+"\n");
         }
     } catch (SQLException ex) {
         Logger.getLogger(TableController.class.getName()).log(Level.SEVERE, null, ex);
     }
        panel.setComp(sb.toString());
    }
    @Override
    public void valueChanged(ListSelectionEvent e) {
        JTable table=panel.getTable();
        if(table.getSelectedRow()>=0){
            panel.getBtnMod().setEnabled(true);
            panel.getBtnNew().setEnabled(false);
            setArticle(Integer.parseInt((String)table.getValueAt(table.getSelectedRow(),0)));
        }
    }
        
}
