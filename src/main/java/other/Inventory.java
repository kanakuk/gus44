package other;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.concurrent.atomic.AtomicInteger;

/**defines the inventory. */
public class Inventory {
    /**keeps track of changes in a list. */
    public static ObservableList<Part> allParts = FXCollections.observableArrayList();
    public static ObservableList<Product> allProducts = FXCollections.observableArrayList();

    /**sets addedPart on the allParts list
     * @param newPart is the part to be added to the allParts list
     */
    public static void addPart(Part newPart) { allParts.add(newPart); }

    /**sets addedProduct on the allParts list
     * @param newProduct is the part to be added to the allParts list
     */
    public static void addProduct(Product newProduct) { allProducts.add(newProduct); }

    /**returns a list of allParts in the inventory
     * @return returns seeable list of allParts
     */
    public static ObservableList<Part> getAllParts() { return allParts; }

    /**returns a list of allProducts from the inventory
     * @return returns a seeable list of allProducts
     */
    public static ObservableList<Product> getAllProducts() { return allProducts; }

    /**updates a part matching its index
     * @param index line where that part is in the seeable list
     * @param selectedPart updated part set to that index
     */
    public static void updatePart(int index, Part selectedPart) {allParts.set(index, selectedPart); }

    /**updates a product matching its index
     * @param index line where that product is in the seeable list
     */
    public static void updateProduct(int index, Product selectedProduct) {allProducts.set(index, selectedProduct); }

    /**searches for a part in the inventory
     * @param partId part id
     * @return returns matched part to the partId
     */
    public static Part searchPartsInventory(int partId) {
        for (Part part: allParts) {
            if (part.getId() == partId ) {
                return part;
            }
        }
        return null;
    }

    /**searches inventory for a part
     * @param partName name of part
     * @return returns namedpart
     * */
    public static ObservableList<Part> searchPartsInventory(String partName) {
        ObservableList<Part> namedParts = FXCollections.observableArrayList();
        ObservableList<Part> allParts = Inventory.getAllParts();
        for (Part part: allParts) {
            if (part.getName().contains(partName)) {
                namedParts.add(part);
            }
        }
        return namedParts;
    }
    /**searches for a product using the id
     * @param productId product's id
     * @return returns matching product
     * */
    public static Product searchProductsInventory(int productId) {
        for (Product product: allProducts) {
            if (product.getId() == productId ) {
                return product;
            }
        }
        return null;
    }

    /**searches inventory for a product
     * @param productName name of product
     * @return returns namedProduct
     * */
    public static ObservableList<Product> searchProductsInventory(String productName) {
        ObservableList<Product> namedProducts = FXCollections.observableArrayList();
        ObservableList<Product> allProducts = Inventory.getAllProducts();
        for (Product product: allProducts) {
            if (product.getName().contains(productName)) {
                namedProducts.add(product);
            }
        }
        return namedProducts;
    }

    /**method to remove a part from the inventory
     * @param selectedPart
     * */
    public static boolean deletePart(Part selectedPart){
        if (allParts.contains(selectedPart)) {
            allParts.remove(selectedPart);
            return true;
        } else {
            return false;
        }
    }

    /**deletes a product from the table
     * @param actionEvent method called upon button press
     * */
    public static boolean deleteProduct(Product selectedProduct) {
        if (allProducts.contains(selectedProduct)) {
            allProducts.remove(selectedProduct);
            return true;
        } else {
            return false;
        }
    }

    /**method to search for a part using its id
     * @param partId id of part that the user is looking for
     * @return returns the part the user is asking for
     * */
    public static Part lookupPart(int partId) {
        ObservableList<Part> allParts = Inventory.getAllParts();
        for (int i=0; i < allParts.size(); i++) {
            Part part = allParts.get(i);
            if (part.getId() == partId) {
                return part;
            }
        }
        return null;
    }


    /**method to search for a part using its name
     * @param partName is the name the user is using to search
     * @return returns the part the user is asking for
     * */
    public static ObservableList<Part> lookupPart(String partName) {
        ObservableList<Part> matchingParts = FXCollections.observableArrayList();
        ObservableList<Part> allParts = Inventory.getAllParts();
        for (Part part : allParts) {
            if (part.getName().contains(partName)) {
                matchingParts.add(part);
            }
        }
        return matchingParts;
    }
/**creates a random number starting at 100
 * number is unique
 * */
public static AtomicInteger getNewPartId = new AtomicInteger(100);
public static AtomicInteger getNewProductId = new AtomicInteger(100);
}

















































