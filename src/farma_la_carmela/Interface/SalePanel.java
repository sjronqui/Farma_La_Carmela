/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package farma_la_carmela.Interface;

import farma_la_carmela.Model.DataConection;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.SpringLayout;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author usuario1
 */
public class SalePanel extends JPanel{
        private JLabel lblName;
    private JTextField txtName;
    private JTable table;
    private JButton btnAdd;
    private JButton btnDel;
    private JTable tbFactura;
    
    public SalePanel(){
        SpringLayout mgr= new SpringLayout();
        
        lblName=new JLabel("Buscar: ");
        this.add(lblName);
        mgr.putConstraint(SpringLayout.WEST,lblName,5, SpringLayout.WEST, this);
        mgr.putConstraint(SpringLayout.NORTH,lblName,9,SpringLayout.NORTH,this);

        txtName=new JTextField(30);
        this.add(txtName);
        mgr.putConstraint(SpringLayout.WEST,txtName,5, SpringLayout.EAST,lblName);
        mgr.putConstraint(SpringLayout.NORTH,txtName,5,SpringLayout.NORTH,this);
        
        
        String columNames[] = {"Nombre","Presentacion","Stock" ,"Precio" ,"Categoria"};
        DefaultTableModel dtm = new DefaultTableModel(columNames,5);
        table = new JTable(dtm);
        table.setPreferredScrollableViewportSize(new Dimension(590,80));
        table.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        table.setEnabled(false);
        JScrollPane scroll = new JScrollPane(table);
        scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        
        this.add(scroll);
        mgr.putConstraint(SpringLayout.WEST,scroll,5, SpringLayout.WEST, this);
        mgr.putConstraint(SpringLayout.NORTH,scroll,9,SpringLayout.SOUTH,lblName);
        
        
        String columNames2[] = {"Cant.","Articulo","Precio\n Unitario","Precio\n Total"};
        DefaultTableModel dtm2 = new DefaultTableModel(columNames2,10);
        tbFactura = new JTable(dtm2);
        tbFactura.setPreferredScrollableViewportSize(new Dimension(290,160));
        tbFactura.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tbFactura.setEnabled(false);
        JScrollPane scFactura = new JScrollPane(tbFactura);
        scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        
        this.add(scFactura);
        mgr.putConstraint(SpringLayout.WEST,scFactura,5, SpringLayout.WEST, this);
        mgr.putConstraint(SpringLayout.NORTH,scFactura,9,SpringLayout.SOUTH,scroll);
        
        btnAdd=new JButton("Agregar");
        btnAdd.setEnabled(false);
        this.add(btnAdd);
        mgr.putConstraint(SpringLayout.WEST,btnAdd,5, SpringLayout.EAST, scFactura);
        mgr.putConstraint(SpringLayout.NORTH,btnAdd,9,SpringLayout.SOUTH,scroll);
        
        btnDel=new JButton("Quitar");
        btnDel.setEnabled(false);
        this.add(btnDel);
        mgr.putConstraint(SpringLayout.WEST,btnDel,5, SpringLayout.EAST, scFactura);
        mgr.putConstraint(SpringLayout.NORTH,btnDel,9,SpringLayout.SOUTH,btnAdd);
        
        JLabel lblTotal=new JLabel("Total: ");
        this.add(lblTotal);
        mgr.putConstraint(SpringLayout.WEST,lblTotal,5, SpringLayout.WEST, this);
        mgr.putConstraint(SpringLayout.NORTH,lblTotal,9,SpringLayout.SOUTH,scFactura);

        JLabel valor=new JLabel("0.00");
        this.add(valor);
        mgr.putConstraint(SpringLayout.WEST,valor,5, SpringLayout.EAST,lblTotal);
        mgr.putConstraint(SpringLayout.NORTH,valor,9,SpringLayout.SOUTH,scFactura);
        
        this.setLayout(mgr);
    }
    
