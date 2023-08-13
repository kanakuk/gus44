package group1.gus01;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import other.Inventory;
import other.Part;
import other.Product;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import static other.Inventory.searchPartsInventory;
import static other.Inventory.searchProductsInventory;

/**controls the flow of data for the entire program. */
public class MainController implements Initializable  {
    public TextField search1PartsTextField;
    public TextField search2ProductsTextField;
    public TableView<Part> partsTable;
    public TableView productsTable;
    public TableColumn id1Column;
    public TableColumn name2Column;
    public TableColumn inventory3Column;
    public TableColumn price4Column;
    public TableColumn id5Column;
    public TableColumn name6Column;
    public TableColumn inventory7Column;
    public TableColumn price8Column;
    public Button add1Button;
    public Button modify2Button;
    public Button delete3Button;
    public Button add4Button;
    public Button modify5Button;
    public Button delete6Button;

    /**selects user's part choice. */
    private static Part selectedPart;
    /**sets the part. */
    public static void setSelectedPart(Part selected) { Part selectedPart = selected;}
    /**gets the part. */
    public static Part getSelectedPart() {return selectedPart;}

    /**selects user's product choice. */
    private static Product selectedProduct;
    /**sets the product. */
    public static void setSelectedProduct(Product selected) {Product selectedProduct = selected;}
    /**gets the product. */
    public static Product getSelectedProduct() {return selectedProduct; }

    /**creates first table view. */
    public void initialize(URL url, ResourceBundle resourceBundle) {
        partsTable.setItems(Inventory.getAllParts());
        productsTable.setItems(Inventory.getAllProducts());
        id1Column.setCellValueFactory(new PropertyValueFactory<>("id"));
        name2Column.setCellValueFactory(new PropertyValueFactory<>("name"));
        inventory3Column.setCellValueFactory(new PropertyValueFactory<>("price"));
        price4Column.setCellValueFactory(new PropertyValueFactory<>("stock"));
        id5Column.setCellValueFactory(new PropertyValueFactory<>("id"));
        name6Column.setCellValueFactory(new PropertyValueFactory<>("name"));
        inventory7Column.setCellValueFactory(new PropertyValueFactory<>("price"));
        price8Column.setCellValueFactory(new PropertyValueFactory<>("stock"));
    }

    /**gives the first part table searchable functionality
     * @param actionEvent method called upon button press
     * */
    public void search1TextAction(ActionEvent actionEvent) {
        String query = search1PartsTextField.getText().toLowerCase();
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

    /**gives the first product table searchable functionality
     * @param actionEvent method called upon button press
     * */
    public void search2TextAction(ActionEvent actionEvent) {
        String query = search2ProductsTextField.getText().toLowerCase();
        ObservableList<Product> products = searchProductsInventory(query);
        if (products.size() == 0) {
            if(query.matches("\\d*") == false){
                displayNoMatchError();
                return;
            }
            int id = Integer.parseInt(query);
            Product product = searchProductsInventory(id);
            if (product != null) {
                products.add(product);
            }
        }

        if (products.size() == 0) {
            displayNoMatchError();
        }
        productsTable.setItems(products);
    }

    /**alert produced if no mathces were found. */
    public static void displayNoMatchError() {
        Alert errorMessage = new Alert(Alert.AlertType.ERROR);
        errorMessage.setTitle("Error");
        errorMessage.setContentText("No matches were found!");
        errorMessage.showAndWait();
    }

    /**sends the user to the addPart screen. */
    public void add1ButtonAction(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("addPart.fxml"));
        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, 544, 370);
        stage.setTitle("addPart");
        stage.setScene(scene);
        stage.show();
    }

    /**sends the user to modifyPart screen as long as a part is selected
     * @param actionEvent method called upon button press
     * */
    public void modify2ButtonAction(ActionEvent actionEvent) throws IOException {
        Part selected = (Part) partsTable.getSelectionModel().getSelectedItem();
        MainController.setSelectedPart(selected);
        if (selected == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("error");
            alert.setContentText("no part selected");
            alert.showAndWait();
        } else {
            try {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("modifyPart.fxml"));
                Parent root = loader.load();
                Scene scene = new Scene(root, 544, 370);
                ModifyPart modifyPart = loader.getController();
                modifyPart.sendPart(partsTable.getSelectionModel().getSelectedItem(), partsTable.getSelectionModel().getSelectedIndex());
                Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
                stage.setTitle("modifyPart");
                stage.setScene(scene);
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**sends the user to the addProduct scene. */
    public void add4ButtonAction(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("addProduct.fxml"));
        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, 1020, 735);
        stage.setTitle("addProduct");
        stage.setScene(scene);
        stage.show();
    }

    /**sends the user to modifyProduct screen as long as a product is selected
     * @param actionEvent method called upon button press
     * */
    public void modify5ButtonAction(ActionEvent actionEvent) throws IOException {
        Product selected = (Product) productsTable.getSelectionModel().getSelectedItem();
        MainController.setSelectedProduct(selected);
        if (selected == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("error");
            alert.setContentText("no part sel");
            alert.showAndWait();
        } else {
            try {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("modifyProduct.fxml"));
                Parent root = loader.load();
                Scene scene = new Scene(root, 1025, 740);
                ModifyProduct modifyProduct = loader.getController();
                //////
                modifyProduct.sendProduct((Product) productsTable.getSelectionModel().getSelectedItem(), productsTable.getSelectionModel().getSelectedIndex());
                Stage stage  = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
                stage.setTitle("modify Product");
                stage.setScene(scene);
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**returns the user to the first scene. */
    public void exit7ButtonAction(ActionEvent actionEvent) {
        Alert confirm = new Alert(Alert.AlertType.CONFIRMATION, "Do you want to really Quit?");
        Optional<ButtonType> result = confirm.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            Platform.exit();
        }
    }

    /**deletes a part from the table
     * @param actionEvent method called upon button press
     * */
    public void delete3ButtonAction(ActionEvent actionEvent) {
        //added cast to part
        //reference variable partSelected
        Part partSelected = (Part) partsTable.getSelectionModel().getSelectedItem();

        if (partSelected == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("No part selected");
            Optional<ButtonType> result = alert.showAndWait();
        } else {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation");
            alert.setContentText("really?");
            Optional<ButtonType> result = alert.showAndWait();

            if (result.isPresent() && result.get() == ButtonType.OK)
                Inventory.deletePart(partSelected);
        }
    }

    /**deletes a product from the table
     * @param actionEvent method called upon button press
     * */
    public void delete6ButtonAction(ActionEvent actionEvent) {
        Product productSelected = (Product) productsTable.getSelectionModel().getSelectedItem();
        if (productSelected == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("No part selected");
            Optional<ButtonType> result = alert.showAndWait();
        } else if (productSelected.getAllAssociatedParts().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation");
            alert.setContentText("Do you really want to delete this product?");
            Optional<ButtonType> result = alert.showAndWait();

            if (result.isPresent() && result.get() == ButtonType.OK)
                Inventory.deleteProduct(productSelected);
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("cannot delete a part with associated products");
            Optional<ButtonType> result = alert.showAndWait();
        }
    }

}












































