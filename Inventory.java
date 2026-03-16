

import java.io.*;

public class Inventory {

    Product products[];
    int count;

    public Inventory(int size) {

        products = new Product[size];
        count = 0;
    }

    public void addProduct(Product p) {

        products[count++] = p;
        saveStock(p);
    }

    public void addProductManually(String name, double price, int stock) {

        for(int i=0;i<count;i++) {

            if(products[i].name.equalsIgnoreCase(name)) {

                products[i].stock += stock;

                saveAllStocks();

                System.out.println("Stock updated for existing product");

                return;
            }
        }

        Product p = new Product(name,price,stock);

        products[count++] = p;

        saveStock(p);

        System.out.println("New product added");
    }

    public Product searchByName(String name) {

        for(int i=0;i<count;i++) {

            if(products[i].name.equalsIgnoreCase(name))
                return products[i];
        }

        return null;
    }

    public Product searchById(int id) {

        for(int i=0;i<count;i++) {

            if(products[i].id == id)
                return products[i];
        }

        return null;
    }

    public void viewProducts() {

        System.out.println("\nID   Name       Price   Stock");

        for(int i=0;i<count;i++) {

            System.out.println(products[i].id + "   "
                    + products[i].name + "   "
                    + products[i].price + "   "
                    + products[i].stock);
        }
    }

    public void saveStock(Product p) {

        try {

            FileWriter fw = new FileWriter("stock.csv",true);

            fw.append(p.id + "," + p.name + "," + p.price + "," + p.stock + "\n");

            fw.close();

        } catch(Exception e) {

            System.out.println("File error");
        }
    }

    public void saveAllStocks() {

        try {

            FileWriter fw = new FileWriter("stock.csv");

            for(int i=0;i<count;i++) {

                fw.append(products[i].id + ","
                        + products[i].name + ","
                        + products[i].price + ","
                        + products[i].stock + "\n");
            }

            fw.close();

        } catch(Exception e) {

            System.out.println("File error");
        }
    }
}