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
 * @author usuario1
 */
public class Medicina {
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
    
    public Medicina(String nombre, String presentacion, String cod_barras, int stock, int stock_min, double costo, double pvp, Date fecha_ingreso, Date fecha_caducidad, String categoria, String restriccion, String[] Componentes) {
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
        
        query ="call insert_medicina('" + nombre +"','"+presentacion+"','" + cod_barras +"','" + stock +"','"+stock_min+"','"+costo+"','"+pvp+"','"+fecha_ingreso+"','"+fecha_caducidad+"','"+categoria+"','"+restriccion+"')";
        DataConection.ejecutarprocedure(query);
        
        query="{call getid_medicina('" + nombre + "','"+presentacion+"','"+fecha_caducidad+"')}";
        
        try {
            ResultSet rs = DataConection.ejecutarProcedureSelect(query);
            
            if(rs.next()){
                int id_med=rs.getInt(1);
                for(String generico: Componentes){  
                    query="{call find_generico('" + generico + "')}";
                    rs = DataConection.ejecutarProcedureSelect(query);
                    if(!rs.next()){
                        query="{call insert_generico('" + generico+"')}";
                        DataConection.ejecutarProcedureSelect(query);
                        query="{call find_generico('" + generico + "')}";
                        rs = DataConection.ejecutarProcedureSelect(query);
                        rs.next();
                    }    
                        int id_gen=rs.getInt(1);
                        query="{call insert_gen_med(" + id_med + ","+id_gen+")}";
                        DataConection.ejecutarprocedure(query);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger("Erorr").log(Level.SEVERE, null, ex);
        }
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
    }

    public void setPresentacion(String presentacion) {
        this.presentacion = presentacion;
    }

    public void setCod_barras(String cod_barras) {
        this.cod_barras = cod_barras;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public void setStock_min(int stock_min) {
        this.stock_min = stock_min;
    }

    public void setCosto(double costo) {
        this.costo = costo;
    }

    public void setPvp(double pvp) {
        this.pvp = pvp;
    }

    public void setFecha_ingreso(Date fecha_ingreso) {
        this.fecha_ingreso = fecha_ingreso;
    }

    public void setFecha_caducidad(Date fecha_caducidad) {
        this.fecha_caducidad = fecha_caducidad;
    }

    public void setRestriccion(String restriccion) {
        this.restriccion = restriccion;
    }
       
}
