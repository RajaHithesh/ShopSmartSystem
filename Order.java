

public class Order {

    int orderId;
    String orderName;
    String products;
    double amount;

    public Order(int orderId, String orderName, String products, double amount) {

        this.orderId = orderId;
        this.orderName = orderName;
        this.products = products;
        this.amount = amount;
    }
}