/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package farma_la_carmela.Model;

/**
 *
 * @author usuario1
 */
public class Producto {
    
    private String nombre;
    private String presentacion;
    private String cod_barras;
    private int stock;
    private int stock_min;
    private double costo;
    private double pvp;
    private java.sql.Date fecha_ingreso;
    private java.sql.Date fecha_caducidad;
    private String restriccion;
    private String categoria;
    private String query;
    
    
public Producto(String nombre, String presentacion, String cod_barras, int stock, int stock_min, double costo, double pvp, java.sql.Date fecha_ingreso, java.sql.Date fecha_caducidad, String cat, String res) {
        this.nombre = nombre;
        this.presentacion = presentacion;
        this.cod_barras = cod_barras;
        this.stock = stock;
        this.stock_min = stock_min;
        this.costo = costo;
        this.pvp = pvp;
        this.fecha_ingreso = fecha_ingreso;
        this.fecha_caducidad = fecha_caducidad;
        this.categoria=cat;
        this.restriccion=res;
        
        query ="call insert_producto('" + nombre +"','"+presentacion+"','" + cod_barras +"'," + stock +","+stock_min+","+costo+","+pvp+",'"+fecha_ingreso+"','"+fecha_caducidad+"','"+cat+"','"+res+"')";
        
        DataConection.ejecutarprocedure(query);  
        
    }
    
    public String getRest() {
        return nombre;
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

    public java.sql.Date getFecha_ingreso() {
        return fecha_ingreso;
    }

    public java.sql.Date getFecha_caducidad() {
        return fecha_caducidad;
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

    public void setFecha_ingreso(java.sql.Date fecha_ingreso) {
        this.fecha_ingreso = fecha_ingreso;
    }

    public void setFecha_caducidad(java.sql.Date fecha_caducidad) {
        this.fecha_caducidad = fecha_caducidad;
    }

}
