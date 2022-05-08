package model;

public class Users {

    private int id;
    private String nombre;
    private int edad;
    private String cedula;
    private String correo;


    public Users(){

    }

    public Users(int id, String nombre, int edad, String cedula, String correo) {
        this.id = id;
        this.nombre = nombre;
        this.edad = edad;
        this.cedula = cedula;
        this.correo = correo;
    }

    //~~~~~~~~~~~~Getters&Setters~~~~~~~~~~~~~~~~
    public int getId() {return id;}
    public void setId(int id) {this.id = id;}

    public String getNombre() {return nombre;}
    public void setNombre(String nombre) {this.nombre = nombre;}

    public int getEdad() {return edad;}
    public void setEdad(int edad) {this.edad = edad;}

    public String getCedula() {return cedula;}
    public void setCedula(String cedula) {this.cedula = cedula;}

    public String getCorreo() {return correo;}
    public void setCorreo(String correo) {this.correo = correo;}
}
