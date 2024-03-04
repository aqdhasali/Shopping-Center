import java.io.Serializable;

public class Electronics extends Product implements Serializable  {
    //instance variables
    private String brand; //to store the electronic brand
    private String warranty_period; //to store the warranty period

    //Default Constructor
    public Electronics(){}

    //Constructor with passed values
    public Electronics(String productID, String product_name,int noOfItems,double price,String brand,String warranty_period){
        super(productID,product_name,noOfItems,price);
        this.brand = brand;
        this.warranty_period=warranty_period;
    }

    //Getter Method To Get the Brand
    public String getBrand() {
        return brand;
    }

    //Setter Method to Set the Brand
    public void setBrand(){
        this.brand = brand;
    }

    //Getter Method to Get the Warranty Period
    public String getWarranty_period(){
        return warranty_period;
    }

    //Setter Method to Set the Warranty Period
    public void setWarranty_period(){
        this.warranty_period = warranty_period;
    }

    //toString method
    @Override
    public String toString() {
        return super.toString() +
                " Product Type : Electronic" +
                " Brand = " + brand +
                " Warranty Period = " + warranty_period;
    }
}
