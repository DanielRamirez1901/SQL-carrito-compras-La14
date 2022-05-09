package model;

public class Products {


    private int id;
    private String nombre;
    private int precio;
    private int cantidadProducto; //Variable valida solo para uso en OrderInformation

    public Products() {
    }

    public Products(int id, String nombre, int precio) {
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
    }

    public Products(int id, String nombre, int precio, int cantidadProducto) {
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
        this.cantidadProducto = cantidadProducto;
    }

    //*****************Setters&Getters*****************
    public int getId() {return id;}
    public void setId(int id) {this.id = id;}

    public String getNombre() {return nombre;}
    public void setNombre(String nombre) {this.nombre = nombre;}

    public int getPrecio() {return precio;}
    public void setPrecio(int precio) {this.precio = precio;}

    public int getCantidadProducto() {
        return cantidadProducto;
    }

    public void setCantidadProducto(int cantidadProducto) {
        this.cantidadProducto = cantidadProducto;
    }
}
