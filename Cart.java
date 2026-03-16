public class Cart 
{

    CartNode head;

    public void addToCart(Product product, int quantity) {

        CartNode newNode = new CartNode(product, quantity);

        newNode.next = head;
        head = newNode;

        System.out.println(product.name + " added to cart");
    }

    public void viewCart() {

        if(head == null) {

            System.out.println("Cart is Empty");
            return;
        }

        CartNode temp = head;
        double total = 0;

        System.out.println("+------+--------------+-------+----------+");
        System.out.printf("| %-4s | %-12s | %-5s | %-8s |\n","ID","Product","Qty","Price");
        System.out.println("+------+--------------+-------+----------+");

        while(temp != null) {

            double price = temp.product.price * temp.quantity;

            System.out.printf("| %-4d | %-12s | %-5d | %-8.2f |\n",
                    temp.product.id,
                    temp.product.name,
                    temp.quantity,
                    price);

            total += price;

            temp = temp.next;
        }

        System.out.println("+------+--------------+-------+----------+");
        System.out.println("Total Price : " + total);
    }

    public void removeFromCart(String name) {

        CartNode temp = head, prev = null;

        while(temp != null) {

            if(temp.product.name.equalsIgnoreCase(name)) {

                if(prev == null)
                    head = temp.next;
                else
                    prev.next = temp.next;

                System.out.println(name + " removed from cart");

                return;
            }

            prev = temp;
            temp = temp.next;
        }

        System.out.println("Product not found in cart");
    }

    public void clearCart() {

        head = null;
    }
}