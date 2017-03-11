/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package farma_la_carmela.controller;

import farma_la_carmela.Interface.MainJFrame;
import farma_la_carmela.Interface.MainJPanel;
import farma_la_carmela.Model.ArticleList;
import farma_la_carmela.Model.DataConection;
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
    
    public TableController(MainJFrame viewApp, ArticleList articulos){
       this.panel = viewApp.getPanelArticulo();
       this.articulos=articulos;
    }
    
 private void setArticle(String name, String pres){
        String query="{call find_articulo('" + name + "','"+pres+"')}";
        ResultSet rs = DataConection.ejecutarProcedureSelect(query);
        try {
            while(rs.next()){
                panel.idSelected=rs.getInt(1);
                panel.setNombre(rs.getString(2));
                panel.setPresentation(rs.getString(3));
                panel.setStock(rs.getString(5));
                panel.setCost(rs.getString(7));
                panel.setPvp(rs.getString(8));
                panel.setCategory(rs.getString(11));
                panel.setRest(rs.getString(12));
                panel.setExp(rs.getDate(10));
                StringBuilder sb=new StringBuilder();
                query = "{call get_gen_art('" + rs.getInt(1)+"')}";
                rs = DataConection.ejecutarProcedureSelect(query);
                while(rs.next()){
                        sb.append(rs.getString(2)+"\n");
                }
                panel.setComp(sb.toString());
            }
        } catch (SQLException ex) {
            Logger.getLogger("Erorr").log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public void valueChanged(ListSelectionEvent e) {
        JTable table=panel.getTable();
        if(table.getSelectedRow()>=0){
            panel.getBtnMod().setEnabled(true);
            panel.getBtnNew().setEnabled(false);
            setArticle((String)table.getValueAt(table.getSelectedRow(),0),(String)table.getValueAt(table.getSelectedRow(),1));
        }
    }
        
}
