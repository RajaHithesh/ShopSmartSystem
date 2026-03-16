

public class Order {

    int orderId;
    String products;

    public Order(int id,String products){

        this.orderId = id;
        this.products = products;
    }

    public void display(){

        System.out.println("Order ID : " + orderId);
        System.out.println("Products : " + products);
    }
}