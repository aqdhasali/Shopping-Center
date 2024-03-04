import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.*;
import java.util.ArrayList;

public class GUI extends WestminsterShoppingManager implements ActionListener, MouseListener{
    //Initialising variables for the home page
    private static JFrame main_frame;  //Frame for the main page
    private  static JPanel panel_one; //To hold the comboBox, Select Label, Shopping Cart Button
    private static JPanel panel_two; //To hold the product table
    private static JPanel panel_three; //To hold the product details
    private static JPanel mini_panel_one; //To hold the combo box
    private static  JPanel mini_panel_two; //To hold the shopping car button
    private static JButton shopping_cart_Button = new JButton("Shopping Cart"); //shopping cart button
    private static JLabel select_label = new JLabel("Select Product Category"); //Select Label for the comboBox
    private static JComboBox<String> choose_product = new JComboBox(); // To hold the Combox
    private static JTable product_table = new JTable();  //for the main product table

    private static JLabel select_product_label =  new JLabel("Selected Product - Details"); //Declaring the select label
    private static JButton add_to_shopping_cart_btn = new JButton("Add to Shopping Cart"); // Declaring the add to shopping car button


    //Initialising Variables for the Shopping Cart Page

    private static JFrame shopping_cart_frame; //frame for the shopping cart page
    private static JLabel total_label = new JLabel("Total"); //total label
    private static JLabel discount_label_1 = new JLabel("First Purchase Discount (10%)"); //discount label for the first purchase
    private static JLabel discount_label_2 = new JLabel("Three Items in Same Category Discount (20%): "); //discount label for the second purchase
    private static JLabel final_total_label = new JLabel("Final Total : "); // Final total label

    private static JLabel product_name_label = new JLabel("Name : ");
    private static JLabel product_id_label = new JLabel("Product ID : ");
    private static JLabel product_quantity_label = new JLabel("No of Items : ");
    private static JLabel product_category_label = new JLabel("Category :");
    private static JLabel electronic_brand_label = new JLabel("Brand : ");
    private static JLabel warranty_period_label = new JLabel("Warranty Period :");
    private static JLabel clothing_size_label = new JLabel("Size");
    private static JLabel clothing_color_label = new JLabel("Color:");

    private static JTable shopping_cart_table = new JTable();
    private static JLabel shopping_cart_product_label = new JLabel("Product");
    private static JLabel shopping_cart_quantity_label = new JLabel("Quantity");
    private static JLabel shopping_cart_price_label = new JLabel("Price");



    //variables to store the product details from the labels
    String table_product_ID;
    String table_product_name;
    String table_product_category;
    String table_product_info_one;
    String table_product_info_two;
    int table_product_quantity;
    double table_product_price;

    // variable to check the combo box chose option
    String check = "All";


    //creating lists to store the items to pass in the shopping cart
    ArrayList<ShoppingCart> final_list = new ArrayList<>();
    ArrayList<ShoppingCart> shopping_cart_list = new ArrayList<>();


    //calling the home page
    public GUI() throws IOException {  //creating method to run the program
        homePage();
    }

