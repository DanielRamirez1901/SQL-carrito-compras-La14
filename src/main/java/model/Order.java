package model;

import db.DbConnection;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import static java.lang.System.out;

public class Order {


    private String ordenPagada;
    private String fechaPago;
    private int id;
    private int userID;
    private String fechaCreacion;
    private int precioTotalCuenta;
    java.util.Date fecha = new Date();



    //private Users userID ;

    public Order() {}

    public Order(int id, String fechaCreacion, String ordenPagada, String fechaPago, int userID,int precioTotalCuenta) {
        this.ordenPagada = ordenPagada;
        this.fechaPago = fechaPago;
        this.id = id;
        this.userID = userID;
        this.fechaCreacion = fechaCreacion;
        this.precioTotalCuenta = precioTotalCuenta;
    }

    //***********Setters&Getters********************


    public String getFechaPago() {return fechaPago;}
    public void setFechaPago(String condition) {
        if(condition.startsWith("S")){
            fechaPago  = String.valueOf(fecha);
        }
        fechaPago ="-";
    }

    public String getFechaCreacion() {
        fechaCreacion = String.valueOf(fecha);
        return fechaCreacion;
    }
    public void setFechaCreacion(String fechaCreacion) {this.fechaCreacion = fechaCreacion;}

    public String getOrdenPagada() {return ordenPagada;}
    public void setOrdenPagada(String ordenPagada) {this.ordenPagada = ordenPagada;}

    public int getId() {return id;}
    public void setId(int id) {this.id = id;}

    public int getUserID() {return userID;}
    public void setUserID(int userID) {this.userID = userID;}

    public int getPrecioTotalCuenta() {return precioTotalCuenta;}

    public void setPrecioTotalCuenta(int precioTotalCuenta) {this.precioTotalCuenta = precioTotalCuenta;}


}
