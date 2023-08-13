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
import other.Part;
import other.Outsourced;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**modifies a part in the inventory. */
public class ModifyPart implements Initializable {
    public RadioButton inHouseButton;
    public RadioButton outsourcedButton;
    public TextField idTextField;
    public TextField nameTextField;
    public TextField inventoryTextField;
    public TextField priceTextField;
    public TextField minTextField;
    public TextField maxTextField;
    public TextField machineIdTextField;
    public Label machineLabel;
    Part part;
    private int index;


    /**set the extended class to inHouse or machineId
     * @param actionEvent action is called when button clicked
     * */
    public void inHouseButtonAction(ActionEvent actionEvent) { machineLabel.setText("Machine Id"); }
    public void outsourcedButtonAction(ActionEvent actionEvent) { machineLabel.setText("Company Name"); }

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
            int stock = Integer.parseInt(inventoryTextField.getText());
            double price = Double.parseDouble(priceTextField.getText());
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
                alert.setContentText("inventory must be between the minimum and maximum");
                alert.showAndWait();
            } else {
                if (inHouseButton.isSelected()) {
                    int machineId = Integer.parseInt(machineIdTextField.getText());
                    InHouse inHouse = new InHouse(id, name, price, stock, min, max, machineId);
                    Inventory.updatePart(index, inHouse);
                }
                if (outsourcedButton.isSelected()) {
                    /////////////
                    String companyName = machineIdTextField.getText();
                    Outsourced outsourced = new Outsourced(id, name, price, stock, min, max, companyName);
                    Inventory.updatePart(index, outsourced);
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
            alert.setTitle("error");
            alert.setContentText("enter valid values in text fields");
            alert.showAndWait();
        }
    }

    /**transfers parts from inventory to the modifyPart scene. */
    public void sendPart(Part part, int index) {
        this.index = index;
        this.part = part;
        idTextField.setText(String.valueOf(part.getId()));
        nameTextField.setText(String.valueOf(part.getName()));
        priceTextField.setText(String.valueOf(part.getPrice()));
        inventoryTextField.setText(String.valueOf(part.getStock()));
        maxTextField.setText(String.valueOf(part.getMax()));
        minTextField.setText(String.valueOf(part.getMin()));
        if (part instanceof InHouse) {
            inHouseButton.setSelected(true);
            machineIdTextField.setText(String.valueOf(((InHouse) part).getMachineId()));
        } else {
            outsourcedButton.setSelected(true);
            machineIdTextField.setText(String.valueOf(((Outsourced) part).getCompanyName()));
            machineLabel.setText("Company Name");
        }
    }

    /**controller initialized. */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }
}











































