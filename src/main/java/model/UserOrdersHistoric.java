package model;

import java.util.ArrayList;

public class UserOrdersHistoric {

    //Clase usada exclusivamente para almacenar al usuario y sus ordenes en toda la base de datos

    Users user = new Users();
    ArrayList<OrderWithoutConditions> totalUserOrders= new ArrayList<>();


    public UserOrdersHistoric(){

    }


    public void addOrderToUserHistory(OrderWithoutConditions order){
        totalUserOrders.add(order);
    }

    //~~~~~~~~~~~~Getters&Setters~~~~~~~~~~~~~~~~


    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    public ArrayList<OrderWithoutConditions> getTotalUserOrders() {
        return totalUserOrders;
    }

    public void setTotalUserOrders(ArrayList<OrderWithoutConditions> totalUserOrders) {
        this.totalUserOrders = totalUserOrders;
    }
}
