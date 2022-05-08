package config;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

@ApplicationPath("api")
public class ApplicationConfig extends Application {



    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> recursos = new HashSet<>();

        //Registro de los servicios del programa
        recursos.add(services.UsersServices.class);
        recursos.add(services.ProductsServices.class);
        recursos.add(services.OrderServices.class);
        recursos.add(services.Order_ProductsServices.class);

        return recursos;
    }


}
