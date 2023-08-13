package group1.gus01;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import other.InHouse;
import other.Inventory;
import other.Product;

import java.io.IOException;
/**gives initial application settings. */
public class MainApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("main.fxml"));

        Scene scene = new Scene(fxmlLoader.load(), 1000, 404);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }
    /** entry for the entire application
     * @param args first arguments
     * */
    public static void main(String[] args) {
        InHouse p1 = new InHouse(1, "apple", 10.00, 3, 1, 10, 7);
        InHouse p2 = new InHouse(2, "bed", 10.00, 3, 1, 10, 7);

        Product q1 = new Product(1, "xerox", 1.00, 3, 1, 10);
        Product q2 = new Product(2, "zebra", 1.00, 3, 1, 10);
        Inventory.addPart(p1);
        Inventory.addPart(p2);

        Inventory.addProduct(q1);
        Inventory.addProduct(q2);

        launch();
    }
}