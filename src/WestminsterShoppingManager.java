import java.io.*;
import java.util.*;



public class WestminsterShoppingManager implements ShoppingManager {
    protected static ArrayList<Product> product_list = new ArrayList<>(); // to store the entire products
    protected static ArrayList<Product> electronic_list = new ArrayList<>(); //to store  electronic products
    protected static ArrayList<Product> clothing_list = new ArrayList<>(); //to store clothing products
    static Scanner input = new Scanner(System.in);

    //Variables in the menu
    static int choice;

    //Variables in the Adding Method
    static String product_id; //to store product id
    static String product_name; //to store product name
    static int no_Of_Items; //to store the number items in the product
    static double product_price; //to store product price

    //Electronics
    static String electronic_product_brand; //to store product electronic product brand
    static String warranty_period; //to store the warranty period of the electronic product

    //Clothing
    static String clothing_color; //to store the color of the clothing product
    static String clothing_size; //to store the size of the clothing product

    //Delete product Variables
    static String delete_product_id; //to store the temporary product id.

    public WestminsterShoppingManager(){}



    public void menu() {

        while (true) {

            System.out.println("---------------MANAGER MENU------------------");
            System.out.println("""
                    SELECT 1 TO ADD A PRODUCT TO THE SYSTEM :\s
                    SELECT 2 TO DELETE PRODUCT FROM THE SYSTEM
                    SELECT 3 TO PRINT THE LIST OF PRODUCTS IN THE SYSTEM
                    SELECT 4 TO SAVE THE LIST OF PRODUCTS IN A FILE
                    SELECT 5 TO LOAD FILE CONTAINING THE LIST OF PRODUCTS
                    SELECT 6 TO EXIT THE MANAGER MENU AND GO TO MAIN MENU""");

            try {
                System.out.println("Enter Your Choice : ");
                choice = input.nextInt();

                while (choice < 1 || choice > 6) {
                    System.out.println("Entered Choice Not in Range! Please Try Again : ");
                    choice = input.nextInt();
                }
                switch (choice) {
                    case 1 -> addProduct();
                    case 2 -> deleteProduct();
                    case 3 -> printProductList();
                    case 4 -> saveProductsToFile();
                    case 5 -> loadProductsFromFile();
                    case 6 -> {
                        System.out.println("Moving to the Menu Page");
                        Main.main(new String[]{});
                    }
                    default -> System.out.println("Invalid Input");
                }
            } catch (InputMismatchException | IOException | ClassNotFoundException exception) {
                System.out.println("Enter an Integer!");
                input.nextLine();
            }

        }
    }


    public void addProduct() {
        if (product_list.size()<=50){
            try {
                do{
                    System.out.println("Enter product ID : ");
                    product_id = input.next();

                    boolean id_existing_status = false;

                    for (Product product:product_list){
                        if (product_id.equals(product.getProduct_ID())){
                            id_existing_status = true;
                            System.out.println("Entered Product ID Already In The System!");
                            break;
                        }
                    }

                    if (!id_existing_status){
                        System.out.println("Enter product name : ");
                        product_name = input.next();
                        break;
                    }
                }while (true);


                System.out.println("Enter the No of Items : ");
                no_Of_Items = input.nextInt();

                System.out.println("Enter Product Price : ");
                do{
                    if(!input.hasNextDouble()){
                        System.out.println("Enter a Valid Price : ");
                        input.next();
                    }else {
                        product_price = input.nextDouble();
                        break;
                    }
                }while (true);


            } catch (InputMismatchException exception) {
                System.out.println("Enter the Correct Data Type");
            }

            System.out.println("""
                    SELECT;
                    1.IF YOU'RE ADDING AN ELECTRONIC ITEM
                    2.IF YOU'RE ADDING A CLOTHING ITEM""");

            try{
                System.out.println("Enter Your Choice : ");
                int choice = input.nextInt();

                while (choice < 1 || choice > 2) {
                    System.out.println("Enter the Choice within the given range");
                    choice = input.nextInt();
                }
                if (choice == 1) {
                    System.out.println("========ELECTRONICS========");

                    System.out.println("Enter The Brand : ");
                    electronic_product_brand = input.next();

                    System.out.println("Enter the Warranty Period (Enter Number Of Weeks)");
                    int weeks = input.nextInt();


                    warranty_period = weeks + "-" + "Weeks";

                    Product new_electronic_item = new Electronics(product_id, product_name, no_Of_Items, product_price, electronic_product_brand, warranty_period); //creating new electronic object
                    product_list.add(new_electronic_item); //adding it to the product list
                    electronic_list.add(new_electronic_item); //adding it to the electronic list

                    System.out.println("Enter\n1. To go to the main menu : \n2. To add more products ");
                    choice = input.nextInt();
                    while (choice<1 || choice>2){
                        System.out.println("Enter the Choice in the Given Range : ");
                        choice = input.nextInt();
                    }
                    if (choice == 1) {
                        menu();
                    } else {
                        addProduct();
                    }

                } else if (choice == 2) {
                    System.out.println("=========CLOTHING=========");

                    System.out.println("Enter the Size S/M/L : ");
                    clothing_size = input.next();

                    System.out.println("Enter the color : ");
                    clothing_color = input.next();


                    Product new_clothing_item = new Clothing(product_id, product_name, no_Of_Items, product_price, clothing_size, clothing_color); //Creating a clothing object
                    product_list.add(new_clothing_item); //adding it to the product list
                    clothing_list.add(new_clothing_item); //adding it to the clothing list


                    System.out.println("Enter\n1. To go to the main menu : \n2. To add more products ");
                    choice = input.nextInt();
                    while (choice<1 || choice>2){
                        System.out.println("Enter the Choice in the Given Range : ");
                        choice = input.nextInt();
                    }
                    if (choice == 1) {
                        menu();
                    } else {
                        addProduct();
                    }

                }
            }catch (InputMismatchException exception){
                System.out.println("Enter An Integer");
            }
        }


    }


