package services;

import model.Message;
import model.Products;
import model.Users;
import providers.ProductsProvider;
import providers.UsersProvider;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.sql.SQLException;
import java.util.ArrayList;

@Path("products")
public class ProductsServices {

    ResponsesServices responsesServices = new ResponsesServices();

    @GET
    @Path("echo")
    public String echo(){
        return "echo";
    }

    @POST
    @Path("create")
    @Consumes("application/json")
    public Response create(Products product){
        try {
            ProductsProvider provider = new ProductsProvider();
            provider.create(product);
            return responsesServices.successfully();
        } catch (SQLException exception) {
            exception.printStackTrace();
            return responsesServices.successfully();
        }
    }


    @GET
    @Path("all")
    public Response getAll(){
        try {
            ProductsProvider provider = new ProductsProvider();
            ArrayList<Products> products = provider.getAllProducts();
            return Response
                    .ok(products)
                    .header("Content-Type","application/json")
                    .build();
        } catch (SQLException exception) {
            exception.printStackTrace();
            return responsesServices.unsuccessfully();
        }

    }

    @PUT
    @Path("update")
    @Consumes("application/json")
    public Response update(Products product) {
        try {
            ProductsProvider provider = new ProductsProvider();
            provider.update(product);
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
            ProductsProvider provider = new ProductsProvider();
            provider.deleteById(id);
            return  responsesServices.successfully();
        } catch (SQLException exception) {
            exception.printStackTrace();
            return responsesServices.unsuccessfully();
        }
    }

}
