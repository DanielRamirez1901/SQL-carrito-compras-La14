package providers;

import db.DbConnection;
import model.Order;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class OrderProvider {

    Order_ProductsProvider provider = new Order_ProductsProvider();
    DbProvider connection = new DbProvider();

    //CRUD
    //Metodo para la creacion de una orden
    public void create(Order order) throws SQLException {
        String sql = ("INSERT INTO orders(fechaCreacion,ordenPagada,fechaPago,userID) VALUES ('$FECHACREACION','$ORDENPAGADA','$FECHAPAGO',$USERID)");
                sql = sql.replace("$USERID",""+order.getUserID());
                sql = sql.replace("$FECHACREACION",order.getFechaCreacion());
                sql = sql.replace("$FECHAPAGO",order.getFechaPagoInicial());
                sql = sql.replace("$ORDENPAGADA",order.getOrdenNoPagada());
                connection.connection(sql);
    }

    //Actualizar user ID
    public void updateUserID(Order order) throws SQLException {
        String sql = ("UPDATE orders SET userID = $USERID WHERE id=$ID")
                .replace("$ID",""+order.getId())
                .replace("$USERID",""+order.getUserID());
        connection.connection(sql);
    }

    // //Metodo encargado de asignar a la orden del usuario un "SI HA SIDO PAGADO"
    public void updateStateOfOrderPaid(Order order) throws SQLException {
        String sql = ("UPDATE orders SET ordenPagada='$ORDENPAGADA', fechaPago='$FECHAPAGO' WHERE id=$ID");
                sql = sql.replace("$ID",""+order.getId());
                sql = sql.replace("$ORDENPAGADA",""+order.getOrdenSiPagada());
                sql = sql.replace("$FECHAPAGO",order.getFechaPago());
        connection.connection(sql);
    }

    //Metodo encargado de asignar a la orden del usuario un "NO HA SIDO PAGADO"
    public void updateStateOfOrderUnpaid(Order order) throws SQLException {
        String sql = ("UPDATE orders SET ordenPagada='$ORDENPAGADA', fechaPago='$FECHAPAGO' WHERE id=$ID");
        sql = sql.replace("$ID",""+order.getId());
        sql = sql.replace("$ORDENPAGADA",""+order.getOrdenNoPagada());
        sql = sql.replace("$FECHAPAGO",order.getFechaPagoInicial());
        connection.connection(sql);
    }


    //Metodo encargado de actualizar la tabla para obtener el precio total de la orden o cuenta
    public void updateTotalPrice(Order order) throws SQLException {
        String sql = ("UPDATE orders SET precioTotalCuenta=$PRECIOTOTALCUENTA WHERE id=$ID");
        sql = sql.replace("$ID",""+order.getId());
        int precioTotalCuenta = provider.getPrecioTotalProducto(order.getId());
        order.setPrecioTotalCuenta(precioTotalCuenta);
        sql = sql.replace("$PRECIOTOTALCUENTA",""+order.getPrecioTotalCuenta());
        connection.connection(sql);
    }

    //Eliminar ordenes por ID
    public void deleteById(int id) throws SQLException {
        String sql = "DELETE FROM orders WHERE id="+id;
        connection.connection(sql);
    }

    //Metodo para la obtencion de todos las ordenes con todos sus parametros
    public ArrayList<Order> getAllOrders() throws SQLException {
        ArrayList<Order> output = new ArrayList<>();

        String sql = "SELECT * FROM orders";
        DbConnection connection =  new DbConnection();
        connection.connect();
        ResultSet resultSet =  connection.getDataBySQL(sql);

        while(resultSet.next()){
            int id = resultSet.getInt(resultSet.findColumn("id"));
            String fechaCreacion = resultSet.getString(resultSet.findColumn("fechaCreacion"));
            String ordenPagada = resultSet.getString(resultSet.findColumn("ordenPagada"));
            String fechaPago = resultSet.getString(resultSet.findColumn("fechaPago"));
            int userID = resultSet.getInt(resultSet.findColumn("userID"));
            int precioTotalCuenta = resultSet.getInt(resultSet.findColumn("precioTotalCuenta"));

            Order order = new Order(id,fechaCreacion,ordenPagada,fechaPago,userID,precioTotalCuenta);
            output.add(order);
        }

        connection.disconnect();
        return output;
    }



}
