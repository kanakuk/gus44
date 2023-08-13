package group1.gus01;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import other.*;
import other.Inventory;
import other.Product;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import static group1.gus01.MainController.displayNoMatchError;
import static other.Inventory.searchPartsInventory;


/**modifies the chosen product. */
public class ModifyProduct implements Initializable{
    public TableView associatedTable;
    Product product;
    private int index;
    public TextField idTextField;
    public TextField nameTextField;
    public TextField inventoryTextField;
    public TextField priceTextField;
    public TextField minTextField;
    public TextField maxTextField;
    public TextField searchTextField;
    public TableView partsTable;
    public TableColumn id1Column;
    public TableColumn name2Column;
    public TableColumn inventory3Column;
    public TableColumn price4Column;
    public TableColumn id5Column;
    public TableColumn name6Column;
    public TableColumn inventory7Column;
    public TableColumn price8Column;

    /**this was a problem for me to use the index to add parts to the associated table. */

    /**starts associated list. */
    private ObservableList<Part> associatedParts = FXCollections.observableArrayList();

    /**moves a part from parts table to the associated table
     * @param actionEvent action is called when button clicked
     * */
    public void addButtonAction(ActionEvent actionEvent) throws IOException{
        Part part = (Part) partsTable.getSelectionModel().getSelectedItem();
        associatedParts.add(part);
        associatedTable.setItems(associatedParts);
    }

    /**a part is removed upon a user changing their mind
     * @param actionEvent method called upon button press
     * */
    public void removeButtonAction(ActionEvent actionEvent) {
        Part selectedPart = (Part) associatedTable.getSelectionModel().getSelectedItem();
        if (selectedPart == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("error");
            alert.setContentText("part not selected");
            alert.showAndWait();
        } else {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("confirmation");
            alert.setContentText("are you sure you want to delete?");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                associatedParts.remove(selectedPart);
            }
        }
    }

    /**view changes to first screen
     * @param actionEvent action is called when button is called
     * */
    public void cancelButtonAction(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("main.fxml"));
        Stage stage = (Stage) ((Button)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, 1000, 369);
        stage.setTitle("back");
        stage.setScene(scene);
        stage.show();
    }

    /**saves the new product to the inventory
     * @param actionEvent method called upon saveButton click
     * */
    public void saveButtonAction(ActionEvent actionEvent) throws IOException{
        try {
            int id = Integer.parseInt(idTextField.getText());
            String name = nameTextField.getText();
            double price = Double.parseDouble(priceTextField.getText());
            int stock = Integer.parseInt(inventoryTextField.getText());
            int max = Integer.parseInt(maxTextField.getText());
            int min = Integer.parseInt(minTextField.getText());

            if (min > max) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("error");
                alert.setContentText("min must be less than max");
                alert.showAndWait();
            } else if (stock < min || stock > max) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("error");
                alert.setContentText("inv must");
                alert.showAndWait();
            } else {
                Product newProd = new Product(id, name, price, stock, min, max);
                //gets all associated Parts
                newProd.getAllAssociatedParts().addAll(associatedParts);
                //dont add just update productInventory.addProduct(newProd);
                Inventory.updateProduct(index, newProd);
                Parent root = FXMLLoader.load(getClass().getResource("main.fxml"));
                Stage stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
                Scene scene = new Scene(root,1000, 1000);
                stage.setTitle("home");
                stage.setScene(scene);
                stage.show();
            }
        } catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("error");
            alert.setContentText("enter valid");
            alert.showAndWait();
        }
    }

    /**transfers parts from inventory to the addProduct scene. */
    public void sendProduct(Product product, int index) throws IOException{
        this.index = index;
        this.product = product;
        idTextField.setText(String.valueOf(product.getId()));
        nameTextField.setText(String.valueOf(product.getName()));
        priceTextField.setText(String.valueOf(product.getPrice()));
        inventoryTextField.setText(String.valueOf(product.getStock()));
        minTextField.setText(String.valueOf(product.getMin()));
        maxTextField.setText(String.valueOf(product.getMax()));
        //flipping moving from parts to associated parts
        associatedParts.addAll(product.getAllAssociatedParts());
    }

    /**creates the settings of the two tables. */
    public void initialize(URL url, ResourceBundle resourceBundle) {
        id1Column.setCellValueFactory(new PropertyValueFactory<>("Id"));
        name2Column.setCellValueFactory(new PropertyValueFactory<>("Name"));
        price4Column.setCellValueFactory(new PropertyValueFactory<>("Price"));
        inventory3Column.setCellValueFactory(new PropertyValueFactory<>("Stock"));
        partsTable.setItems(Inventory.getAllParts());

        id5Column.setCellValueFactory(new PropertyValueFactory<>("Id"));
        name6Column.setCellValueFactory(new PropertyValueFactory<>("Name"));
        price8Column.setCellValueFactory(new PropertyValueFactory<>("Price"));
        inventory7Column.setCellValueFactory(new PropertyValueFactory<>("Stock"));
        associatedTable.setItems(associatedParts);

    }

    public void search1TextAction(ActionEvent actionEvent) {
        String query = searchTextField.getText().toLowerCase();
        ObservableList<Part> parts = searchPartsInventory(query);
        if (parts.size() == 0) {
            if(query.matches("\\d*") == false){
                displayNoMatchError();
                return;
            }
            int id = Integer.parseInt(query);
            Part part = searchPartsInventory(id);
            if (part != null) {
                parts.add(part);
            }
        }
        if (parts.size() == 0) {
            displayNoMatchError();
        }
        partsTable.setItems(parts);
    }



}


























































