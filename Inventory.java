

import java.io.FileWriter;
import java.io.IOException;

public class Inventory {

    Product[] products;
    int size = 0;

    public Inventory(int capacity) {
        products = new Product[capacity];
    }

    public void addProduct(Product p) {

        products[size++] = p;

        saveToCSV(p);
    }

    public void addProductManually(String name, double price, int stock) {

        Product p = new Product(name, price, stock);

        products[size++] = p;

        saveToCSV(p);

        System.out.println(name + " added successfully");
    }

    public void viewProducts() {

        System.out.println("+------+--------------+----------+--------+");
        System.out.printf("| %-4s | %-12s | %-8s | %-6s |\n","ID","Name","Price","Stock");
        System.out.println("+------+--------------+----------+--------+");

        for(int i = 0; i < size; i++) {

            System.out.printf("| %-4d | %-12s | %-8.2f | %-6d |\n",
                    products[i].id,
                    products[i].name,
                    products[i].price,
                    products[i].stock);
        }

        System.out.println("+------+------+--------+----------+--------+");
    }

    public Product searchByName(String name) {

        for(int i = 0; i < size; i++) {

            if(products[i].name.equalsIgnoreCase(name)) {
                return products[i];
            }
        }

        return null;
    }

    public Product searchById(int id) {

        for(int i = 0; i < size; i++) {

            if(products[i].id == id) {
                return products[i];
            }
        }

        return null;
    }

    
    public void saveToCSV(Product p) {

        try {

            FileWriter fw = new FileWriter("products.csv", true);

            fw.append(p.id + "," + p.name + "," + p.price + "," + p.stock + "\n");

            fw.close();

        } catch(IOException e) {

            System.out.println("Error writing to CSV file");
        }
    }
}