    //creating the home page
    public void homePage() {

        main_frame = new JFrame(); //creating the home frame
        main_frame.setTitle("Westminster Shopping Center"); //setting title for the frame
        main_frame.getContentPane().setBackground(Color.WHITE);//setting colour of the frame
        main_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        main_frame.setSize(630, 800); //setting size of the frame
        main_frame.setResizable(false);
        GridLayout gridLayout = new GridLayout(3, 1);
        main_frame.setLayout(gridLayout);

        panel_one = new JPanel();//creating the panel
//        panel_one.setLayout(new FlowLayout(FlowLayout.LEFT,10,5)); //setting the layout of the panel to null
        panel_one.setBackground(Color.WHITE);

        mini_panel_one = new JPanel();
        mini_panel_one.setLayout(new FlowLayout(FlowLayout.LEFT,50,10));
        mini_panel_one.setBackground(Color.WHITE);

        mini_panel_two = new JPanel();
        mini_panel_two.setLayout(new FlowLayout(FlowLayout.RIGHT,60,10));
        mini_panel_two.setBackground(Color.WHITE);

        select_label.setFont(new Font("Serif", Font.PLAIN, 10));

        String[] options = {"All", "Clothing", "Electronics"};//creating the combo box with the options
        choose_product = new JComboBox<>(options);
        choose_product.addActionListener(this);
        choose_product.setBackground(Color.WHITE);

        shopping_cart_Button.setBackground(new Color(255, 255, 255));
        shopping_cart_Button.setFont(new Font("Poppins", Font.PLAIN, 10));
        shopping_cart_Button.setFocusable(false);
        shopping_cart_Button.addActionListener(this);

        mini_panel_one.add(select_label);//adding to the panels
        mini_panel_one.add(choose_product);
        mini_panel_two.add(shopping_cart_Button, BorderLayout.EAST);

        panel_one.add(mini_panel_one);
        panel_one.add(mini_panel_two);

        panel_two= new JPanel();//creating the panel
        panel_two.setBackground(Color.WHITE);
        panel_two.setBounds(0,20,630,283);
        panel_two.setLayout(new FlowLayout(FlowLayout.CENTER,20,0));


        Collections.sort(product_list); //sorting the product list before passing to create the table
        updateTable(product_list); //passing the product list to populate the table

        // Create the table using the table model
        ProductTableModel productTableModel = new ProductTableModel(product_list);
        product_table = new JTable(productTableModel);
        product_table.setBounds(100, 20, 500, 300);
        product_table.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        product_table.addMouseListener(this);

        TableColumnModel columnModel = product_table.getColumnModel();
        columnModel.getColumn(4).setPreferredWidth(100);

        JTableHeader header = product_table.getTableHeader();
        header.setBackground(Color.WHITE);
        header.setPreferredSize(new Dimension(100,40));
        header.setBorder(BorderFactory.createLineBorder(Color.BLACK,1));
        product_table.setRowHeight(40);  // Set your desired row height
        product_table.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        // Create a scroll pane to hold the table
        JScrollPane scrollPane = new JScrollPane(product_table);
        scrollPane.getViewport().setBackground(Color.WHITE);

        //Adding the scroll Pane to the panel
        panel_two.add(scrollPane);

        panel_three = new JPanel();
        panel_three.setLayout(null);
        panel_three.setBounds(0,120,630,283);
        panel_three.setBackground(Color.WHITE);


        select_product_label.setBounds(20,5,200,30);
        product_id_label.setBounds(20,30,200,30);
        product_category_label.setBounds(20,50,200,30);
        product_name_label.setBounds(20,70,200,30);
        product_quantity_label.setBounds(20,90,200,30);
        electronic_brand_label.setBounds(20,110,200,30);
        warranty_period_label.setBounds(20,130,200,30);
        clothing_color_label.setBounds(20,110,200,30);
        clothing_size_label.setBounds(20,130,200,30);


        add_to_shopping_cart_btn.setBounds(170,160,170,30);
        add_to_shopping_cart_btn.setBackground(Color.WHITE);
        add_to_shopping_cart_btn.setFocusable(false);
        add_to_shopping_cart_btn.addActionListener(this);

        panel_three.add(select_product_label);
        panel_three.add(product_id_label);
        panel_three.add(product_category_label);
        panel_three.add(product_name_label);
        panel_three.add(product_quantity_label);



        panel_three.add(add_to_shopping_cart_btn);


        //adding the panel to the frame
        main_frame.add(panel_one);
        main_frame.add(panel_two);
        main_frame.add(panel_three);


        main_frame.setVisible(true);//setting the frame to be visible


    }