    private void setMedicine(String name, String pres){
        String query="{call find_medicina('" + name + "','"+pres+"')}";
        ResultSet rs = DataConection.ejecutarProcedureSelect(query);
//        try {
//            while(rs.next()){
//                this.idSelected=rs.getInt(1);
//                this.txtName.setText(rs.getString(2));
//                this.cbPres.setSelectedItem(rs.getString(3));
//                this.txtStock.setText(rs.getString(5));
//                this.txtCost.setText(rs.getString(7));
//                this.txtPvp.setText(rs.getString(8));
//                this.cbCat.setSelectedItem(rs.getString(11));
//                this.txtRest.setText(rs.getString(12));
//                this.txtExp.setDate(rs.getDate(10));
//                StringBuilder sb=new StringBuilder();
//                query = "{call get_gen_med('" + rs.getInt(1)+"')}";
//                rs = DataConection.ejecutarProcedureSelect(query);
//                while(rs.next()){
//                        sb.append(rs.getString(2)+"\n");
//                }
//                this.txtComp.setText(sb.toString());
//            }
//        } catch (SQLException ex) {
//            Logger.getLogger("Erorr").log(Level.SEVERE, null, ex);
//        }
    }
    public void controller(ActionListener ctr){
        this.txtName.addKeyListener(new KeyAdapter(){
                    public void keyReleased(KeyEvent e) {
                        setTable(searchMedicina(txtName.getText()));
                        table.setEnabled(true);
                        //btnNew.setEnabled(true);
                    }
                    public void keyTyped(KeyEvent e){
                    }
                    public void keyPressed(KeyEvent e){
                    }
                });
        
        
        this.table.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
                    @Override
                    public void valueChanged(ListSelectionEvent e) {
                        if(table.getSelectedRow()>=0){
                            btnAdd.setEnabled(true);
                            btnDel.setEnabled(false);
                            //setMedicine((String)table.getValueAt(table.getSelectedRow(),0),(String)table.getValueAt(table.getSelectedRow(),1));
                        }
                    }
                    
                });
        this.tbFactura.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
                    @Override
                    public void valueChanged(ListSelectionEvent e) {
                        if(table.getSelectedRow()>=0){
                            btnAdd.setEnabled(false);
                            btnDel.setEnabled(true);
                            //setMedicine((String)table.getValueAt(table.getSelectedRow(),0),(String)table.getValueAt(table.getSelectedRow(),1));
                        }
                    }
                    
                });
    }
    
    public void setTable(LinkedList lista){
        DefaultTableModel dtm=(DefaultTableModel)table.getModel();dtm.setRowCount(0);
        table.getSelectionModel().clearSelection();
        while(dtm.getRowCount()>0) dtm.removeRow(0);
        for(int i= 0; i<lista.size();i++){
            String[] temp= (String[])lista.get(i);
            dtm.insertRow(i,temp);        
        }
    }
    
        public LinkedList searchMedicina(String name){
        String query="{call search_medicina('%" + name + "%')}";
        ResultSet rs = DataConection.ejecutarProcedureSelect(query);
        LinkedList list= new LinkedList<String[]>();
        try {
            while(rs.next()){
                list.add(new String[]{rs.getString(1), rs.getString(2),String.valueOf(rs.getInt(3)), String.valueOf(rs.getDouble(4)), rs.getString(5)});                
            }
        } catch (SQLException ex) {
            Logger.getLogger("Erorr").log(Level.SEVERE, null, ex);
        }
        /*Look*/
        query="{call search_producto('%" + name + "%')}";
        rs = DataConection.ejecutarProcedureSelect(query);
        try {
            while(rs.next()){
                list.add(new String[]{rs.getString(1), rs.getString(2),String.valueOf(rs.getInt(3)), String.valueOf(rs.getDouble(4)), rs.getString(5)});                
            }
        } catch (SQLException ex) {
            Logger.getLogger("Erorr").log(Level.SEVERE, null, ex);
        }
        return list;
    }
}


