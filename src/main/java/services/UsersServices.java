package services;

import model.UserOrdersHistoric;
import model.Users;
import providers.OrderProvider;
import providers.UsersProvider;

import javax.ws.rs.*;
import java.sql.SQLException;
import java.util.ArrayList;

@Path("users")
public class UsersServices {
    Response response = new Response();

    @GET
    @Path("echo")
    public String echo(){
        return "echo";
    }

    @POST
    @Path("create")
    @Consumes("application/json")
    public javax.ws.rs.core.Response create(Users user){
        try {
            UsersProvider provider = new UsersProvider();
            provider.create(user);
            return response.successfully();
        } catch (SQLException exception) {
            exception.printStackTrace();
            return  response.successfully();
        }
    }


    @GET
    @Path("all")
    public javax.ws.rs.core.Response getAll(){
        try {
            UsersProvider provider = new UsersProvider();
            ArrayList<Users> users = provider.getAllUsers();
            return javax.ws.rs.core.Response
                    .ok(users)
                    .header("Content-Type","application/json")
                    .build();
        } catch (SQLException exception) {
            exception.printStackTrace();
            return response.unsuccessfully();
        }

    }

    @GET
    @Path("userHistory/{cedula}")
    public javax.ws.rs.core.Response getUserHistory(@PathParam("cedula") String cedula){
        try {
            UsersProvider userprovider = new UsersProvider();
            OrderProvider orderProvider = new OrderProvider();
            Users user = userprovider.getAnUsers(cedula);
            UserOrdersHistoric userHistory = new UserOrdersHistoric();
            userHistory.setUser(user);
            userHistory.setTotalUserOrders(orderProvider.getUserOrders(user.getId()));
            //Crear metodo que a partir de la cedula, busque al usuario y lo asigne a userToPrint
            //Crear metodo que a partir del id de ese usuario, forme una orden de oders y asigne a addOrderToUser
            return javax.ws.rs.core.Response
                    .ok(userHistory)
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
    public javax.ws.rs.core.Response update(Users user) {
        try {
            UsersProvider provider = new UsersProvider();
            provider.update(user);
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
            UsersProvider provider = new UsersProvider();
            provider.deleteById(id);
            return  response.successfully();
        } catch (SQLException exception) {
            exception.printStackTrace();
            return response.unsuccessfully();
        }
    }

}
