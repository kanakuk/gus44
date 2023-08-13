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
import other.InHouse;
import other.Inventory;
import other.Product;
import other.Part;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import static other.Inventory.lookupPart;

/**adds a product to the inventory. */
public class AddProduct implements Initializable{
    public TextField nameTextField;
    public TextField inventoryTextField;
    public TextField priceTextField;
    public TextField minTextField;
    public TextField maxTextField;
    public TextField searchTextField;
    public TableView partsTable;
    public TableView associatedTable;
    public TableColumn id1Column;
    public TableColumn name2Column;
    public TableColumn inventory3Column;
    public TableColumn price4Column;
    public TableColumn id5Column;
    public TableColumn name6Column;
    public TableColumn price8Column;
    public TableColumn inventory7Column;
    public TableView<Part> associatedPartTable;
    private ObservableList<Part> associatedParts = FXCollections.observableArrayList();

    /**associates a part with a product
     * @param actionEvent action is called when button clicked
     * */
    public void addButtonAction(ActionEvent actionEvent) {
        Part part = (Part) partsTable.getSelectionModel().getSelectedItem();
        associatedParts.add(part);

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
    public void saveButtonAction(ActionEvent actionEvent) throws IOException {
        try {
            int id = Inventory.getNewProductId.incrementAndGet();
            String name = nameTextField.getText();
            int stock = Integer.parseInt(inventoryTextField.getText());
            double price = Double.parseDouble(priceTextField.getText());
            int min = Integer.parseInt(minTextField.getText());
            int max = Integer.parseInt(maxTextField.getText());

            if (min > max) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("error");
                alert.setContentText("minimum");
                alert.showAndWait();
            }
            else if (stock < min || stock > max) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("error");
                alert.setContentText("inv needs");
                alert.showAndWait();
            }
            else {
                Product newProduct = new Product(id, name, price, stock, min, max);
                //put them in your list of parts
                newProduct.getAllAssociatedParts().addAll(associatedParts);
                Inventory.addProduct(newProduct);
                Parent root = FXMLLoader.load(getClass().getResource("main.fxml"));
                Stage stage = (Stage)((Button)actionEvent.getSource()).getScene().getWindow();
                Scene scene = new Scene(root, 1000, 1000);
                stage.setTitle("home");
                stage.setScene(scene);
                stage.show();
            }
        }
        catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("error");
            alert.setContentText("enter valid");
            alert.showAndWait();
        }
    }

    /**transfers parts from inventory to the addProduct scene.
     * this part was hard because the next method has to match with int and doubles
     * */
    public void sendPart (Part part) throws IOException{
        id1Column.setText(String.valueOf(part.getId()));
        name2Column.setText(String.valueOf(part.getName()));
        inventory3Column.setText(String.valueOf(part.getStock()));
        price4Column.setText(String.valueOf(part.getPrice()));
        minTextField.setText(String.valueOf(part.getMin()));
        maxTextField.setText(String.valueOf(part.getMax()));
        }

    /**creates first setting of the two tables. */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        id1Column.setCellValueFactory(new PropertyValueFactory<>("Id"));
        name2Column.setCellValueFactory(new PropertyValueFactory<>("Name"));
        inventory3Column.setCellValueFactory(new PropertyValueFactory<>("Stock"));
        price4Column.setCellValueFactory(new PropertyValueFactory<>("Price"));
        partsTable.setItems(Inventory.getAllParts());
        id5Column.setCellValueFactory(new PropertyValueFactory<>("Id"));
        name6Column.setCellValueFactory(new PropertyValueFactory<>("Name"));
        inventory7Column.setCellValueFactory(new PropertyValueFactory<>("Stock"));
        price8Column.setCellValueFactory(new PropertyValueFactory<>("Price"));
        associatedTable.setItems(associatedParts);
    }

    /**a part is removed upon a user changing their mind
     * @param actionEvent method called upon button press
     * */
    public void removeButtonAction(ActionEvent actionEvent) {
        Part selectedPart = (Part) partsTable.getSelectionModel().getSelectedItem();
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

    /**user can search for a part
     * @param actionEvent method called upon button press
     * */
    public void search1TextAction(ActionEvent actionEvent) {
        String partName = searchTextField.getText();
        ObservableList<Part> parts = lookupPart(partName);
        if (parts.size() == 0) {
            try {
                int partId = Integer.parseInt(partName);
                Part part = lookupPart(partId);
                if (part != null)
                    parts.add(part);
            } catch (NumberFormatException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("error");
                alert.setContentText("empty search");
                alert.showAndWait();
            }
        }
        partsTable.setItems(parts);
    }
}
