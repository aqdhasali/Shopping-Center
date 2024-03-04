import java.io.Serializable;

public abstract class Product implements Serializable,Comparable<Product> {

    //Instance variables
    private String product_ID; //To store the product id
    private String product_name;  // To store the product name
    private int noOfItems; // To store the number of items
    private double price; //To store the price of the product

    //Constructors
    public Product(){} //Default Constructor

    public Product(String product_ID,String product_name,int noOfItems,double price){ //Constructor with values
        this.product_ID = product_ID;
        this.product_name = product_name;
        this.noOfItems = noOfItems;
        this.price = price;
    }
    //Getter Methods
    //Getter method to get the product id
    public String getProduct_ID() {
        return product_ID;
    }

    //Getter method to get the product name
    public String getProduct_name() {
        return product_name;
    }

    //Getter method to get the number of items
    public int getNoOfItems() {
        return noOfItems;
    }

    //Getter method to get the price
    public double getPrice() {
        return price;
    }


    //Setter Methods

    //Setter method to set the product id
    public void setProduct_ID(String product_ID) {
        this.product_ID = product_ID;
    }

    //Setter method to set product name
    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }
    //Setter method to set number of items of that product
    public void setNoOfItems(int noOfItems) {
        this.noOfItems = noOfItems;
    }
    //Setter method to set product price
    public void setPrice(double price) {
        this.price = price;
    }

    //toString method
    @Override
    public String toString() {
        return "Product : " +
                "Product_ID = " + product_ID +
                " Product_name = " + product_name +
                " No Of Items = " + noOfItems +
                " Price = " + price;
    }

    // Comparable interface implementation
    @Override
    public int compareTo(Product next_product){
        //Compare the products based on the product ID
        return this.product_ID.compareTo(next_product.product_ID);
    }



}
