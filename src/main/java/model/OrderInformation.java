package model;

import java.util.ArrayList;

public class OrderInformation {

    private int orderID;
    private int totalProductos;
    private int precioTotalEnOrden;
    ArrayList<Products> productsInOrder = new ArrayList<>();

    public OrderInformation() {}

    public OrderInformation(int orderID, int totalProductos, int precioTotalEnOrden, ArrayList<Products> productsInOrder) {
        this.orderID = orderID;
        this.totalProductos = totalProductos;
        this.precioTotalEnOrden = precioTotalEnOrden;
        this.productsInOrder = productsInOrder;
    }

    public void addProduct(Products product){
        productsInOrder.add(product);
    }

    public int getOrderID() {return orderID;}
    public void setOrderID(int orderID) {this.orderID = orderID;}

    public int getTotalProductos() {return totalProductos;}
    public void setTotalProductos(int totalProductos) {this.totalProductos = totalProductos;}

    public int getPrecioTotalEnOrden() {return precioTotalEnOrden;}
    public void setPrecioTotalEnOrden(int precioTotalEnOrden) {this.precioTotalEnOrden = precioTotalEnOrden;}

    public ArrayList<Products> getProductsInOrder() {return productsInOrder;}
    public void setProductsInOrder(ArrayList<Products> productsInOrder) {this.productsInOrder = productsInOrder;}
}
