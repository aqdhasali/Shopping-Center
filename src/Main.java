import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException, ClassNotFoundException{
        Scanner input = new Scanner(System.in);

        String user_name;
        String user_password;

        System.out.println("==========WELCOME TO WESTMINSTER SHOPPING CENTER!=========");

        WestminsterShoppingManager manager = new WestminsterShoppingManager(); //Creating a new object


        int choice;
        while (true) {
            System.out.println("SELECT 1 TO OPEN MANAGER MENU\n" + "SELECT 2 TO OPEN GUI ");
            System.out.print("Enter Your Choice : ");
            choice = input.nextInt();

            switch (choice) {
                case 1:
                    manager.menu(); //Loading a manager menu.
                    break;
                case 2:
                    System.out.println("Enter User Name : ");
                    user_name = input.next();

                    System.out.println("Enter Password : ");
                    user_password = input.next();

                    User user = new User(user_name, user_password);

                    manager.loadGUI(); //Loading the gui
                    break;

            }
        }
    }

}
