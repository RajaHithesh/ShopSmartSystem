

import java.util.Random;

public class Product {

    int id;
    String name;
    double price;
    int stock;

    public Product(String name, double price, int stock) {

        Random r = new Random();
        this.id = 100 + r.nextInt(900);   // random ID
        this.name = name;
        this.price = price;
        this.stock = stock;
    }
}