package services;

import model.Order;
import model.Order_Products;
import providers.OrderProvider;
import providers.Order_ProductsProvider;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.sql.SQLException;

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
    @Path("addProduct")
    @Consumes("application/json")
    public Response updateTotalPrice(Order_Products orderProducts) {
        try {
            Order_ProductsProvider provider = new Order_ProductsProvider();
            System.out.println("In services: "+orderProducts.getProductID());
            provider.addProductsInOrder(orderProducts);
            return responsesServices.successfully();
        } catch (SQLException exception) {
            exception.printStackTrace();
            return responsesServices.unsuccessfully();
        }
    }

    @DELETE
    @Path("delete/{id}/{cantidadProducto}")
    public Response delete(@PathParam("id") int id, @PathParam("cantidadProducto") int cantidadProducto){
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


    /*
    Falta metodo actualizar algun objeto de orders_products(*)
    Falta metodo para ver historico ordenes usuario por cedula(*)
    Falta metodo permite ver informacion de una orden por ID(**)
     */
}
