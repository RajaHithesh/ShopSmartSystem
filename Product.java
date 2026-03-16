

import java.util.Random;

public class Product {

    int id;
    String name;
    double price;
    int stock;

    public Product(String name, double price, int stock) {

        this.id = generateId();
        this.name = name;
        this.price = price;
        this.stock = stock;
    }

    public Product(int id, String name, double price, int stock) {

        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
    }

    private int generateId() {

        Random r = new Random();
        return 100 + r.nextInt(900);
    }
}