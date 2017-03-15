/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package farma_la_carmela.Interface;

import farma_la_carmela.Model.DataConection;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.ResultSet;
import java.util.LinkedList;
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
import javax.swing.table.TableColumnModel;

/**
 *
 * @author Saulitron
 */
public class SalePanel extends JPanel{
        private JLabel lblName;
    private JTextField txtName;
    private JTable table;
    private JButton btnAdd;
    private JButton btnDel;
    private JButton btnBuy;
    private JTable tbFactura;
    private JLabel valor;
    private JLabel cambio;
    private JTextField efectivo;
    
    public int numArticulos;
    
    public SalePanel(){
        this.numArticulos=0;
        SpringLayout mgr= new SpringLayout();
        
        lblName=new JLabel("Buscar: ");
        this.add(lblName);
        mgr.putConstraint(SpringLayout.WEST,lblName,5, SpringLayout.WEST, this);
        mgr.putConstraint(SpringLayout.NORTH,lblName,9,SpringLayout.NORTH,this);

        txtName=new JTextField(30);
        this.add(txtName);
        mgr.putConstraint(SpringLayout.WEST,txtName,5, SpringLayout.EAST,lblName);
        mgr.putConstraint(SpringLayout.NORTH,txtName,5,SpringLayout.NORTH,this);
        
        
        String columNames[] = {"Cod","Nombre","Presentacion","Stock" ,"Precio","Fecha de Expiracion" ,"Categoria"};
        DefaultTableModel dtm = new DefaultTableModel(columNames,5);
        table = new JTable(dtm);
        table.setPreferredScrollableViewportSize(new Dimension(590,80));
        table.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        table.setEnabled(false);
        JScrollPane scroll = new JScrollPane(table);
        scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        TableColumnModel columna = table.getColumnModel();
        columna.getColumn(0).setPreferredWidth(10);
        columna.getColumn(1).setPreferredWidth(140);
        columna.getColumn(2).setPreferredWidth(80);
        columna.getColumn(3).setPreferredWidth(10);
        columna.getColumn(4).setPreferredWidth(10);
        columna.getColumn(5).setPreferredWidth(40);
//        columna.getColumn(6).setPreferredWidth(10);
        
        this.add(scroll);
        mgr.putConstraint(SpringLayout.WEST,scroll,5, SpringLayout.WEST, this);
        mgr.putConstraint(SpringLayout.NORTH,scroll,9,SpringLayout.SOUTH,lblName);
        
        
        String columNames2[] = {"Cant.","Cod.","Articulo","Pr.Uni.","Pr.Tot."};
        DefaultTableModel dtm2 = new DefaultTableModel(columNames2,0);
        tbFactura = new JTable(dtm2);
        tbFactura.setPreferredScrollableViewportSize(new Dimension(350,180));
        tbFactura.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        JScrollPane scFactura = new JScrollPane(tbFactura);
        scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        columna = tbFactura.getColumnModel();
        columna.getColumn(0).setPreferredWidth(10);
        columna.getColumn(1).setPreferredWidth(10);
        columna.getColumn(2).setPreferredWidth(80);
        columna.getColumn(3).setPreferredWidth(10);
        columna.getColumn(4).setPreferredWidth(10);
        
        this.add(scFactura);
        mgr.putConstraint(SpringLayout.WEST,scFactura,5, SpringLayout.WEST, this);
        mgr.putConstraint(SpringLayout.NORTH,scFactura,9,SpringLayout.SOUTH,scroll);
        
        btnAdd=new JButton("Agregar");
        btnAdd.setEnabled(false);
        this.add(btnAdd);
        mgr.putConstraint(SpringLayout.WEST,btnAdd,5, SpringLayout.EAST, scFactura);
        mgr.putConstraint(SpringLayout.NORTH,btnAdd,9,SpringLayout.SOUTH,scroll);
        
        btnDel=new JButton(" Quitar  ");
        btnDel.setEnabled(false);
        this.add(btnDel);
        mgr.putConstraint(SpringLayout.WEST,btnDel,5, SpringLayout.EAST, scFactura);
        mgr.putConstraint(SpringLayout.NORTH,btnDel,9,SpringLayout.SOUTH,btnAdd);
        
        btnBuy=new JButton("Comprar");
        btnBuy.setEnabled(false);
        this.add(btnBuy);
        mgr.putConstraint(SpringLayout.WEST,btnBuy,5, SpringLayout.EAST, scFactura);
        mgr.putConstraint(SpringLayout.SOUTH,btnBuy,0,SpringLayout.SOUTH,scFactura);
        
        valor=new JLabel("0.00");
        valor.setFont(new Font("Verdana", Font.BOLD, 16));
        this.add(valor);
        mgr.putConstraint(SpringLayout.EAST,valor,-5, SpringLayout.EAST,scFactura);
        mgr.putConstraint(SpringLayout.NORTH,valor,9,SpringLayout.SOUTH,scFactura);
        
        JLabel lblTotal=new JLabel("Total: ");
        lblTotal.setFont(new Font("Verdana", Font.BOLD, 16));
        this.add(lblTotal);
        mgr.putConstraint(SpringLayout.EAST,lblTotal,-5, SpringLayout.WEST, valor);
        mgr.putConstraint(SpringLayout.NORTH,lblTotal,9,SpringLayout.SOUTH,scFactura);
        
        efectivo=new JTextField(4);
        efectivo.setFont(new Font("Verdana", Font.BOLD, 16));
        this.add(efectivo);
        mgr.putConstraint(SpringLayout.EAST,efectivo,-5, SpringLayout.EAST,scFactura);
        mgr.putConstraint(SpringLayout.NORTH,efectivo,5,SpringLayout.SOUTH,valor);
        
        JLabel lblEfectivo=new JLabel("Efectivo: ");
        lblEfectivo.setFont(new Font("Verdana", Font.BOLD, 16));
        this.add(lblEfectivo);
        mgr.putConstraint(SpringLayout.EAST,lblEfectivo,-5, SpringLayout.WEST, efectivo);
        mgr.putConstraint(SpringLayout.NORTH,lblEfectivo,5,SpringLayout.SOUTH,lblTotal);
        
        cambio=new JLabel("0.00");
        cambio.setFont(new Font("Verdana", Font.BOLD, 16));
        this.add(cambio);
        mgr.putConstraint(SpringLayout.EAST,cambio,-5, SpringLayout.EAST,scFactura);
        mgr.putConstraint(SpringLayout.NORTH,cambio,5,SpringLayout.SOUTH,efectivo);
        
        JLabel lblcambio=new JLabel("Cambio: ");
        lblcambio.setFont(new Font("Verdana", Font.BOLD, 16));
        this.add(lblcambio);
        mgr.putConstraint(SpringLayout.EAST,lblcambio,-5, SpringLayout.WEST, cambio);
        mgr.putConstraint(SpringLayout.NORTH,lblcambio,5,SpringLayout.SOUTH,efectivo);


        this.setLayout(mgr);
    }

