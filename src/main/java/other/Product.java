package other;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * @author augustinegonzales
 * */
public class Product {
    private ObservableList<Part> associatedParts = FXCollections.observableArrayList();
    private int id;
    private String name;
    private double price;
    private int stock;
    private int min;
    private int max;

    //sets 6 values
    public Product (int id, String name, double price, int stock, int min, int max) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.min = min;
        this.max = max;
    }

    /**
     * @return the id
     */
    public int getId() { return id; }

    /**
     * @param id the id to set
     */
    public void setId(int id) { this.id = id;}

    /**
     * @return the name
     */
    public String getName() { return name; }

    /**
     * @param name the name to set
     */
    public void setName(String name) { this.name = name; }

    /**
     * @return the price
     */
    public double getPrice() {return price; }

    /**
     * @param price set
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * @return the stock
     */
    public int getStock() {
        return stock;
    }

    /**
     * @param the stock to set
     */
    public void setStock(int stock) {
        this.stock = stock;
    }

    /**
     * @return the min
     */
    public int getMin() {
        return min;
    }

    /**
     * @param min the min to set
     */
    public void setMin(int min) {
        this.min = min;
    }

    /**
     * @return the max
     */
    public int getMax() {
        return max;
    }

    /**
     * @param max the max to set
     */
    public void setMax(int max) {
        this.max = max;
    }

    /**
     * @param
     */

    /**adds an associatedPart to the associatedPartList
     * @param part the part added to the list
     * missing methods from uml
     * */
    public void addAssociatedPart(Part part) {
        associatedParts.add(part);
    }

    /**deletes an associatedPart
     * @param selectedAssociatedPart selectedPart to delete
     * @return returns a true if succesful delete
     * */
    public boolean deleteAssociatedPart(Part selectedAssociatedPart) {
        if(associatedParts.contains(selectedAssociatedPart)) {
            associatedParts.remove(selectedAssociatedPart);
            return true;
        } else {
            return false;
        }
    }

    /**returns the associatedParts list
     * @return observable associatedList
     * */
    public ObservableList<Part> getAllAssociatedParts() {
        return associatedParts;
    }

}
