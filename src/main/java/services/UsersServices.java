package services;

import model.Message;
import model.Users;
import providers.ProductsProvider;
import providers.UsersProvider;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.sql.SQLException;
import java.util.ArrayList;

@Path("users")
public class UsersServices {
    ResponsesServices responsesServices = new ResponsesServices();

    @GET
    @Path("echo")
    public String echo(){
        return "echo";
    }

    @POST
    @Path("create")
    @Consumes("application/json")
    public Response create(Users user){
        try {
            UsersProvider provider = new UsersProvider();
            provider.create(user);
            return responsesServices.successfully();
        } catch (SQLException exception) {
            exception.printStackTrace();
            return  responsesServices.successfully();
        }
    }


    @GET
    @Path("all")
    public Response getAll(){
        try {
            UsersProvider provider = new UsersProvider();
            ArrayList<Users> users = provider.getAllUsers();
            return Response
                    .ok(users)
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
    public Response update(Users user) {
        try {
            UsersProvider provider = new UsersProvider();
            provider.update(user);
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
            UsersProvider provider = new UsersProvider();
            provider.deleteById(id);
            return  responsesServices.successfully();
        } catch (SQLException exception) {
            exception.printStackTrace();
            return responsesServices.unsuccessfully();
        }
    }

}