    public void setTotal(double total){
        this.valor.setText(String.valueOf(total));
    }
    public void setEfectivo(double efect){
        this.efectivo.setText(String.valueOf(efect));
    }
    public void setCambio(double efect){
        this.cambio.setText(String.valueOf(efect));
    }
    public JTable getTable() {
        return table;
    }

    public JButton getBtnAdd() {
        return btnAdd;
    }
    public JButton getBtnBuy() {
        return btnBuy;
    }
    public JButton getBtnDel() {
        return btnDel;
    }

    public JTable getTbFactura() {
        return tbFactura;
    }
    
    public void controller(ActionListener ctr, KeyListener ctr2){
        this.btnAdd.addActionListener(ctr);
        this.btnDel.addActionListener(ctr);
        this.btnBuy.addActionListener(ctr);
        this.btnAdd.setActionCommand("ADD");
        this.btnDel.setActionCommand("DEL");
        this.btnBuy.setActionCommand("BUY");
        
        this.txtName.addKeyListener(ctr2);
        
        this.efectivo.addKeyListener(new KeyListener(){
            @Override
            public void keyTyped(KeyEvent e) {
                
            }

            @Override
            public void keyPressed(KeyEvent e) {

                if(e.getKeyCode()==KeyEvent.VK_ENTER){
                    float v =Float.parseFloat(valor.getText());
                    float t=Float.parseFloat(efectivo.getText());
                    cambio.setText(String.valueOf(t-v));
                }
            }

            @Override
            public void keyReleased(KeyEvent e) { //To change body of generated methods, choose Tools | Templates.
                
            }
            
        });
        this.table.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
                    @Override
                    public void valueChanged(ListSelectionEvent e) {
                        if(table.getSelectedRow()>=0){
                            btnAdd.setEnabled(true);
                            btnDel.setEnabled(false);
                        }
                    }
                    
                });
        this.tbFactura.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
                    @Override
                    public void valueChanged(ListSelectionEvent e) {
                        if(table.getSelectedRow()>=0){
                            btnAdd.setEnabled(false);
                            btnDel.setEnabled(true);
                        }
                    }
                });
    }
    
        
        public void incrementNumArticulos(){
            this.numArticulos++;
        }
        public void decrementNumArticulos(){
            this.numArticulos--;
        }
        
        public String getNombre(){
            return this.txtName.getText();
        }
}


