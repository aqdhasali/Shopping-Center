import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.util.ArrayList;

public class ProductTableModel extends AbstractTableModel {
    private String[] columns = {"Product ID", "Name", "Category", "Price(£)","Info"};
    private ArrayList<Product> list;


    //Constructor
    public ProductTableModel(ArrayList<Product> productList) {
        this.list = productList;
    }

    // To get the number of rows in the table
    @Override
    public int getRowCount() {
        return list.size();
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
            return list.get(rowIndex).getProduct_ID();
        } else if (columnIndex == 1) {
            return list.get(rowIndex).getProduct_name();
        } else if (columnIndex == 2) {
            return (list.get(rowIndex) instanceof Electronics) ? "Electronics" : "Clothing"; //Check the instance type and sets the type
        } else if (columnIndex == 3) {
            return list.get(rowIndex).getPrice();
        } else if (columnIndex == 4) {
            if (list.get(rowIndex) instanceof Electronics){
                return ((Electronics) list.get(rowIndex)).getBrand() + " " +(((Electronics) list.get(rowIndex)).getWarranty_period());
            }
            else{
                return  ((Clothing) list.get(rowIndex)).getColor()+ " " +(((Clothing) list.get(rowIndex)).getSize());
            }
        }
        return null;
    }

    public String getColumnName(int col) {
        return columns[col];
    }





}