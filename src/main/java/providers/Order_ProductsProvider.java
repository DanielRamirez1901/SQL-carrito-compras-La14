package providers;

import db.DbConnection;
import model.Order;
import model.Order_Products;
import model.Products;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Order_ProductsProvider {


    ProductsProvider provider = new ProductsProvider();
    DbProvider connection = new DbProvider();

    //CRUD
    //Metodo para la referenciar una orden y un producto
    public void create(Order_Products order_products) throws SQLException {
        String sql = ("INSERT INTO orders_products(orderID,productID,cantidadProducto,precioTotalProducto) VALUES ($ORDERID,$PRODUCTID,$CANTIDADPRODUCTO,$PRECIOTOTALPRODUCTO)");
        sql = sql.replace("$ORDERID",""+order_products.getOrderID());
        sql = sql.replace("$PRODUCTID",""+order_products.getProductID());
        sql = sql.replace("$CANTIDADPRODUCTO",""+order_products.getCantidadProducto());
        int precioTotal = provider.getPrecioProducto(order_products.getProductID());
        order_products.setPrecioTotalProducto(precioTotal * order_products.getCantidadProducto());
        sql = sql.replace("$PRECIOTOTALPRODUCTO",""+order_products.getPrecioTotalProducto());
        connection.connection(sql);
    }

    //Metodo encargado de actualizar la tabla para obtener el precio total de la orden o cuenta
    public void addProductsInOrder(Order_Products order_products) throws SQLException {
        String sql = ("UPDATE orders_products SET cantidadProducto= $CANTIDADPRODUCTO,precioTotalProducto=$PRECIOTOTALPRODUCTO WHERE id=$ID");
        sql = sql.replace("$ID",""+order_products.getId());
        sql = sql.replace("$CANTIDADPRODUCTO",""+order_products.getCantidadProducto());
        order_products.setProductID(getIdProducto(order_products.getId()));
        int precioProducto = provider.getPrecioProducto(order_products.getProductID());
        order_products.setPrecioTotalProducto(precioProducto * order_products.getCantidadProducto());
        sql = sql.replace("$PRECIOTOTALPRODUCTO",""+order_products.getPrecioTotalProducto());
        connection.connection(sql);
    }

    //Metodo para la obtencion del precio total de productos que se encuentra en la tabla orders_products
    public int getPrecioTotalProducto(int orderIDInOrderTable) throws SQLException {
        int precioProductos=0;
        String sql = "SELECT * FROM orders_products";
        DbConnection connection =  new DbConnection();
        connection.connect();
        ResultSet resultSet =  connection.getDataBySQL(sql);

        while(resultSet.next()){
            int orderID = resultSet.getInt(resultSet.findColumn("orderID"));
            int precioTotalProducto = resultSet.getInt(resultSet.findColumn("precioTotalProducto"));
            if (orderID == orderIDInOrderTable) {
                precioProductos = precioProductos+precioTotalProducto;
            }
        }
        connection.disconnect();
        return precioProductos;
    }

    //Metodo para la obtencion de el productID
    public int getIdProducto(int orderIDInOrderTable) throws SQLException {
        int idProducto=0;
        String sql = "SELECT * FROM orders_products";
        DbConnection connection =  new DbConnection();
        connection.connect();
        ResultSet resultSet =  connection.getDataBySQL(sql);

        while(resultSet.next()){
            int id = resultSet.getInt(resultSet.findColumn("id"));
            int productID = resultSet.getInt(resultSet.findColumn("productID"));
            int precioTotalProducto = resultSet.getInt(resultSet.findColumn("precioTotalProducto"));
            if (id == orderIDInOrderTable) {
                idProducto=productID;
            }
        }
        connection.disconnect();
        return idProducto;
    }

    //Metodo para la obtencion del un objeto completo Order_Product
    public Order_Products getOrderProduct(int idParam) throws SQLException {
        String sql = "SELECT * FROM orders_products";
        Order_Products order_product = new Order_Products();
        DbConnection connection =  new DbConnection();
        connection.connect();
        ResultSet resultSet =  connection.getDataBySQL(sql);

        while(resultSet.next()){
            int id = resultSet.getInt(resultSet.findColumn("id"));
            int orderID = resultSet.getInt(resultSet.findColumn("orderID"));
            int productID = resultSet.getInt(resultSet.findColumn("productID"));
            int cantidadProducto = resultSet.getInt(resultSet.findColumn("cantidadProducto"));
            int precioTotalProducto = resultSet.getInt(resultSet.findColumn("precioTotalProducto"));
            if (id == idParam) {
                order_product.setId(id);order_product.setOrderID(orderID);order_product.setProductID(productID);
                order_product.setCantidadProducto(cantidadProducto);order_product.setPrecioTotalProducto(precioTotalProducto);
            }
        }
        connection.disconnect();
        return order_product;
    }

    //Metodo que elimina la cantidad de un producto que se encuentre en la orden en cuestion (id)
    public void deleteProductsByID(int id, int cantidadEliminadaProducto) throws SQLException{
        String sql = ("UPDATE orders_products SET cantidadProducto= $CANTIDADPRODUCTO,precioTotalProducto=$PRECIOTOTALPRODUCTO WHERE id=$ID");
        Order_Products order_product = getOrderProduct(id);
        System.out.println("id: "+order_product.getId()+" cantidadP: "+order_product.getCantidadProducto()+" idPro: "+order_product.getProductID());
        int cantidadProductoInOrder = order_product.getCantidadProducto();
        int cantidadResultante = cantidadProductoInOrder - cantidadEliminadaProducto;
        if(cantidadResultante<0){//Validar que no se quiera eliminar mas de lo que se tiene
            order_product.setCantidadProducto(0);
        }else{
            order_product.setCantidadProducto(cantidadResultante);
        }
        sql = sql.replace("$ID",""+order_product.getId());
        sql = sql.replace("$CANTIDADPRODUCTO",""+order_product.getCantidadProducto());
        int precioProducto = provider.getPrecioProducto(order_product.getProductID());
        order_product.setPrecioTotalProducto(precioProducto * order_product.getCantidadProducto());
        sql = sql.replace("$PRECIOTOTALPRODUCTO",""+order_product.getPrecioTotalProducto());
        connection.connection(sql);
    }




}
