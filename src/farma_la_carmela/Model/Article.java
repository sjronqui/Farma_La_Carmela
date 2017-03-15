/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package farma_la_carmela.Model;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Saulitron
 */
public class Article {
    private int id;
    private String nombre;
    private String presentacion;
    private String cod_barras;
    private int stock;
    private int stock_min;
    private double costo;
    private double pvp;
    private Date fecha_ingreso;
    private Date fecha_caducidad;
    private String restriccion;
    private String categoria;
    private String query;
    
    
     public Article(String nombre, String presentacion, String cod_barras, int stock, int stock_min, double costo, double pvp, Date fecha_ingreso, Date fecha_caducidad, String categoria, String restriccion, String[] Componentes) {
        this.nombre = nombre;
        this.presentacion = presentacion;
        this.cod_barras = cod_barras;
        this.stock = stock;
        this.stock_min = stock_min;
        this.costo = costo;
        this.pvp = pvp;
        this.fecha_ingreso = fecha_ingreso;
        this.fecha_caducidad = fecha_caducidad;
        this.categoria=categoria;
        this.restriccion = restriccion;
        
        query ="call insert_articulo('" + nombre +"','"+presentacion+"'," + cod_barras +"," + stock +","+stock_min+","+costo+","+pvp+",'"+fecha_ingreso+"','"+fecha_caducidad+"','"+categoria+"','"+restriccion+"')";
        DataConection.ejecutarprocedure(query);
                
        query ="select last_insert_id() as last_id from Articulo";
        ResultSet rs = DataConection.ejecutarProcedureSelect(query);
       
        try {
            if(rs.next()){
                this.id=rs.getInt("last_id");
                for(String generico: Componentes){  
                    query="{call find_generico('" + generico.toUpperCase() + "')}";
                    rs = DataConection.ejecutarProcedureSelect(query);
                    if(!rs.next()){
                        query="{call insert_generico('" + generico.toUpperCase()+"')}";
                        DataConection.ejecutarProcedureSelect(query);
                        query="{call find_generico('" + generico.toUpperCase() + "')}";
                        rs = DataConection.ejecutarProcedureSelect(query);
                        rs.next();
                    }    
                        int id_gen=rs.getInt(1);
                        query="{call insert_gen_art(" + id + ","+id_gen+")}";
                        DataConection.ejecutarprocedure(query);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger("Erorr").log(Level.SEVERE, null, ex);
        }
    }
     
    public Article(int id,String nombre, String presentacion, String cod_barras, int stock, int stock_min, double costo, double pvp, Date fecha_ingreso, Date fecha_caducidad, String categoria, String restriccion, String[] Componentes) {
        this.id=id;
        this.nombre = nombre.toUpperCase();
        this.presentacion = presentacion.toUpperCase();
        this.cod_barras = cod_barras;
        this.stock = stock;
        this.stock_min = stock_min;
        this.costo = costo;
        this.pvp = pvp;
        this.fecha_ingreso = fecha_ingreso;
        this.fecha_caducidad = fecha_caducidad;
        this.categoria=categoria.toUpperCase();
        this.restriccion = restriccion.toUpperCase();  
    } 
     
    public String getNombre() {
        return nombre;
    }

    public String getPresentacion() {
        return presentacion;
    }

    public String getCod_barras() {
        return cod_barras;
    }

    public int getStock() {
        return stock;
    }
    
    
    public int getId() {
        return id;
    }

    public String getCategoria() {
        return categoria;
    }

    public int getStock_min() {
        return stock_min;
    }

    public double getCosto() {
        return costo;
    }

    public double getPvp() {
        return pvp;
    }

    public Date getFecha_ingreso() {
        return fecha_ingreso;
    }

    public Date getFecha_caducidad() {
        return fecha_caducidad;
    }

    public String getRestriccion() {
        return restriccion;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
        updateArticle();
    }

    public void setPresentacion(String presentacion) {
        this.presentacion = presentacion;
        updateArticle();
    }

    public void setCod_barras(String cod_barras) {
        this.cod_barras = cod_barras;
        updateArticle();
    }

    public void setStock(int stock) {
        this.stock = stock;
        updateArticle();
    }

    public void setStock_min(int stock_min) {
        this.stock_min = stock_min;
        updateArticle();
    }

    public void setCosto(double costo) {
        this.costo = costo;
        updateArticle();
    }

    public void setPvp(double pvp) {
        this.pvp = pvp;
        updateArticle();
    }

    public void setFecha_ingreso(Date fecha_ingreso) {
        this.fecha_ingreso = fecha_ingreso;
        updateArticle();
    }

    public void setFecha_caducidad(Date fecha_caducidad) {
        this.fecha_caducidad = fecha_caducidad;
        updateArticle();
    }

    public void setRestriccion(String restriccion) {
        this.restriccion = restriccion;
        updateArticle();
    }
    
    private void updateArticle(){
         query="{call update_articulo(" +this.id+",'"+ this.nombre+ "','"+this.presentacion+"','"+this.cod_barras+"',"+this.stock+","+this.stock_min+","+this.costo+","+this.pvp+",'"+ this.fecha_caducidad+"','"+this.categoria+"','"+this.restriccion+"')}";
         DataConection.ejecutarprocedure(query);
    }
    
    public void updateArticle(String n, String p, String cb,int s,int sm,double c, double pvp, Date exp, String ca,String r,String[] comp){
         this.nombre=n;
         this.presentacion=p;
         this.cod_barras=cb;
         this.stock=s;
         this.stock_min=sm;
         this.costo=c;
         this.pvp=pvp;
         this.fecha_caducidad=exp;
         this.categoria=ca;
         this.restriccion=r;
         this.updateArticle();
         this.updateComponentes(comp);
    }
    
    public void updateComponentes(String[] Componentes){
        query="delete from Generico_Articulo where id="+this.id+";";
        DataConection.ejecutarprocedure(query);
        try {
            for(String generico: Componentes){  
                query="{call find_generico('" + generico.toUpperCase() + "')}";
                ResultSet rs = DataConection.ejecutarProcedureSelect(query);
                if(!rs.next()){
                    query="{call insert_generico('" + generico.toUpperCase()+"')}";
                    DataConection.ejecutarProcedureSelect(query);
                    query="{call find_generico('" + generico.toUpperCase() + "')}";
                    rs = DataConection.ejecutarProcedureSelect(query);
                    rs.next();
                }    
                    int id_gen=rs.getInt(1);
                    query="{call insert_gen_art(" + id + ","+id_gen+")}";
                    DataConection.ejecutarprocedure(query);
            }    
        } catch (SQLException ex) {
            Logger.getLogger("Erorr").log(Level.SEVERE, null, ex);
        }
    }
    
    public void buy(int idc,Date f,int cant){
        query="{call insert_compra_articulo("+this.id+","+idc+",'"+f+"',"+cant+")}";
        DataConection.ejecutarprocedure(query);
        this.stock=stock-cant;
    }
    
    
    @Override
    public boolean equals(Object o){
        return (o instanceof Article)&&((Article)o).id==this.id;
    }
    
    @Override
    public int hashCode() {
        return this.id;
    }
}
