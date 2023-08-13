package group1.gus01;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import other.InHouse;
import other.Inventory;
import other.Outsourced;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

//hover.press to implement methods
/**Class adds a part to the inventory. */
public class AddPart implements Initializable {
    public RadioButton inHouseButton;
    public RadioButton outsourcedButton;
    public TextField nameTextField;
    public TextField inventoryTextField;
    public TextField priceTextField;
    public TextField minTextField;
    public TextField maxTextField;
    public TextField machineIdTextField;
    public Button saveButton;
    public Label changeableLabel;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }

    /**Method takes data from the user and saves it to the inventory
     @param actionEvent button press
     */
    public void saveButtonAction(ActionEvent actionEvent) throws IOException {
        try {
            int id = Inventory.getNewPartId.incrementAndGet();
            String name = nameTextField.getText();
            int stock = Integer.parseInt(inventoryTextField.getText());
            double price = Double.parseDouble(priceTextField.getText());
            int max = Integer.parseInt(maxTextField.getText());
            int min = Integer.parseInt(minTextField.getText());
            if (min > max) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("error");
                alert.setContentText("minimum must be less than the maximum");
                alert.showAndWait();
            } else if (stock < min || stock > max) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("error");
                alert.setContentText("inventory needs to be less than max");
                alert.showAndWait();
            } else {
                //this works but does the inventory need to save that the inhouse button was pressed?
                if (inHouseButton.isSelected()) {
                    int machineId = Integer.parseInt(machineIdTextField.getText());
                    Inventory.addPart(new InHouse(id, name, price, stock, min, max, machineId));
                }
                if (outsourcedButton.isSelected()) {
                    String companyName = machineIdTextField.getText();
                    Inventory.addPart(new Outsourced(id, name, price, stock, min, max, companyName));
                }
                Parent root = FXMLLoader.load(getClass().getResource("main.fxml"));
                Stage stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
                Scene scene = new Scene(root,1000, 1000);
                stage.setTitle("home");
                stage.setScene(scene);
                stage.show();
            }
        } catch (NumberFormatException e) {

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("enter data first");
            alert.showAndWait();
        }
    }

    /**This method allows a user to get out of the addPart window without entering data. */
    public void cancelButtonAction(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("main.fxml"));
        //the (Button) was hard to figure out because I thought it was (Node)
        Stage stage = (Stage) ((Button)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, 1000, 369);
        stage.setTitle("back");
        stage.setScene(scene);
        stage.show();
    }

    /**This method is where the user saves if the part is from inside/outside the company. */
    public void inHouseButtonAction(ActionEvent actionEvent) { changeableLabel.setText("Machine Id");
    }
    public void outsourcedButtonAction(ActionEvent actionEvent) { changeableLabel.setText("Company Name");
    }
}