    @Override
    public  void deleteProduct() {

        System.out.println("Enter Product ID to Delete : ");
        delete_product_id = input.next(); //Getting the id which user wants to delete
        boolean product_found = false;

        for(Product product:product_list){
            if(product.getProduct_ID().equals(delete_product_id)) {
                product_list.remove(product);
                System.out.println("Product Deleted Successfully");
                System.out.println("No of Available Products In The System : " + product_list.size());
                product_found = true;
                break;
            }
        }

        if(!product_found){
            System.out.println("Product ID Not in the System");
        }

    }

    public void printProductList() {
        //checking if the list is empty
        if (product_list.isEmpty()) {
            System.out.println("Product List is Empty");
        }

        //Sorting the list by a th product ID using the comparator interface
        Collections.sort(product_list, new Comparator<Product>() {
            public int compare(Product p1, Product p2) {
                return p1.getProduct_ID().compareTo(p2.getProduct_ID());
            }
        });

        //Printing the list after sorting
        for (Product product : product_list) {
            System.out.println(product);
        }
    }

    public void saveProductsToFile() {
        //Write to the file
        try {
            FileOutputStream fos = new FileOutputStream("product_list.ser"); //creating a new file output stream to write/create a new file
            ObjectOutputStream oos = new ObjectOutputStream(fos);  //creating to write objects to the fos in serializable formats
            //traversing through the list and convert the products to serializable format
            for (Product product : product_list) {
                oos.writeObject(product);
            }
            oos.close();
            System.out.println("Product(s) written to file successfully!");


        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }

    public void loadProductsFromFile() {
        try {
            FileInputStream file_input = new FileInputStream("product_list.ser");  //creating file input stream to read from the file
            ObjectInputStream input = new ObjectInputStream(file_input);  //Creating the object input stream to deserialize the object from the file
            ArrayList<Product> product_list_temp = new ArrayList<>(); //Crating a temporary arraylist to store the loaded object

            try {
                // Continue reading objects until the end of the file.
                while (true) {
                    Product obj = (Product) input.readObject();
                    //keep adding the product to the temproray list
                    product_list_temp.add(obj);
                }
            } catch (EOFException e) {
                // End of file reached
            }

            product_list.clear();

            for (Product product:product_list_temp){
                if (product instanceof Electronics){
                    product_list.add(product);
                    electronic_list.add(product);
                }
                else{
                    product_list.add(product);
                    clothing_list.add(product);
                }
            }


            System.out.println("Products Loaded Successfully");

        } catch (FileNotFoundException e) {
            // Handle file not found exception
            e.printStackTrace();
        } catch (IOException | ClassNotFoundException e) {
            // Handle other exceptions
            e.printStackTrace();
        }
    }


    //method to load the gui
    @Override
    public void loadGUI() throws IOException {
        new GUI();
    }
}
