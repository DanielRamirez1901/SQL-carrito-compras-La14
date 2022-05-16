package services;

import model.Order;
import providers.OrderProvider;

import javax.ws.rs.*;
import java.sql.SQLException;
import java.util.ArrayList;

@Path("orders")
public class OrderServices {

    Response response = new Response();

    @GET
    @Path("echo")
    public String echo(){
        return "echo";
    }

    @POST
    @Path("create")
    @Consumes("application/json")
    public javax.ws.rs.core.Response create(Order order){
        try {
            OrderProvider provider = new OrderProvider();
            provider.create(order);
            return response.successfully();
        } catch (SQLException exception) {
            exception.printStackTrace();
            return response.successfully();
        }
    }

    @PUT
    @Path("updateOrderUser")
    @Consumes("application/json")
    public javax.ws.rs.core.Response updateOrderUser(Order order) {
        try {
            OrderProvider provider = new OrderProvider();
            provider.updateUserID(order);
            return response.successfully();
        } catch (SQLException exception) {
            exception.printStackTrace();
            return response.unsuccessfully();
        }
    }

    @PUT
    @Path("updatePaidState/{id}/{mode}")
    @Consumes("application/json")
    public javax.ws.rs.core.Response updatePaidState(@PathParam("id") int id, @PathParam("mode") int mode) {
        try {
            OrderProvider provider = new OrderProvider();
            Order order = provider.getAnOrder(id);
            if(mode == 1){
                //Con 1 se actualiza a cuenta pagada
                provider.updateStateOfOrderPaid(order);
                provider.updateTotalPrice(order);//Actualizo de paso el precio total
            }else if(mode == 2){
                //Con 2 se actualiza a cuenta no pagada
                provider.updateStateOfOrderUnpaid(order);
                provider.updateTotalPrice(order);//Actualizo de paso el precio total
            }
            return response.successfully();
        } catch (SQLException exception) {
            exception.printStackTrace();
            return response.unsuccessfully();
        }
    }

    @DELETE
    @Path("delete/{id}")
    public javax.ws.rs.core.Response delete(@PathParam("id") int id){
        try {
            OrderProvider provider = new OrderProvider();
            provider.deleteById(id);
            return  response.successfully();
        } catch (SQLException exception) {
            exception.printStackTrace();
            return response.unsuccessfully();
        }
    }

    @GET
    @Path("all")
    public javax.ws.rs.core.Response getAll(){
        try {
            OrderProvider provider = new OrderProvider();
            ArrayList<Order> orders = provider.getAllOrders();
            return javax.ws.rs.core.Response
                    .ok(orders)
                    .header("Content-Type","application/json")
                    .build();
        } catch (SQLException exception) {
            exception.printStackTrace();
            return response.unsuccessfully();
        }

    }



}