    //creating the shopping cart page
    public void shoppingCartPage() {
        shopping_cart_frame = new JFrame(); //creating the home frame
        shopping_cart_frame.setTitle("Shopping Cart"); //setting title for the frame
        shopping_cart_frame.getContentPane().setBackground(Color.WHITE);//setting colour of the frame
        shopping_cart_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        shopping_cart_frame.setSize(550, 700); //setting size of the frame
        shopping_cart_frame.setLayout(null);
        shopping_cart_frame.setResizable(false);
        shopping_cart_frame.dispose();

        //setting the bounds
        total_label.setBounds(360,500,300,100);
        discount_label_1.setBounds(250,540,400,100);
        discount_label_2.setBounds(138,520,400,100);
        final_total_label.setBounds(350,560,300,100);
        shopping_cart_product_label.setBounds(25,50,150,50);
        shopping_cart_quantity_label.setBounds(175, 50, 150, 50);
        shopping_cart_price_label.setBounds(325, 50, 150, 50);


        //creating borders for the table
        shopping_cart_product_label.setBorder(BorderFactory.createLineBorder(Color.BLACK,1)); // Example: black border
        shopping_cart_quantity_label.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1)); // Example: gray border with thickness 2
        shopping_cart_price_label.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1)); // Example: etched border
        shopping_cart_product_label.setHorizontalAlignment(SwingConstants.CENTER);
        shopping_cart_quantity_label.setHorizontalAlignment(SwingConstants.CENTER);
        shopping_cart_price_label.setHorizontalAlignment(SwingConstants.CENTER);


        //passing the list with the added product to populate the shopping cart table
        ShoppingCartTable(final_list);


        // Create the table using the table model
        ShoppingCartTableModel shoppingCartTableModel = new ShoppingCartTableModel(final_list);
        shopping_cart_table= new JTable(shoppingCartTableModel);


        shopping_cart_table.setBounds(25, 100, 450, 400);
        shopping_cart_table.setRowHeight(100);  // Set your desired row height
        shopping_cart_table.setBorder(BorderFactory.createLineBorder(Color.BLACK));


        //Adding the things to the frame
        shopping_cart_frame.add(shopping_cart_table);
        shopping_cart_frame.add(shopping_cart_product_label);
        shopping_cart_frame.add(shopping_cart_quantity_label);
        shopping_cart_frame.add(shopping_cart_price_label);
        shopping_cart_frame.add(total_label);
        shopping_cart_frame.add(discount_label_1);
        shopping_cart_frame.add(discount_label_2);
        shopping_cart_frame.add(final_total_label);



        shopping_cart_frame.setVisible(true);
    }




    @Override
    public void actionPerformed(ActionEvent event) {
        ArrayList<ShoppingCart> tempList = new ArrayList<>(); //creating an array list to store the temporary items to remove duplicate items from the final list before passing it into the shopping cart table

        //variables for updating the labels in the shopping cart
        int totalPrice = 0;
        int discountprice2 = 0;
        int firstPurchaseDiscount = 0;
        int finalTotal = 0;

        if (event.getSource() == shopping_cart_Button) { //functions that perform when the Shopping Cart Button is clicked
            for (ShoppingCart shoppingCartItem : shopping_cart_list) { //iterate through the shopping cart list
                //Variables to update the Shopping cart list (final list)
                int quantity = 0;
                double total_Price = 0;
                String productName = null;
                String infoOne = null;
                String infoTwo = null;


                String ProductID = shoppingCartItem.getProduct_ID();

                for (ShoppingCart updatedShoppingCartItem :shopping_cart_list) { //check whether the item with the same ID exists more than once
                    if (updatedShoppingCartItem.getProduct_ID().equals(ProductID)) {//adding to the variables and updating  the quantity and the price
                        quantity += updatedShoppingCartItem.getQuantity();
                        total_Price += updatedShoppingCartItem.getPrice();
                        productName = updatedShoppingCartItem.getProduct_Name();
                        infoOne = updatedShoppingCartItem.getInfo_one();
                        infoTwo = updatedShoppingCartItem.getInfo_two();
                    }
                }

                System.out.println("Quantity of the product :" + ProductID + " is : " + quantity + " and the total price: " + total_Price); //printing for testing purpose

                ShoppingCart sc = new ShoppingCart(ProductID, productName, infoOne, infoTwo, quantity, total_Price); //creating new constructor with updated quantity and total price of the product
                final_list.add(sc); //adding the new object to the final list

            }


            //iterating through the finalist and adding that to the temporary list to  remove duplicate values that were created
            for(ShoppingCart shoppingCartItem  :final_list){
                if(tempList.contains(shoppingCartItem)){
                    System.out.println("Duplicate values found");
                }
                else{
                    tempList.add(shoppingCartItem);
                }
            }
            final_list.clear();
            final_list.addAll(tempList);



            //updating the quantity values of the product after the user has added the items to the shopping cart
            for (ShoppingCart shoppingCartItem : final_list) {
                for (Product product : product_list) {
                    if (shoppingCartItem.getProduct_ID().equals(product.getProduct_ID())) {
                        product.setNoOfItems((product.getNoOfItems() - shoppingCartItem.getQuantity()));
                        System.out.println(product_list);
                    }
                }

            }


            // Asks whether it's the first purchase
            int consultationResult = JOptionPane.showConfirmDialog(null, "Is this your first purchase?");
            String firstPurchase;


            //calling the shopping cart page
            shoppingCartPage();


            //calculating and updating the total price before discounts
            for(ShoppingCart sc : final_list){
                totalPrice = (int) (totalPrice + sc.getPrice());
                System.out.println("The total price is: " + totalPrice);
            }
            total_label.setText("Final Total: " + totalPrice);

            //calculating and updating the discounts if more than 3 items belong to the same category
            for(ShoppingCart sc : final_list){
                if(sc.getQuantity() >= 3){
                    discountprice2 = (totalPrice * 20/100);
                    System.out.println(discountprice2);
                }
            }
            discount_label_2.setText("Three items in the same category Discount(20%)   - " + discountprice2);

            //calculating and updating the first purchase discount based on what user chose in the dialog box
            switch (consultationResult) {
                case JOptionPane.YES_OPTION -> {
                    firstPurchase = "yes";
                    System.out.println("First Purchase: " + firstPurchase);
                    firstPurchaseDiscount = totalPrice * 10 / 100;
                    discount_label_1.setText("First Purchase Discount 10%  - " + firstPurchaseDiscount);
                }
                case JOptionPane.NO_OPTION -> {
                    firstPurchase = "no";
                    System.out.println("First Purchase: " + firstPurchase);
                    discount_label_1.setText("First Purchase Discount 10%  - " + firstPurchaseDiscount);
                }
                default -> firstPurchase = "unknown";
            }

            //calculating and updating the final price of the purchase after discounts.
            finalTotal = totalPrice - (discountprice2 + firstPurchaseDiscount);
            final_total_label.setText("Final Total     " + finalTotal);

        }

        if(event.getSource() == add_to_shopping_cart_btn){//functions to perform when the add to shopping cart button is clicked

            System.out.println("The add to shopping card button has been clicked and therefore the shopping cart list is now being printed");//printing statements for testing purposes
            ShoppingCart shoppingCart = new ShoppingCart(table_product_ID ,table_product_name,table_product_info_one,table_product_info_two,1,  table_product_price);//creating constructor
            shopping_cart_list.add(shoppingCart);//adding the object to the shopping cart list
            System.out.println(shopping_cart_list);

        }

        if (event.getSource() ==choose_product){//actions to perform based on the combo box selection
            String selectedItem = choose_product.getSelectedItem().toString(); //storing the item that was selected in the combo box

            if (selectedItem.equals("All")){//if "ALL" is selected passing product_list to into the product table

                updateTable(product_list); //updating the product table with the list
                System.out.println(selectedItem);
                System.out.println(product_list);

                check = "All"; //updating the variable, it'll be checked during mouse click listener


            }else if (selectedItem.equals("Electronics")){//if "Electronics" is selected passing electronic_list to into the product table

                updateTable(electronic_list); //updating the product table with the list
                System.out.println("Electronic has been selected");
                System.out.println(electronic_list);


                check = "Electronics";//updating the variable it'll be checked during mouse click listener

            }else if (selectedItem.equals("Clothing")){//if "Clothing" is selected passing clothing_list to into the product table

                updateTable(clothing_list); //updating the product table with the list
                System.out.println("Clothing has been selected");
                System.out.println(clothing_list);

                check = "Clothing";//updating the variable it'll be checked during mouse click listener

            }else{
                System.out.println("List not found!");
            }


        }
    }

    @Override
    public void mouseClicked(MouseEvent event) { //this will perform when the user clicks on the table to display the labels

        int selectedRow = product_table.getSelectedRow();
        TableModel model = product_table.getModel();
        table_product_ID = model.getValueAt(selectedRow,0).toString();


        if(check.equals("All")){ //based on what's selected in the combo box, the table will change therefore the data to be clicked also will change
            System.out.println("The table that's being displayed is ALL therefore items will be selected from product list");

            for(Product product:product_list){
                if(table_product_ID.contains((product.getProduct_ID()))){

                    table_product_name = product.getProduct_name();
                    table_product_quantity = product.getNoOfItems();
                    table_product_price = product.getPrice();


                    if(product instanceof Electronics){ //updating the labels
                        table_product_category = "Electronics";
                        table_product_info_one = ((Electronics) product).getBrand();
                        table_product_info_two = ((Electronics) product).getWarranty_period();
                        electronic_brand_label.setText("Brand : "+table_product_info_one);
                        warranty_period_label.setText("Warranty Period : "+table_product_info_two);

                        product_id_label.setText("Product ID :" + table_product_ID);
                        product_category_label.setText("Product Category : " + table_product_category);
                        product_name_label.setText("Product Name : "+ table_product_name);
                        product_quantity_label.setText("Items Available : " + String.valueOf(table_product_quantity));

                        clothing_size_label.setVisible(false);
                        clothing_color_label.setVisible(false);

                        electronic_brand_label.setVisible(true);
                        warranty_period_label.setVisible(true);

                        panel_three.add(electronic_brand_label);
                        panel_three.add(warranty_period_label);


                    }
                    else if(product instanceof Clothing){ //updating the labels
                        table_product_category = "Clothing";
                        table_product_info_one = ((Clothing) product).getSize();
                        table_product_info_two = ((Clothing) product).getColor();

                        clothing_size_label.setText("Size : "+table_product_info_one);
                        clothing_color_label.setText("Color : "+table_product_info_two);

                        product_id_label.setText("Product ID :" + table_product_ID);
                        product_category_label.setText("Product Category : " + table_product_category);
                        product_name_label.setText("Product Name : "+ table_product_name);
                        product_quantity_label.setText("Items Available : " + String.valueOf(table_product_quantity));


                        electronic_brand_label.setVisible(false);
                        warranty_period_label.setVisible(false);

                        clothing_size_label.setVisible(true);
                        clothing_color_label.setVisible(true);


                        panel_three.add(clothing_size_label);
                        panel_three.add(clothing_color_label);


                    }


                }
            }



        }else if(check.equals("Electronics")){ //based on what's selected in the combo box, the table will change therefore the data to be clicked also will change
            System.out.println("The table that's being displayed is ELECTRONICS therefore items will be selected from electronics list");
            for(Product product:electronic_list){

                if(table_product_ID.contains((product.getProduct_ID()))){

                    table_product_name = product.getProduct_name();
                    table_product_quantity= product.getNoOfItems();
                    table_product_price = product.getPrice();

                    if(product instanceof Electronics){ //updating the labels
                        table_product_category = "Electronics";
                        table_product_info_one = ((Electronics) product).getBrand();
                        table_product_info_two = ((Electronics) product).getWarranty_period();
                        electronic_brand_label.setText("Brand : "+table_product_info_one);
                        warranty_period_label.setText("Warranty Period : "+table_product_info_two);

                        clothing_size_label.setVisible(false);
                        clothing_color_label.setVisible(false);

                        electronic_brand_label.setVisible(true);
                        warranty_period_label.setVisible(true);

                        panel_three.add(electronic_brand_label);
                        panel_three.add(warranty_period_label);
                    }
                    else if(product instanceof Clothing){ //updating the labels
                        table_product_category = "Clothing";
                        table_product_info_one = ((Clothing) product).getSize();
                        table_product_info_two = ((Clothing) product).getColor();

                        clothing_size_label.setText("Size : "+table_product_info_one);
                        clothing_color_label.setText("Color : "+table_product_info_two);

                        clothing_size_label.setVisible(true);
                        clothing_color_label.setVisible(true);

                        electronic_brand_label.setVisible(false);
                        warranty_period_label.setVisible(false);

                        panel_three.add(clothing_size_label);
                        panel_three.add(clothing_color_label);
                    }
                }



            }
        }else if(check.equals("Clothing")){ //based on what's selected in the combo box, the table will change therefore the data to be clicked also will change
            System.out.println("The table that's being displayed is Clothing therefore items will be selected from clothing list");
            for(Product product:clothing_list){

                if(table_product_ID.contains((product.getProduct_ID()))){

                    table_product_name = product.getProduct_name();
                    table_product_quantity = product.getNoOfItems();
                    table_product_price = product.getPrice();

                    if(product instanceof Electronics){ //updating the labels
                        table_product_category = "Electronics";
                        table_product_info_one = ((Electronics) product).getBrand();
                        table_product_info_two = ((Electronics) product).getWarranty_period();
                        electronic_brand_label.setText("Brand : "+table_product_info_one);
                        warranty_period_label.setText("Warranty Period : "+table_product_info_two);

                        clothing_size_label.setVisible(false);
                        clothing_color_label.setVisible(false);

                        electronic_brand_label.setVisible(true);
                        warranty_period_label.setVisible(true);

                        panel_three.add(electronic_brand_label);
                        panel_three.add(warranty_period_label);
                    }
                    else if(product instanceof Clothing){ //updating the labels
                        table_product_category = "Clothing";
                        table_product_info_one = ((Clothing) product).getSize();
                        table_product_info_two = ((Clothing) product).getColor();

                        clothing_size_label.setText("Size : "+table_product_info_one);
                        clothing_color_label.setText("Color : "+table_product_info_two);

                        clothing_size_label.setVisible(true);
                        clothing_color_label.setVisible(true);

                        electronic_brand_label.setVisible(false);
                        warranty_period_label.setVisible(false);

                        panel_three.add(clothing_size_label);
                        panel_three.add(clothing_color_label);
                    }
                }

                product_id_label.setText("Product ID :" + table_product_ID);
                product_category_label.setText("Product Category : " + table_product_category);
                product_name_label.setText("Product Name : "+ table_product_name);
                product_quantity_label.setText("Items Available : " + String.valueOf(table_product_quantity));
            }
        }

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }


    public void updateTable(ArrayList<Product> list){ // this the method where the product table is being populated and new model for the table is created
       // Create the table model with the data
       ProductTableModel productTableModel = new ProductTableModel(list);

       // update the table using the set model keyword
       product_table.setModel(productTableModel);

       // Repaint the panel to reflect the changes
       panel_two.revalidate();
       panel_two.repaint();

   }

    private void ShoppingCartTable(ArrayList<ShoppingCart> finalList) { // this the method where the shopping cart table is being populated and new model for the table is created

        // Create the table model with the data
        ShoppingCartTableModel shoppingCartTableModel = new ShoppingCartTableModel(finalList);

        // update the table using the set model keyword
        shoppingCartTableModel.setModel(shoppingCartTableModel);
    }





}

