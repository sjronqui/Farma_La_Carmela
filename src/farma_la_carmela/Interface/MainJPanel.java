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
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.SpringLayout;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import com.toedter.calendar.JDateChooser;
import java.awt.Color;
import java.sql.Date;
import java.util.Calendar;
import javax.swing.BorderFactory;
import javax.swing.JOptionPane;

/**
 *
 * @author usuario1
 */
public class MainJPanel extends JPanel{
    public final String type;
    public int idSelected=0;
    private JLabel lblName;
    private JTextField txtName;
    private JLabel lblPres;
    private JComboBox cbPres;
    private JTextField txtRest;
    private JLabel lblStock;
    private JTextField txtStock;
    private JLabel lblCost;
    private JTextField txtCost;
    private JLabel lblPvp;
    private JTextField txtPvp;
    private JLabel lblComp;
    private final JTextArea txtComp;
    private JLabel lblCat;
    private JComboBox cbCat;
    private JLabel lblRest;
    private JButton btnNew;
    private JButton btnMod;
    private JTable table;
    private JDateChooser txtExp;
    
    
    public MainJPanel(String ty){
        this.type=ty;
        String[] itemsC;
        String[] itemsP;
        SpringLayout mgr= new SpringLayout();
        
        String columNames[] = {"Nombre","Presentacion","Stock" ,"P.V.P." ,"Categoria"};
        
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
        mgr.putConstraint(SpringLayout.NORTH,scroll,9,SpringLayout.NORTH,this);
        
        lblName=new JLabel("Nombre: ");
        this.add(lblName);
        mgr.putConstraint(SpringLayout.WEST,lblName,5, SpringLayout.WEST, this);
        mgr.putConstraint(SpringLayout.NORTH,lblName,9,SpringLayout.SOUTH,scroll);

        txtName=new JTextField(30);
        this.add(txtName);
        mgr.putConstraint(SpringLayout.WEST,txtName,5, SpringLayout.EAST,lblName);
        mgr.putConstraint(SpringLayout.NORTH,txtName,5,SpringLayout.SOUTH,scroll);
        
        lblPres=new JLabel("Presentacion: ");
        this.add(lblPres);
        mgr.putConstraint(SpringLayout.WEST,lblPres,5, SpringLayout.WEST, this);
        mgr.putConstraint(SpringLayout.NORTH,lblPres,9,SpringLayout.SOUTH,txtName);
 
        cbPres= new JComboBox();
        this.add(cbPres);
        mgr.putConstraint(SpringLayout.WEST,cbPres,5, SpringLayout.EAST,lblPres);
        mgr.putConstraint(SpringLayout.NORTH,cbPres,5,SpringLayout.SOUTH,txtName);
        
        lblStock=new JLabel("Stock: ");
        this.add(lblStock);
        mgr.putConstraint(SpringLayout.WEST,lblStock,5, SpringLayout.WEST, this);
        mgr.putConstraint(SpringLayout.NORTH,lblStock,9,SpringLayout.SOUTH,cbPres);
 
        txtStock= new JTextField(3);
        this.add(txtStock);
        mgr.putConstraint(SpringLayout.WEST,txtStock,5, SpringLayout.EAST,lblStock);
        mgr.putConstraint(SpringLayout.NORTH,txtStock,5,SpringLayout.SOUTH,cbPres);
        
        lblCost=new JLabel("Costo: ");
        this.add(lblCost);
        mgr.putConstraint(SpringLayout.WEST,lblCost,5, SpringLayout.WEST, this);
        mgr.putConstraint(SpringLayout.NORTH,lblCost,9,SpringLayout.SOUTH,txtStock);
 
        txtCost= new JTextField(7);
        this.add(txtCost);
        mgr.putConstraint(SpringLayout.WEST,txtCost,5, SpringLayout.EAST,lblCost);
        mgr.putConstraint(SpringLayout.NORTH,txtCost,5,SpringLayout.SOUTH,txtStock);
        
        lblPvp=new JLabel("P.V.P : ");
        this.add(lblPvp);
        mgr.putConstraint(SpringLayout.WEST,lblPvp,5, SpringLayout.WEST, this);
        mgr.putConstraint(SpringLayout.NORTH,lblPvp,9,SpringLayout.SOUTH,txtCost);
 
        txtPvp= new JTextField(7);
        this.add(txtPvp);
        mgr.putConstraint(SpringLayout.WEST,txtPvp,5, SpringLayout.EAST,lblPvp);
        mgr.putConstraint(SpringLayout.NORTH,txtPvp,5,SpringLayout.SOUTH,txtCost);
        
        lblComp=new JLabel("Componentes: ");
        this.add(lblComp);
        mgr.putConstraint(SpringLayout.WEST,lblComp,5, SpringLayout.WEST, this);
        mgr.putConstraint(SpringLayout.NORTH,lblComp,9,SpringLayout.SOUTH,txtPvp);
 
        txtComp= new JTextArea(3,20);
        txtComp.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        this.add(txtComp);
        mgr.putConstraint(SpringLayout.WEST,txtComp,5, SpringLayout.EAST,lblComp);
        mgr.putConstraint(SpringLayout.NORTH,txtComp,5,SpringLayout.SOUTH,txtPvp);
        
        lblCat=new JLabel("Categoria: ");
        this.add(lblCat);
        mgr.putConstraint(SpringLayout.WEST,lblCat,5, SpringLayout.WEST, this);
        mgr.putConstraint(SpringLayout.NORTH,lblCat,9,SpringLayout.SOUTH,txtComp);
 
        cbCat= new JComboBox();
        this.add(cbCat);
        mgr.putConstraint(SpringLayout.WEST,cbCat,5, SpringLayout.EAST,lblCat);
        mgr.putConstraint(SpringLayout.NORTH,cbCat,5,SpringLayout.SOUTH,txtComp);
        
        lblRest=new JLabel("Observacion: ");
        this.add(lblRest);
        mgr.putConstraint(SpringLayout.WEST,lblRest,5, SpringLayout.WEST, this);
        mgr.putConstraint(SpringLayout.NORTH,lblRest,9,SpringLayout.SOUTH,cbCat);
 
        txtRest= new JTextField(35);
        this.add(txtRest);
        mgr.putConstraint(SpringLayout.WEST,txtRest,5, SpringLayout.EAST,lblRest);
        mgr.putConstraint(SpringLayout.NORTH,txtRest,5,SpringLayout.SOUTH,cbCat);
        
        JLabel lblExp= new JLabel("Fecha de expiracion: ");
        this.add(lblExp);
        mgr.putConstraint(SpringLayout.WEST,lblExp,5, SpringLayout.WEST, this);
        mgr.putConstraint(SpringLayout.NORTH,lblExp,9,SpringLayout.SOUTH,txtRest);
        
        txtExp=new JDateChooser();
        txtExp.setDateFormatString("dd/MM/yyyy");
        Calendar clndr= Calendar.getInstance();
        txtExp.setPreferredSize(new Dimension(100,20));
        clndr.add(Calendar.YEAR, 1);
        txtExp.setCalendar(clndr);
        this.add(txtExp);
        mgr.putConstraint(SpringLayout.WEST,txtExp,5, SpringLayout.EAST,lblExp);
        mgr.putConstraint(SpringLayout.NORTH,txtExp,9,SpringLayout.SOUTH,txtRest);
        
        btnNew= new JButton("Nuevo");
        btnNew.setEnabled(false);
        this.add(btnNew);
        mgr.putConstraint(SpringLayout.EAST,btnNew,10, SpringLayout.EAST,this);
        mgr.putConstraint(SpringLayout.NORTH,btnNew,5,SpringLayout.SOUTH,txtRest);
        
        btnMod= new JButton("Modificar");
        btnMod.setEnabled(false);
        this.add(btnMod);
        mgr.putConstraint(SpringLayout.EAST,btnMod,10, SpringLayout.WEST,btnNew);
        mgr.putConstraint(SpringLayout.NORTH,btnMod,5,SpringLayout.SOUTH,txtRest);
        
        this.setLayout(mgr);
        switch (type){
            case "Medicinas":
                itemsC = new String[]{"Resfriado","Rehidratacion","Diarrea","Estomago","Limpieza de Heridas","Llagas","Tos","Goteros",
                    "Vitaminas","Alergia","Presion","Antiflamatorio","Diabetes","Analgesicos","Colicos","Colesterol-Trigliceridos",
                    "Antibioticos","Vias Urinarias","Ampollas","Estrenimiento","Hongos","Hormonas","Vomito/Mareo","Ovulos",
                    "Cremas","Parasitos","Diureticos","Otros"};
                this.setCategories(itemsC);
                
                itemsP=new String[]{"Tableta","Tableta Masticable","Tableta Efervecente","Frasco","Sache","Jarabe",
                    "Sobre","Supositorio","Ampolla Bebible","Ampolla Inyectable","Ampolla de Inhalacion","Suspension",
                    "Gotas", "Unguento","Crema","Rollo","Tira","Caja",
                    "Otros"};
                this.setPresentations(itemsP);
                break;
                
            case "Productos":
                itemsC = new String[]{"Default"};
                this.setCategories(itemsC);
                this.txtComp.setVisible(false);
                this.txtComp.setRows(1);
                this.lblComp.setVisible(false);
                itemsP=new String[]{"Default"};
                this.setPresentations(itemsP);
                break;
        }
    }
    
    
    public void controller(ActionListener ctr){
        this.btnNew.addActionListener(ctr);
        this.btnMod.addActionListener(ctr);
        
        switch (type){
            case "Medicinas":
                this.btnNew.setActionCommand("NEW_MED");
                this.btnMod.setActionCommand("MOD_MED");
                this.txtName.addKeyListener(new KeyAdapter() {
                    public void keyReleased(KeyEvent e) {
                        setTable(searchMedicina(getNombre()));
                        table.setEnabled(true);
                        btnNew.setEnabled(true);
                    }
                    public void keyTyped(KeyEvent e) {
                    }
                    public void keyPressed(KeyEvent e) {
                    }
                });
                this.table.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
                    @Override
                    public void valueChanged(ListSelectionEvent e) {
                        if(table.getSelectedRow()>=0){
                            btnMod.setEnabled(true);
                            btnNew.setEnabled(false);
                            setMedicine((String)table.getValueAt(table.getSelectedRow(),0),(String)table.getValueAt(table.getSelectedRow(),1));
                        }
                    }
                    
                });
                break;
            case "Productos":
                this.btnNew.setActionCommand("NEW_PRO");
                this.txtName.setActionCommand("SEARCH_PRO");
                this.txtName.addKeyListener(new KeyAdapter() {
                    public void keyReleased(KeyEvent e) {
                        setTable(searchProducto(getNombre()));
                        table.setEnabled(true);
                        btnNew.setEnabled(true);
                    }
                    public void keyTyped(KeyEvent e) {
                    }
                    public void keyPressed(KeyEvent e) {
                    }
                });
                this.table.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
                    @Override
                    public void valueChanged(ListSelectionEvent e) {
                        if(table.getSelectedRow()>=0){
                            btnMod.setEnabled(true);
                            btnNew.setEnabled(false);
                            setProduct((String)table.getValueAt(table.getSelectedRow(),0),(String)table.getValueAt(table.getSelectedRow(),1));
                        }
                    }
                    
                });
                break;
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
        return list;
    }
    
    public LinkedList searchProducto(String name){
        String query="{call search_producto('%" + name + "%')}";
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
        DefaultTableModel dtm=(DefaultTableModel)
        table.getModel();dtm.setRowCount(0);
        table.getSelectionModel().clearSelection();
        while(dtm.getRowCount()>0) dtm.removeRow(0);
        for(int i= 0; i<lista.size();i++){
            String[] temp= (String[])lista.get(i);
            dtm.insertRow(i,temp);        
        }
    }
    
    private void setMedicine(String name, String pres){
        String query="{call find_medicina('" + name + "','"+pres+"')}";
        ResultSet rs = DataConection.ejecutarProcedureSelect(query);
        try {
            while(rs.next()){
                this.idSelected=rs.getInt(1);
                this.txtName.setText(rs.getString(2));
                this.cbPres.setSelectedItem(rs.getString(3));
                this.txtStock.setText(rs.getString(5));
                this.txtCost.setText(rs.getString(7));
                this.txtPvp.setText(rs.getString(8));
                this.cbCat.setSelectedItem(rs.getString(11));
                this.txtRest.setText(rs.getString(12));
                this.txtExp.setDate(rs.getDate(10));
                StringBuilder sb=new StringBuilder();
                query = "{call get_gen_med('" + rs.getInt(1)+"')}";
                rs = DataConection.ejecutarProcedureSelect(query);
                while(rs.next()){
                        sb.append(rs.getString(2)+"\n");
                }
                this.txtComp.setText(sb.toString());
            }
        } catch (SQLException ex) {
            Logger.getLogger("Erorr").log(Level.SEVERE, null, ex);
        }
    }
    
    
    private void setProduct(String name, String pres){
        String query="{call find_producto('" + name + "','"+pres+"')}";
        ResultSet rs = DataConection.ejecutarProcedureSelect(query);
        try {
            System.out.println("EStoy fuera");
            while(rs.next()){
                System.out.println("EStoy adentro");
                this.idSelected=rs.getInt(1);
                this.txtName.setText(rs.getString(2));
                this.cbPres.setSelectedItem(rs.getString(3));
                this.txtStock.setText(rs.getString(5));
                this.txtCost.setText(rs.getString(7));
                this.txtPvp.setText(rs.getString(8));
                this.cbCat.setSelectedItem(rs.getString(11));
                this.txtRest.setText(rs.getString(12));
                this.txtExp.setDate(rs.getDate(10));
            }
        } catch (SQLException ex) {
            Logger.getLogger("Erorr").log(Level.SEVERE, null, ex);
        }
    }
    
    public Date getExpDate(){
        return new java.sql.Date(this.txtExp.getDate().getTime());
    }
    
    public String getNombre(){
        return txtName.getText();
    }
    
    public String getCategoria(){
        return this.cbCat.getSelectedItem().toString();
    }
    
    public double getCosto(){
        try{
            return Double.parseDouble(this.txtCost.getText());
        }catch(NumberFormatException e){
            JOptionPane.showMessageDialog(null, "Ingrese un valor adecuado para el costo");
            return 0;
        }
    }
    
    public String getPres(){
        return this.cbPres.getSelectedItem().toString();
    }
    
    public String[] getComponentes(){
        return txtComp.getText().split("\n");
    }
    
    public String getRest(){
        return this.txtRest.getText();
    }
    
    public int getStock(){
        try{
            return Integer.parseInt(this.txtStock.getText());
        }catch(NumberFormatException e){
            JOptionPane.showMessageDialog(null, "Ingrese un valor adecuado para el stock");
            return 0;
        }
    }
    public double getPvp(){
        try{
            return Double.parseDouble(this.txtPvp.getText());
        }catch(NumberFormatException e){
            JOptionPane.showMessageDialog(null, "Ingrese un valor adecuado para el P.V.P.");
            return 0;
        }
        
    }
    
    public void setCategories(String[] items){
        for (String item: items){
            this.cbCat.addItem(item);
        }
    }
    
    public void setPresentations(String[] items){
        for (String item: items){
            this.cbPres.addItem(item);
        }
    }
}
