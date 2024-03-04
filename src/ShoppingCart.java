import java.util.ArrayList;
import java.util.Objects;

public class ShoppingCart extends Product {

    //instance variables
    ArrayList <Objects> shopping_cart_list = new ArrayList<>();

    private String product_ID;
    private String product_name;
    private String info_one;
    private String info_two;
    private int quantity;
    private double price;

    //Constructors

    public ShoppingCart(){}

    public ShoppingCart(String product_ID,String product_name,String info_one, String info_two, int quantity, double price){
        this.product_ID = product_ID;
        this.product_name = product_name;
        this.info_one = info_one;
        this.info_two = info_two;
        this.quantity = quantity;
        this.price = price;
    }

    //To Add a product to the list
    private void toAdd(ArrayList<ShoppingCart> list_of_products){
        ShoppingCart shoppingCartItem = new ShoppingCart(product_ID,product_name,info_one,info_two,quantity,price);
        list_of_products.add(shoppingCartItem);

    }

    // To Remove a product from a list
    private static void toRemove(ArrayList<ShoppingCart> list_of_products, String productID) {
        // Use an iterator to safely remove elements while iterating
        list_of_products.removeIf(product -> product.getProduct_ID().equals(productID));
    }

    // To calculate the Total of the order
    private static int calculateTotal(ArrayList<ShoppingCart> list_of_products) {
        int total = 0;

        for (ShoppingCart product : list_of_products) {
            total += product.getPrice();
        }

        return total;
    }

    // Getter method for product ID
    public String getProduct_ID() {
        return product_ID ;
    }



    public String getInfo_one(){
        return info_one ;
    }

    public String getInfo_two(){
        return info_two ;
    }

    public int getQuantity(){
        return quantity ;
    }

    public double getPrice(){
        return price ;
    }

    public String getProduct_Name(){
        return product_name ;
    }



    @Override
    public String toString() {
        return "Product : " +
                "Product_ID = " + product_ID +
                " Product_name = " + product_name +
                " Cat_01 = " + info_one +
                " Cat_02 = " + info_two +
                " Quantity = " + quantity +
                 " Price = " + price  ;
    }



}
