/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package farma_la_carmela.Interface;

import java.awt.Dimension;
import java.awt.event.ActionListener;
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
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import com.toedter.calendar.JDateChooser;
import java.awt.Color;
import java.awt.event.KeyListener;
import java.sql.Date;
import java.util.Calendar;
import javax.swing.BorderFactory;
import javax.swing.JOptionPane;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

/**
 *
 * @author Saulitron
 */
public class MainJPanel extends JPanel{
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
    
    
    public MainJPanel(){
        String[] itemsC;
        String[] itemsP;
        SpringLayout mgr= new SpringLayout();
        
        String columNames[] = {"Cod.","Nombre","Presentacion","Stock" ,"P.V.P.","Fecha de Expiracion" ,"Categoria"};
        
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
        txtExp.setDateFormatString("yyyy-MM-dd");
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
        
        itemsC = new String[]{"RESFRIADO","REHIDRATACION","DIARREA","ESTOMAGO","LIMPIEZA DE HERIDAS","LLAGAS","TOS","GOTEROS",
            "VITAMINAS","ALERGIA","PRESION","ANTIFLAMATORIO","DIABETES","ANALGESICOS","COLICOS","COLESTEROL","TRIGLICERIDOS",
            "ANTIBIOTICOS","VIAS URINARIAS","AMPOLLAS","ESTRENIMIENTO","HONGOS","HORMONAS","VOMITO/MAREO","OVULOS",
            "CREMAS","PARASITOS","PSICOTROPICOS","DIURETICOS","BEBIDAS","PAPELERIA","BEBES","ROPA","FIEBRE","OTROS"};
        this.setCategories(itemsC);

        itemsP=new String[]{"TABLETA","TABLETA MASTICABLE","TABLETA EFERVECENTE","FRASCO","SACHET","JARABE",
            "SOBRE","SUPOSITORIO","AMPOLLA BEBIBLE","AMPOLLA INYECTABLE","AMPOLLA DE INHALACION","SUSPENSION",
            "GOTAS", "UNGUENTO","CREMA","ROLLO","TIRA","CAJA","BARRA","LIQUIDO","POMADA","INHALADOR","EQUIPO",
            "JALEA", "TARRO","FUNDA","PLIEGO","HOJA","ROLL ON","PAQUETE","GRANEL",
            "OTROS"};
        this.setPresentations(itemsP);
    }
    
    public void controller(ActionListener ctr1,KeyListener ctr2,ListSelectionListener ctr3){
        this.btnNew.addActionListener(ctr1);
        this.btnMod.addActionListener(ctr1);
        this.btnNew.setActionCommand("NEW");
        this.btnMod.setActionCommand("MOD");
        this.txtName.addKeyListener(ctr2);
        this.table.getSelectionModel().addListSelectionListener(ctr3);
       
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

    public JTable getTable() {
        return table;
    }

    public JButton getBtnNew() {
        return btnNew;
    }

    public JButton getBtnMod() {
        return btnMod;
    }
    
    public void setCategory(String item){
            this.cbCat.setSelectedItem(item);
    }
    
    public void setStock(String i){
        this.txtStock.setText(i);
        
    }
    public void setComp(String i){
        this.txtComp.setText(i);
        
    }
    public void setCost(String i){
        this.txtCost.setText(i);
        
    }
    
    public void setPvp(String i){
        this.txtPvp.setText(i);
        
    }
    public void setRest(String i){
        this.txtRest.setText(i);
        
    }
    public void setExp(Date i){
        this.txtExp.setDate(i);
        
    }
    public void setNombre(String i){
        this.txtName.setText(i);
    }
    
    public void setPresentation(String item){
            this.cbPres.setSelectedItem(item);
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
