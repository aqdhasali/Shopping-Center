import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class ShoppingCartTableModel extends AbstractTableModel {

    private String[] columns = {"Product", "Quantity", "Price"};
    private final ArrayList<ShoppingCart> finalList;


    //Constructor
    public ShoppingCartTableModel(ArrayList<ShoppingCart> finalList) {
        this.finalList = finalList;
    }

    // To get the number of rows in the table
    @Override
    public int getRowCount() {
        return finalList.size();
    }

    //To get the number of columns in the table
    @Override
    public int getColumnCount() {
        return columns.length;
    }

    // Get the value at a specific row and column in the table
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        String yes = "";
        Object temp;
        if (columnIndex == 0) {
            System.out.println(finalList.get(rowIndex).getProduct_ID() + finalList.get(rowIndex).getProduct_Name() + finalList.get(rowIndex).getInfo_one() + finalList.get(rowIndex).getInfo_two() );
            return finalList.get(rowIndex).getProduct_ID()
                    + " | " + finalList.get(rowIndex).getProduct_Name() + " | " +
                    finalList.get(rowIndex).getInfo_one() + " , " + finalList.get(rowIndex).getInfo_two() ;
        }
        else if (columnIndex == 1) {
            System.out.println(finalList.get(rowIndex).getQuantity());
            return finalList.get(rowIndex).getQuantity();
        } else if (columnIndex == 2) {
             //Check the instance type and sets the type
            System.out.println(finalList.get(rowIndex).getPrice());
            return finalList.get(rowIndex).getPrice();
        }
        return null;
    }

    public String getColumnName(int col) {
        return columns[col];
    }
    public void setModel(ShoppingCartTableModel shoppingCartTableModel) {

    }
}
