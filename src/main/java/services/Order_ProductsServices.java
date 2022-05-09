package services;

import model.Order;
import model.OrderInformation;
import model.Order_Products;
import providers.OrderProvider;
import providers.Order_ProductsProvider;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.sql.SQLException;
import java.util.ArrayList;

@Path("orders_products")
public class Order_ProductsServices {

    ResponsesServices responsesServices = new ResponsesServices();

    @GET
    @Path("echo")
    public String echo(){
        return "echo";
    }

    @POST
    @Path("create")
    @Consumes("application/json")
    public Response create(Order_Products order_products){
        try {
            Order_ProductsProvider provider = new Order_ProductsProvider();
            provider.create(order_products);
            return responsesServices.successfully();
        } catch (SQLException exception) {
            exception.printStackTrace();
            return responsesServices.successfully();
        }
    }

    @PUT
    @Path("addProduct/{id}/{cantidadProducto}")
    public Response addProduct(@PathParam("id") int id, @PathParam("cantidadProducto") int cantidadProducto){
        try {
            System.out.println("id: "+id+" cantidad: "+cantidadProducto);
            Order_ProductsProvider provider = new Order_ProductsProvider();
            provider.addProductsByID(id,cantidadProducto);
            return  responsesServices.successfully();
        } catch (SQLException exception) {
            exception.printStackTrace();
            return responsesServices.unsuccessfully();
        }
    }

    @DELETE
    @Path("deleteProduct/{id}/{cantidadProducto}")
    public Response deleteProduct(@PathParam("id") int id, @PathParam("cantidadProducto") int cantidadProducto){
        try {
            System.out.println("id: "+id+" cantidad: "+cantidadProducto);
            Order_ProductsProvider provider = new Order_ProductsProvider();
            provider.deleteProductsByID(id,cantidadProducto);
            return  responsesServices.successfully();
        } catch (SQLException exception) {
            exception.printStackTrace();
            return responsesServices.unsuccessfully();
        }
    }

    @GET
    @Path("allByID/{id}")
    public Response getByOrderID(@PathParam("id") int id){
        try {
            Order_ProductsProvider order_productsProvider = new Order_ProductsProvider();
            OrderInformation orderInformation = order_productsProvider.getOrderByID(id);
            return Response
                    .ok(orderInformation)
                    .header("Content-Type","application/json")
                    .build();
        } catch (SQLException exception) {
            exception.printStackTrace();
            return responsesServices.unsuccessfully();
        }

    }


    /*
        Crear clase con arrayList de usuarios, orderWithout y productos asociados (*)
     */
}
