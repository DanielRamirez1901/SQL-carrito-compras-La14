package services;

import model.Products;
import model.Order;
import model.Users;
import providers.OrderProvider;
import providers.ProductsProvider;
import providers.UsersProvider;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.sql.SQLException;
import java.util.ArrayList;

@Path("orders")
public class OrderServices {

    ResponsesServices responsesServices = new ResponsesServices();

    @GET
    @Path("echo")
    public String echo(){
        return "echo";
    }

    @POST
    @Path("create")
    @Consumes("application/json")
    public Response create(Order order){
        try {
            OrderProvider provider = new OrderProvider();
            provider.create(order);
            return responsesServices.successfully();
        } catch (SQLException exception) {
            exception.printStackTrace();
            return responsesServices.successfully();
        }
    }

    @PUT
    @Path("updateOrderUser")
    @Consumes("application/json")
    public Response updateOrderUser(Order order) {
        try {
            OrderProvider provider = new OrderProvider();
            provider.updateUserID(order);
            return responsesServices.successfully();
        } catch (SQLException exception) {
            exception.printStackTrace();
            return responsesServices.unsuccessfully();
        }
    }

    @PUT
    @Path("updatePaidState")
    @Consumes("application/json")
    public Response updatePaidState(Order order) {
        try {
            OrderProvider provider = new OrderProvider();
            provider.updateStateOfOrderPaid(order);
            return responsesServices.successfully();
        } catch (SQLException exception) {
            exception.printStackTrace();
            return responsesServices.unsuccessfully();
        }
    }

    @PUT
    @Path("updateUnpaidState")
    @Consumes("application/json")
    public Response updateUnpaidState(Order order) {
        try {
            OrderProvider provider = new OrderProvider();
            provider.updateStateOfOrderUnpaid(order);
            return responsesServices.successfully();
        } catch (SQLException exception) {
            exception.printStackTrace();
            return responsesServices.unsuccessfully();
        }
    }

    @PUT
    @Path("updateTotalPrice")
    @Consumes("application/json")
    public Response updateTotalPrice(Order order) {
        try {
            OrderProvider provider = new OrderProvider();
            provider.updateTotalPrice(order);
            return responsesServices.successfully();
        } catch (SQLException exception) {
            exception.printStackTrace();
            return responsesServices.unsuccessfully();
        }
    }

    @DELETE
    @Path("delete/{id}")
    public Response delete(@PathParam("id") int id){
        try {
            OrderProvider provider = new OrderProvider();
            provider.deleteById(id);
            return  responsesServices.successfully();
        } catch (SQLException exception) {
            exception.printStackTrace();
            return responsesServices.unsuccessfully();
        }
    }

    @GET
    @Path("all")
    public Response getAll(){
        try {
            OrderProvider provider = new OrderProvider();
            ArrayList<Order> orders = provider.getAllOrders();
            return Response
                    .ok(orders)
                    .header("Content-Type","application/json")
                    .build();
        } catch (SQLException exception) {
            exception.printStackTrace();
            return responsesServices.unsuccessfully();
        }

    }


}
