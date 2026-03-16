

import java.util.*;

public class ShopSmartSystem {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        Inventory inventory = new Inventory(20);
        Cart cart = new Cart();

        inventory.addProduct(new Product("Mobile",20000,10));
        inventory.addProduct(new Product("Laptop",50000,5));
        inventory.addProduct(new Product("Headphone",2000,15));
        inventory.addProduct(new Product("Mouse",700,25));

        int choice;

        do{

            System.out.println("\n1 View Products");
            System.out.println("2 Search Product by Name");
            System.out.println("3 Search Product by ID");
            System.out.println("4 Add to Cart");
            System.out.println("5 View Cart");
            System.out.println("6 Remove from Cart");
            System.out.println("7 Place Order");
            System.out.println("8 Add New Product");
            System.out.println("9 Exit");

            System.out.print("Enter choice: ");

            choice = sc.nextInt();
            sc.nextLine();

            switch(choice){

                case 1:

                    inventory.viewProducts();
                    break;

                case 2:

                    System.out.print("Enter product name: ");
                    String name = sc.nextLine();

                    Product p = inventory.searchByName(name);

                    if(p != null){

                        System.out.println("ID : " + p.id);
                        System.out.println("Name : " + p.name);
                        System.out.println("Price : " + p.price);
                        System.out.println("Stock : " + p.stock);
                    }
                    else
                        System.out.println("Product not found");

                    break;

                case 3:

                    System.out.print("Enter product ID: ");
                    int id = sc.nextInt();
                    sc.nextLine();

                    Product pid = inventory.searchById(id);

                    if(pid != null){

                        System.out.println("ID : " + pid.id);
                        System.out.println("Name : " + pid.name);
                        System.out.println("Price : " + pid.price);
                        System.out.println("Stock : " + pid.stock);
                    }
                    else
                        System.out.println("Product not found");

                    break;

                case 4:

                    System.out.print("Enter product name: ");
                    String add = sc.nextLine();

                    Product product = inventory.searchByName(add);

                    if(product != null){

                        System.out.print("Enter quantity: ");
                        int qty = sc.nextInt();
                        sc.nextLine();

                        if(qty <= product.stock)
                            cart.addToCart(product,qty);
                        else
                            System.out.println("Not enough stock");
                    }
                    else
                        System.out.println("Product not found");

                    break;

                case 5:

                    cart.viewCart();
                    break;

                case 6:

                    System.out.print("Enter product name to remove: ");
                    String remove = sc.nextLine();

                    cart.removeFromCart(remove);
                    break;

                case 7:

                    if(cart.head == null){

                        System.out.println("Cart Empty");
                        break;
                    }

                    CartNode temp = cart.head;
                    double total = 0;

                    System.out.println("\n========= ORDER BILL =========");

                    System.out.println("+------+--------------+-------+----------+");
                    System.out.printf("| %-4s | %-12s | %-5s | %-8s |\n",
                            "ID","Product","Qty","Price");
                    System.out.println("+------+--------------+-------+----------+");

                    while(temp != null){

                        double price = temp.product.price * temp.quantity;

                        System.out.printf("| %-4d | %-12s | %-5d | %-8.2f |\n",
                                temp.product.id,
                                temp.product.name,
                                temp.quantity,
                                price);

                        total += price;

                        // ⭐ Reduce stock
                        temp.product.stock -= temp.quantity;

                        inventory.saveStock(temp.product);

                        // ⭐ Remove if stock becomes 0
                        if(temp.product.stock <= 0){
                            inventory.removeProduct(temp.product.id);
                        }

                        temp = temp.next;
                    }

                    System.out.println("+------+--------------+-------+----------+");

                    System.out.println("TOTAL BILL : " + total);

                    cart.clearCart();

                    break;

                case 8:

                    System.out.print("Enter product name: ");
                    String pname = sc.nextLine();

                    System.out.print("Enter price: ");
                    double price = sc.nextDouble();

                    System.out.print("Enter stock: ");
                    int stock = sc.nextInt();
                    sc.nextLine();

                    inventory.addProductManually(pname,price,stock);

                    break;

                case 9:

                    System.out.println("Thank you for using ShopSmart");
                    break;

                default:
                    System.out.println("Invalid choice");
            }

        }while(choice != 9);

        sc.close();
    }
}