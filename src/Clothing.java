import java.io.Serializable;

public class Clothing extends Product implements Serializable {
    private String size; // To store the clothing size
    private String color; //To store the color of th clothing

    //Default Constructor
    public Clothing(){}

    //Constructor with passed values
    public Clothing(String productID, String product_name,int noOfItems,double price,String size, String color){
        super(productID,product_name,noOfItems,price);
        this.size = size;
        this.color = color;
    }

    //Getter Method to get the size of the clothing
    public String getSize(){
        return size;
    }

    //Setter Method to set the size of the clothing
    public void setSize(){
        this.size = size;
    }

    //Getter Method to get the color of the clothing
    public String getColor(){
        return color;
    }

    //Setter Method to set the color of the clothing
    public void setColor(){
        this.color = color;
    }

    //to String method
    @Override
    public String toString() {
        return super.toString() +
                " Product Type : Clothing" +
                " Size = " + size +
                " Color = " + color;
    }
}
