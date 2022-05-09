package providers;

import db.DbConnection;
import model.Users;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UsersProvider {

    DbProvider connection = new DbProvider();

    //CRUD


    //Metodo para la creacion de un usuario
    public void create(Users user) throws SQLException {
        String sql = ("INSERT INTO users(nombre,edad,cedula,correo) VALUES ('$NOMBRE',$EDAD,'$CEDULA','$CORREO')")
                .replace("$NOMBRE",user.getNombre())
                .replace("$EDAD",""+user.getEdad())
                .replace("$CEDULA",user.getCedula())
                .replace("$CORREO",user.getCorreo());
        connection.connection(sql);
    }


    //Metodo para la actualizacion de un usuario
    public void update(Users user) throws SQLException {
        String sql = ("UPDATE users SET nombre='$NOMBRE',edad='$EDAD',cedula='$CEDULA',correo='$CORREO' WHERE id=$ID")
                .replace("$ID",""+user.getId())
                .replace("$NOMBRE",user.getNombre())
                .replace("$EDAD",""+user.getEdad())
                .replace("$CEDULA",user.getCedula())
                .replace("$CORREO",user.getCorreo());
        connection.connection(sql);
    }

    //Metodo para la eliminacion de un usuario por su id
    public void deleteById(int id) throws SQLException {
        String sql = "DELETE FROM users WHERE id="+id;
        connection.connection(sql);
    }

    //Metodo para la obtencion de todos los usuarios con todos sus parametros
    public ArrayList<Users> getAllUsers() throws SQLException {
        ArrayList<Users> output = new ArrayList<>();

        String sql = "SELECT * FROM users";
        DbConnection connection =  new DbConnection();
        connection.connect();
        ResultSet resultSet =  connection.getDataBySQL(sql);

        while(resultSet.next()){
            int id = resultSet.getInt(resultSet.findColumn("id"));
            String nombre = resultSet.getString(resultSet.findColumn("nombre"));
            int edad = resultSet.getInt(resultSet.findColumn("edad"));
            String cedula = resultSet.getString(resultSet.findColumn("cedula"));
            String correo = resultSet.getString(resultSet.findColumn("correo"));

            Users user = new Users(id,nombre,edad,cedula,correo);
            output.add(user);
        }

        connection.disconnect();
        return output;
    }

    //Metodo para buscar a un usuario por su cedula
    public Users getAnUsers(String cedulaU) throws SQLException {
        System.out.println(cedulaU);
        Users user = new Users();
        String sql = "SELECT * FROM users";
        DbConnection connection =  new DbConnection();
        connection.connect();
        ResultSet resultSet =  connection.getDataBySQL(sql);

        while(resultSet.next()){
            int id = resultSet.getInt(resultSet.findColumn("id"));
            String nombre = resultSet.getString(resultSet.findColumn("nombre"));
            int edad = resultSet.getInt(resultSet.findColumn("edad"));
            String cedula = resultSet.getString(resultSet.findColumn("cedula"));
            String correo = resultSet.getString(resultSet.findColumn("correo"));
            if(cedula.equals(cedulaU)){
                user.setId(id);user.setNombre(nombre);user.setEdad(edad);user.setCedula(cedula);user.setCorreo(correo);
            }

        }

        connection.disconnect();
        return user;
    }

}
