import java.io.IOException;

public interface ShoppingManager {

    void loadGUI() throws IOException;
    void addProduct();
    void deleteProduct();
    void printProductList();
    void saveProductsToFile();
    void loadProductsFromFile();

}
