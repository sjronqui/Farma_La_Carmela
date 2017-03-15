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
import java.sql.Date;
import java.util.Calendar;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;


/**
 *
 * @author Saulitron
 */
public class ButtonController implements ActionListener {
    
    private String query;
    private MainJFrame viewApp;
    private ArticleList articulos;
    
    public ButtonController(MainJFrame viewApp){
       this.viewApp = viewApp;
       this.articulos=viewApp.getArticulos();
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        MainJPanel panel= this.viewApp.getPanelArticulo();
        SalePanel panelS= this.viewApp.getPanelSale();
        DefaultTableModel dtm =(DefaultTableModel) panelS.getTbFactura().getModel();
        JTable table=panelS.getTable();
        double total=0.00;
        switch (command){
            case "NEW":
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
                if(panel.getNombre().isEmpty()||panel.getCosto()==0||panel.getPres().isEmpty()||panel.getPvp()==0){
                    JOptionPane.showMessageDialog(null, "LLene correctamente todos los campos");
                    break;
                }
                if(panel.idSelected!=0){
                    
             //       query="{call update_articulo(" +panel.idSelected+",'"+ panel.getNombre()+ "','"+panel.getPres()+"',"+null+","+panel.getStock()+","+0+","+panel.getCosto()+","+panel.getPvp()+",'"+ panel.getExpDate()+"','"+panel.getCategoria()+"','"+panel.getRest()+"')}";
           //         DataConection.ejecutarprocedure(query);
                    this.articulos.modArticle(panel.idSelected, panel.getNombre(),panel.getPres(),null,panel.getStock(),0,panel.getCosto(),panel.getPvp(), panel.getExpDate(),panel.getCategoria(),panel.getRest(),panel.getComponentes());
                    JOptionPane.showMessageDialog(null, "Articulo modificado correctamente");
                }else{
                    JOptionPane.showMessageDialog(null, "Seleccione el articulo a modificar");
                }
                break;
            case "ADD":
                boolean correct=false;
                int cnt=0;
                while(!correct){
                    try{
                        cnt=Integer.parseInt(JOptionPane.showInputDialog("Ingrese la cantidad: "));
                        if(cnt>0) correct=true;
                        else JOptionPane.showMessageDialog(null, "Ingrese un numero positivo");
                    }catch(NumberFormatException nfe){
                        JOptionPane.showMessageDialog(null, "Ingrese un numero entero");
                    }
                }
                Double pvp=Double.parseDouble((String)table.getValueAt(table.getSelectedRow(),4));
                Double subtotal=pvp*cnt;
                String[] str=new String[]{ String.valueOf(cnt),
                    (String)table.getValueAt(table.getSelectedRow(),0),
                    (String)table.getValueAt(table.getSelectedRow(),1),
                    (String)table.getValueAt(table.getSelectedRow(),4),
                    String.valueOf(subtotal.floatValue())};
                dtm.insertRow(panelS.numArticulos, str);
                panelS.incrementNumArticulos();
                
                for(int i=0;i<panelS.numArticulos;i++){
                    total+=Double.parseDouble((String)dtm.getValueAt(i, 4));
                }
                
                break;        
            case "DEL":
                dtm.removeRow(panelS.getTbFactura().getSelectedRow());
                panelS.decrementNumArticulos();
                total=0;
                for(int i=0;i<panelS.numArticulos;i++){
                    total+=Double.parseDouble((String)dtm.getValueAt(i, 4));
                }
                break;
            case "BUY":
                int id=0;
                if(JOptionPane.showConfirmDialog(null, "Desea realizar esta compra", "Comprar", JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION)
                {
                    for(int i=0;i<panelS.numArticulos;i++){
                        id=Integer.parseInt((String)dtm.getValueAt(i, 1));
                        this.articulos.buyArticle(id, 1,new Date(Calendar.getInstance().getTimeInMillis()), Integer.parseInt((String)dtm.getValueAt(i, 0)));
                    }
                    dtm.setRowCount(0);
                    panelS.setEfectivo(0.00);
                    panelS.setCambio(0.00);
                    panelS.numArticulos=0;
                    JOptionPane.showMessageDialog(null, "Compra Exitosa");
                }
                break;
                
        }
        panelS.setTotal(total);
        if(panelS.numArticulos>0){
            panelS.getBtnBuy().setEnabled(true);
        }
    }
}
