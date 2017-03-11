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
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Saulitron
 */
public class SearchController implements KeyListener{
     private String query;
    private MainJPanel panel;
    private ArticleList articulos;
    
    public SearchController(MainJFrame viewApp, ArticleList articulos){
       this.panel = viewApp.getPanelArticulo();
       this.articulos=articulos;
    }
    
    public LinkedList searchMedicina(String name){
        String query="{call search_articulo('%" + name + "%')}";
        ResultSet rs = DataConection.ejecutarProcedureSelect(query);
        LinkedList list= new LinkedList<String[]>();
        try {
            while(rs.next()){
                list.add(new String[]{rs.getString(1), rs.getString(2),String.valueOf(rs.getInt(3)), String.valueOf(rs.getDouble(4)), rs.getString(5)});                
            }
        } catch (SQLException ex) {
            Logger.getLogger("Erorr").log(Level.SEVERE, null, ex);
        }
        return list;
    }
    
      public void setTable(LinkedList lista){
          JTable table = panel.getTable();
        DefaultTableModel dtm=(DefaultTableModel)
        table.getModel();dtm.setRowCount(0);
        table.getSelectionModel().clearSelection();
        while(dtm.getRowCount()>0) dtm.removeRow(0);
        for(int i= 0; i<lista.size();i++){
            String[] temp= (String[])lista.get(i);
            dtm.insertRow(i,temp);        
        }
    }
      
    public void keyReleased(KeyEvent e) {
        setTable(searchMedicina(panel.getNombre()));
        panel.getTable().setEnabled(true);
        panel.getBtnNew().setEnabled(true);
    }
    public void keyTyped(KeyEvent e) {
    }
    public void keyPressed(KeyEvent e) {
    }
}
