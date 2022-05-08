package db;

import java.sql.*;

public class DbConnection {

    private Connection connection;

    public DbConnection(){

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    //Metodo para generar la conexion
    public void connect() throws SQLException {
        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/carrito-la14","root","");
    }

    //Metodo para desconectar la conexion
    public void disconnect() throws SQLException {
        connection.close();
    }

    //Metodo para pedir informacion: SELECT
    public ResultSet getDataBySQL(String sql) throws SQLException {
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        return resultSet;
    }

    //Comandos: DELETE & EDIT & INSERT
    public void commandSQL(String sql) throws SQLException {
        Statement statement = connection.createStatement();
        statement.execute(sql);
    }

}
