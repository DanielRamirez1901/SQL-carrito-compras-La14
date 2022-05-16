package services;

import model.Products;
import providers.ProductsProvider;

import javax.ws.rs.*;
import java.sql.SQLException;
import java.util.ArrayList;

@Path("products")
public class ProductsServices {

    Response response = new Response();

    @GET
    @Path("echo")
    public String echo(){
        return "echo";
    }

    @POST
    @Path("create")
    @Consumes("application/json")
    public javax.ws.rs.core.Response create(Products product){
        try {
            ProductsProvider provider = new ProductsProvider();
            provider.create(product);
            return response.successfully();
        } catch (SQLException exception) {
            exception.printStackTrace();
            return response.successfully();
        }
    }


    @GET
    @Path("all")
    public javax.ws.rs.core.Response getAll(){
        try {
            ProductsProvider provider = new ProductsProvider();
            ArrayList<Products> products = provider.getAllProducts();
            return javax.ws.rs.core.Response
                    .ok(products)
                    .header("Content-Type","application/json")
                    .build();
        } catch (SQLException exception) {
            exception.printStackTrace();
            return response.unsuccessfully();
        }

    }

    @PUT
    @Path("update")
    @Consumes("application/json")
    public javax.ws.rs.core.Response update(Products product) {
        try {
            ProductsProvider provider = new ProductsProvider();
            provider.update(product);
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
            ProductsProvider provider = new ProductsProvider();
            provider.deleteById(id);
            return  response.successfully();
        } catch (SQLException exception) {
            exception.printStackTrace();
            return response.unsuccessfully();
        }
    }

}
