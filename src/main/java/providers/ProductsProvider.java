package providers;

import db.DbConnection;
import model.Products;
import model.Users;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ProductsProvider {

    DbProvider connection = new DbProvider();
    //CRUD


    //Metodo para la creacion de un producto
    public void create(Products product) throws SQLException {
        String sql = ("INSERT INTO products(nombre,precio) VALUES ('$NOMBRE',$PRECIO)")
                .replace("$NOMBRE",product.getNombre())
                .replace("$PRECIO",""+product.getPrecio());
        connection.connection(sql);
    }


    //Metodo para la actualizacion de un producto
    public void update(Products product) throws SQLException {
        String sql = ("UPDATE products SET nombre='$NOMBRE',precio ='$PRECIO' WHERE id=$ID")
                .replace("$ID",""+product.getId())
                .replace("$NOMBRE",product.getNombre())
                .replace("$PRECIO",""+product.getPrecio());
        connection.connection(sql);
    }

    //Metodo para la eliminacion de un producto por su id
    public void deleteById(int id) throws SQLException {
        String sql = "DELETE FROM products WHERE id="+id;
        connection.connection(sql);
    }

    //Metodo para la obtencion de todos los productos con todos sus parametros
    public ArrayList<Products> getAllProducts() throws SQLException {
        ArrayList<Products> output = new ArrayList<>();
        String sql = "SELECT * FROM products";
        DbConnection connection =  new DbConnection();
        connection.connect();
        ResultSet resultSet =  connection.getDataBySQL(sql);

        while(resultSet.next()){
            int id = resultSet.getInt(resultSet.findColumn("id"));
            String nombre = resultSet.getString(resultSet.findColumn("nombre"));
            int precio = resultSet.getInt(resultSet.findColumn("precio"));


            Products products = new Products(id,nombre,precio);
            output.add(products);
        }

        connection.disconnect();
        return output;
    }

    //Metodo para la obtencion del precio de un unico producto
    public int getPrecioProducto(int productID) throws SQLException {
        int precioProducto=0;
        boolean finishProcess = false;
        String sql = "SELECT * FROM products";
        DbConnection connection =  new DbConnection();
        connection.connect();
        ResultSet resultSet =  connection.getDataBySQL(sql);

        while(resultSet.next() && !finishProcess){
            int id = resultSet.getInt(resultSet.findColumn("id"));
            String nombre = resultSet.getString(resultSet.findColumn("nombre"));
            int precio = resultSet.getInt(resultSet.findColumn("precio"));
            if (id == productID) {
                precioProducto = precio;
                finishProcess = true; //Terminar busqueda al encontrar el producto en cuestion
            }
        }
        connection.disconnect();
        return precioProducto;
    }
}
