package model;

public class Products {


    private int id;
    private String nombre;
    private int precio;

    public Products() {
    }

    public Products(int id, String nombre, int precio) {
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
    }


    //*****************Setters&Getters*****************
    public int getId() {return id;}
    public void setId(int id) {this.id = id;}

    public String getNombre() {return nombre;}
    public void setNombre(String nombre) {this.nombre = nombre;}

    public int getPrecio() {return precio;}
    public void setPrecio(int precio) {this.precio = precio;}
}
