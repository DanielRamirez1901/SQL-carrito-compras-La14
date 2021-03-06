package providers;

import db.DbConnection;
import model.Order;
import model.OrderWithoutConditions;


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
                order.setFechaPago("N");
                sql = sql.replace("$FECHAPAGO",order.getFechaPago());
                order.setOrdenPagada("NO HA SIDO PAGADA");
                sql = sql.replace("$ORDENPAGADA",order.getOrdenPagada());
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
                order.setOrdenPagada("SI HA SIDO PAGADA");
                order.setFechaPago("Si");
                sql = sql.replace("$ORDENPAGADA",""+order.getOrdenPagada());
                sql = sql.replace("$FECHAPAGO",order.getFechaPago());
        connection.connection(sql);
    }

    //Metodo encargado de asignar a la orden del usuario un "NO HA SIDO PAGADO"
    public void updateStateOfOrderUnpaid(Order order) throws SQLException {
        String sql = ("UPDATE orders SET ordenPagada='$ORDENPAGADA', fechaPago='$FECHAPAGO' WHERE id=$ID");
        sql = sql.replace("$ID",""+order.getId());
        order.setOrdenPagada("NO HA SIDO PAGADA");
        order.setFechaPago("No");
        sql = sql.replace("$ORDENPAGADA",""+order.getOrdenPagada());
        sql = sql.replace("$FECHAPAGO",order.getFechaPago());
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

    //Metodo para la obtencion de una sola orden en base al ID
    public Order getAnOrder(int idParam) throws SQLException {

        Order order = new Order();
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

            if(idParam == id){
                order.setId(id);order.setFechaCreacion(fechaCreacion);order.setOrdenPagada(ordenPagada);
                order.setFechaPago(fechaPago);order.setUserID(userID);order.setPrecioTotalCuenta(precioTotalCuenta);
            }
        }

        connection.disconnect();
        return order;
    }//Modificar fecha

    //Metodo para obtener ordenes relacionadas con el usuario en cuestion
    public ArrayList<OrderWithoutConditions> getUserOrders(int userOrder) throws SQLException {

        ArrayList<OrderWithoutConditions> output = new ArrayList<>();
        OrderWithoutConditions order;

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

            if(userID==userOrder) {
                System.out.println(id+fechaCreacion+ordenPagada+fechaPago+userID+precioTotalCuenta);
                /*
                order.setId(id);order.setFechaCreacion(fechaCreacion);order.setOrdenPagada(ordenPagada);
                order.setFechaPago(fechaPago);order.setUserID(userID);order.setPrecioTotalCuenta(precioTotalCuenta);
                 */
                order = new OrderWithoutConditions(id,fechaCreacion,ordenPagada,fechaPago,userID,precioTotalCuenta);
                output.add(order);
            }
        }

        connection.disconnect();
        return output;
    }



}
