package services;

import model.OrderInformation;
import model.Order_Products;
import providers.Order_ProductsProvider;

import javax.ws.rs.*;
import java.sql.SQLException;

@Path("orders_products")
public class Order_ProductsServices {

    Response response = new Response();

    @GET
    @Path("echo")
    public String echo(){
        return "echo";
    }

    @POST
    @Path("create")
    @Consumes("application/json")
    public javax.ws.rs.core.Response create(Order_Products order_products){
        try {
            Order_ProductsProvider provider = new Order_ProductsProvider();
            provider.create(order_products);
            return response.successfully();
        } catch (SQLException exception) {
            exception.printStackTrace();
            return response.successfully();
        }
    }

    @PUT
    @Path("addProduct/{id}/{cantidadProducto}")
    public javax.ws.rs.core.Response addProduct(@PathParam("id") int id, @PathParam("cantidadProducto") int cantidadProducto){
        try {
            Order_ProductsProvider provider = new Order_ProductsProvider();
            provider.addProductsByID(id,cantidadProducto);
            return  response.successfully();
        } catch (SQLException exception) {
            exception.printStackTrace();
            return response.unsuccessfully();
        }
    }

    @DELETE
    @Path("deleteProduct/{id}/{cantidadProducto}")
    public javax.ws.rs.core.Response deleteProduct(@PathParam("id") int id, @PathParam("cantidadProducto") int cantidadProducto){
        try {
            Order_ProductsProvider provider = new Order_ProductsProvider();
            provider.deleteProductsByID(id,cantidadProducto);
            return  response.successfully();
        } catch (SQLException exception) {
            exception.printStackTrace();
            return response.unsuccessfully();
        }
    }

    @GET
    @Path("allByID/{id}")
    public javax.ws.rs.core.Response getByOrderID(@PathParam("id") int id){
        try {
            Order_ProductsProvider order_productsProvider = new Order_ProductsProvider();
            OrderInformation orderInformation = order_productsProvider.getOrderByID(id);
            return javax.ws.rs.core.Response
                    .ok(orderInformation)
                    .header("Content-Type","application/json")
                    .build();
        } catch (SQLException exception) {
            exception.printStackTrace();
            return response.unsuccessfully();
        }

    }


    /*
        Crear clase con arrayList de usuarios, orderWithout y productos asociados (*)
     */
}
