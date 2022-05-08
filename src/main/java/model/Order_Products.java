package model;

public class Order_Products {
    //Tabla pivote entre ordenes y productos

    private int orderID;
    private int productID;
    private int cantidadProducto;
    private int precioTotalProducto;
    private int id;
    public Order_Products() {}

    public Order_Products(int id,int orderID, int productID, int cantidadProducto, int precioTotalProducto) {
        this.id = id;
        this.orderID = orderID;
        this.productID = productID;
        this.cantidadProducto = cantidadProducto;
        this.precioTotalProducto = precioTotalProducto;
    }

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public int getCantidadProducto() {
        return cantidadProducto;
    }

    public void setCantidadProducto(int cantidadProducto) {
        this.cantidadProducto = cantidadProducto;
    }

    public int getPrecioTotalProducto() {
        return precioTotalProducto;
    }

    public void setPrecioTotalProducto(int precioTotalProducto) {
        this.precioTotalProducto = precioTotalProducto;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
