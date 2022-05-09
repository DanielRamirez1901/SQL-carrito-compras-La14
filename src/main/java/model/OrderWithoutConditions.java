package model;

import java.util.Date;

public class OrderWithoutConditions {

    //Clase usada exclusivamente para el historico de ordenes del usuario, no tiene condiciones iniciales tales
    //como la fecha de creacion pago y demas que la clase orden contiene para su correcto funcionamiento


    private String ordenPagada;
    private String fechaPago;
    private int id;
    private int userID;
    private String fechaCreacion;
    private int precioTotalCuenta;
    java.util.Date fecha = new Date();



    //private Users userID ;

    public OrderWithoutConditions() {}

    public OrderWithoutConditions(int id, String fechaCreacion, String ordenPagada, String fechaPago, int userID,int precioTotalCuenta) {
        this.ordenPagada = ordenPagada;
        this.fechaPago = fechaPago;
        this.id = id;
        this.userID = userID;
        this.fechaCreacion = fechaCreacion;
        this.precioTotalCuenta = precioTotalCuenta;
    }

    public String getOrdenPagada() {
        return ordenPagada;
    }

    public void setOrdenPagada(String ordenPagada) {
        this.ordenPagada = ordenPagada;
    }

    public String getFechaPago() {
        return fechaPago;
    }

    public void setFechaPago(String fechaPago) {
        this.fechaPago = fechaPago;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(String fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public int getPrecioTotalCuenta() {
        return precioTotalCuenta;
    }

    public void setPrecioTotalCuenta(int precioTotalCuenta) {
        this.precioTotalCuenta = precioTotalCuenta;
    }
}
