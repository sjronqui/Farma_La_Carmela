/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package farma_la_carmela.controller;

import farma_la_carmela.Interface.MainJFrame;
import farma_la_carmela.Interface.MainJPanel;
import farma_la_carmela.Interface.SalePanel;
import farma_la_carmela.Model.Article;
import farma_la_carmela.Model.ArticleList;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Iterator;
import java.util.LinkedList;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Saulitron
 */
public class SearchController implements KeyListener{
    private MainJPanel panel;
    private SalePanel panelS;
    private ArticleList articulos;
    
    public SearchController(MainJFrame viewApp){
       this.panel = viewApp.getPanelArticulo();
       this.panelS=viewApp.getPanelSale();
       this.articulos=viewApp.getArticulos();
    }
    
    public LinkedList searchArticulo(String name){
        LinkedList list= new LinkedList<String[]>();
        ArticleList subList=this.articulos.getArticles(name);
        Iterator<Article> itr = subList.iterator();
        while(itr.hasNext()){
            Article tmp=itr.next();
            list.add(new String[]{String.valueOf(tmp.getId()),tmp.getNombre(),tmp.getPresentacion(),String.valueOf(tmp.getStock()), String.valueOf(tmp.getPvp()), String.valueOf(tmp.getFecha_caducidad()),tmp.getCategoria()});
        }
        return list;
    }
    
      public void setTable(LinkedList lista,JTable table){
        DefaultTableModel dtm=(DefaultTableModel)table.getModel();
        dtm.setRowCount(0);
        table.getSelectionModel().clearSelection();
        while(dtm.getRowCount()>0) dtm.removeRow(0);
        for(int i= 0; i<lista.size();i++){
            String[] temp= (String[])lista.get(i);
            dtm.insertRow(i,temp);    
        }
    }
      
    public void keyReleased(KeyEvent e) {
        if(e.getComponent().getParent() instanceof MainJPanel){
            setTable(searchArticulo(panel.getNombre().toUpperCase()),panel.getTable());
            panel.getTable().setEnabled(true);
            panel.getBtnNew().setEnabled(true);
        }else if(e.getComponent().getParent() instanceof SalePanel){
            setTable(searchArticulo(panelS.getNombre().toUpperCase()),panelS.getTable());
            panelS.getTable().setEnabled(true);
        }
    }
    public void keyTyped(KeyEvent e) {
    }
    public void keyPressed(KeyEvent e) {
    }
}
