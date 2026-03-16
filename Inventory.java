

import java.io.FileWriter;

public class Inventory {

    Product[] products;
    int size = 0;

    public Inventory(int capacity) {
        products = new Product[capacity];
    }

    public void addProduct(Product p) {
        products[size++] = p;

        // Save default products also to stock file
        saveStock(p);
    }

    public void viewProducts(){

        if(size == 0){
            System.out.println("No products available");
            return;
        }

        System.out.println("\n+------+--------------+----------+-------+");
        System.out.printf("| %-4s | %-12s | %-8s | %-5s |\n",
                "ID","Name","Price","Stock");
        System.out.println("+------+--------------+----------+-------+");

        for(int i=0;i<size;i++){

            System.out.printf("| %-4d | %-12s | %-8.2f | %-5d |\n",
                    products[i].id,
                    products[i].name,
                    products[i].price,
                    products[i].stock);
        }

        System.out.println("+------+--------------+----------+-------+");
    }

    public Product searchByName(String name){

        for(int i=0;i<size;i++){

            if(products[i].name.equalsIgnoreCase(name))
                return products[i];
        }

        return null;
    }

    public Product searchById(int id){

        for(int i=0;i<size;i++){

            if(products[i].id == id)
                return products[i];
        }

        return null;
    }

    public void addProductManually(String name,double price,int stock){

        Product existing = searchByName(name);

        if(existing != null){

            if(existing.stock > 0){
                System.out.println("Product already exists");
                return;
            }
            else{
                existing.stock = stock;
                existing.price = price;

                saveStock(existing);

                System.out.println("Stock updated");
                return;
            }
        }

        Product p = new Product(name,price,stock);

        products[size++] = p;

        saveStock(p);

        System.out.println("Product Added Successfully");
    }

    public void saveStock(Product p){

        try{

            FileWriter fw = new FileWriter("stock.csv",true);

            fw.append(p.id + "," + p.name + "," + p.price + "," + p.stock + "\n");

            fw.close();

        }catch(Exception e){

            System.out.println("File error");
        }
    }

    public void removeProduct(int id){

        for(int i = 0; i < size; i++){

            if(products[i].id == id){

                for(int j = i; j < size-1; j++){
                    products[j] = products[j+1];
                }

                size--;
                return;
            }
        }
    }
}